package org.parsa.grpcservicenode.service;

import io.grpc.ManagedChannel;
import io.grpc.ManagedChannelBuilder;
import io.grpc.Server;
import io.grpc.ServerBuilder;
import io.grpc.stub.StreamObserver;
import jakarta.annotation.PostConstruct;
import node.*;
import org.parsa.grpcservicenode.config.NodeConfig;
import org.parsa.grpcservicenode.email.EmailService;
import org.parsa.grpcservicenode.privateKey.PrivateKeyReceiverService;
import org.parsa.grpcservicenode.proto.BroadcastMessage;
import org.parsa.grpcservicenode.proto.BroadcastServiceGrpc;
import org.parsa.grpcservicenode.proto.SubscribeRequest;
import org.parsa.grpcservicenode.util.SpringContextHolder;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Map;
import java.util.Random;
import java.util.concurrent.*;
import java.util.concurrent.atomic.AtomicBoolean;

@Service
public class NodeService extends NodeServiceGrpc.NodeServiceImplBase {

    private Server grpcServer;
    private enum NodeState { INIT, READY, FOLLOWER, CANDIDATE, LEADER }
    private volatile NodeState state = NodeState.INIT;

    private final String myNodeName;
    private final List<String> peers;

    public NodeService(NodeConfig nodeConfig) {
        this.myNodeName = nodeConfig.getMyNodeName();
        this.peers = nodeConfig.getPeers();
    }

    private BroadcastServiceGrpc.BroadcastServiceStub asyncStub;
    private final Map<String, String> readyNodes = new ConcurrentHashMap<>();

    private volatile int currentTerm = 0;
    private volatile String votedFor = null;
    private volatile String currentLeader = null;
    private final Object electionLock = new Object();
    private final AtomicBoolean electionStarted = new AtomicBoolean(false);

    private int myRandom = 0;
    private volatile int maxObservedRandom = Integer.MIN_VALUE;
    private volatile String maxObservedCandidate = null;
    private final Map<String, Integer> candidateRandoms = new ConcurrentHashMap<>();
    private final ScheduledExecutorService scheduler = Executors.newSingleThreadScheduledExecutor();
    private ScheduledFuture<?> heartbeatFuture = null;

    private volatile String lastBroadcastHash = "";

    @Autowired
    private PrivateKeyReceiverService privateKeyReceiverService;

    @Autowired
    private CoreReporterService coreReporterService;

    @PostConstruct
    public void init() {
        readyNodes.put(myNodeName, "Ready");
        EmailService.setNodeName(myNodeName);
        ManagedChannel coreChannel = ManagedChannelBuilder.forAddress("localhost", 50051).usePlaintext().build();
        asyncStub = BroadcastServiceGrpc.newStub(coreChannel);
        subscribeToCore();
        new Thread(this::startNodeServer).start();
        new Thread(() -> {
            try { Thread.sleep(Long.MAX_VALUE); } catch (InterruptedException ignored) {}
        }).start();
    }

    private void subscribeToCore() {
        asyncStub.subscribe(
                SubscribeRequest.newBuilder().setNodeId(myNodeName).build(),
                new StreamObserver<BroadcastMessage>() {
                    @Override
                    public void onNext(BroadcastMessage msg) {
                        if (!msg.getHash().equals(lastBroadcastHash)) {
                            lastBroadcastHash = msg.getHash();
                            System.out.println(myNodeName + " received NEW broadcast: " + msg.getHash());
                            resetNodeStateAndStart();
                            EmailService.setEmail(msg.getTimestamp());
                        } else {
                            System.out.println(myNodeName + " received DUPLICATE broadcast: " + msg.getHash());
                        }
                    }
                    @Override public void onError(Throwable t) { System.err.println("Stream error: " + t.getMessage());
                        SpringContextHolder.restartApp();
                    }
                    @Override public void onCompleted() { System.out.println("Stream closed by Core."); }
                }
        );
    }

    public void resetNodeStateAndStart() {
        synchronized (electionLock) {
            System.out.println(myNodeName + " restarting due to broadcast...");

            if (heartbeatFuture != null && !heartbeatFuture.isCancelled()) {
                heartbeatFuture.cancel(true);
                heartbeatFuture = null;
            }

            state = NodeState.READY;
            currentTerm = 0;
            votedFor = null;
            currentLeader = null;
            myRandom = 0;
            maxObservedRandom = Integer.MIN_VALUE;
            maxObservedCandidate = null;
            candidateRandoms.clear();
            readyNodes.clear();
            readyNodes.put(myNodeName, "Ready");
            electionStarted.set(false);

            announceReadyToPeers();
        }

        scheduler.schedule(() -> {
            if (readyNodes.size() == peers.size() && electionStarted.compareAndSet(false, true)) {
                System.out.println(myNodeName + ": All nodes READY (post-broadcast). Starting election...");
                startElection();
            }
        }, 1000, TimeUnit.MILLISECONDS);
    }


    private void announceReadyToPeers() {
        for (String peer : peers) {
            if (peer.contains(myNodeName)) continue;
            ManagedChannel channel = ManagedChannelBuilder.forTarget(peer).usePlaintext().build();
            try {
                NodeServiceGrpc.NodeServiceBlockingStub stub = NodeServiceGrpc.newBlockingStub(channel);
                ReadyMessage msg = ReadyMessage.newBuilder().setNodeName(myNodeName).setState("Ready").build();
                stub.announceReady(msg);
            } catch (Exception e) {
                System.err.println("Failed to send READY to " + peer);
            } finally {
                channel.shutdown();
            }
        }
    }

    @Override
    public void announceReady(node.ReadyMessage request, StreamObserver<node.Ack> responseObserver) {
        if (!request.getNodeName().equals(myNodeName)) {
            readyNodes.put(request.getNodeName(), request.getState());
            System.out.println(myNodeName + " received READY from " + request.getNodeName());
        }
        responseObserver.onNext(node.Ack.newBuilder().setSuccess(true).build());
        responseObserver.onCompleted();




        if (readyNodes.size() == peers.size() && electionStarted.compareAndSet(false, true)) {
            System.out.println(myNodeName + ": All nodes READY. Starting election...");
            startElection();
        }
    }


    private void startElection() {
        synchronized (electionLock) {
            if (state == NodeState.LEADER || state == NodeState.FOLLOWER) return;
            state = NodeState.CANDIDATE;
            currentTerm++;
            myRandom = new Random().nextInt(1_000_000_000);
            maxObservedRandom = myRandom;
            maxObservedCandidate = myNodeName;
            votedFor = myNodeName;
            currentLeader = null;
            candidateRandoms.clear();
            candidateRandoms.put(myNodeName, myRandom);

            System.out.println(myNodeName + " became CANDIDATE for term " + currentTerm + " with random " + myRandom);
            scheduler.schedule(this::exchangeRandomsAndProceed, 400, TimeUnit.MILLISECONDS);
        }
    }

    private void exchangeRandomsAndProceed() {
        synchronized (electionLock) {
            for (String peer : peers) {
                if (peer.contains(myNodeName)) continue;
                ManagedChannel channel = ManagedChannelBuilder.forTarget(peer).usePlaintext().build();
                try {
                    NodeServiceGrpc.NodeServiceBlockingStub stub = NodeServiceGrpc.newBlockingStub(channel);
                    CandidateInfo response = stub.exchangeCandidateInfo(
                            CandidateInfo.newBuilder()
                                    .setNodeName(myNodeName)
                                    .setRandomNumber(myRandom)
                                    .build()
                    );
                    candidateRandoms.put(response.getNodeName(), response.getRandomNumber());
                } catch (Exception e) {
                    System.err.println(myNodeName + " failed to get candidate info from " + peer);
                } finally {
                    channel.shutdown();
                }
            }
            scheduler.schedule(this::proceedElection, 200, TimeUnit.MILLISECONDS);
        }
    }

    private void proceedElection() {
        synchronized (electionLock) {
            String maxNode = myNodeName;
            int maxRand = myRandom;
            for (Map.Entry<String, Integer> entry : candidateRandoms.entrySet()) {
                if (entry.getValue() > maxRand || (entry.getValue() == maxRand && entry.getKey().compareTo(maxNode) < 0)) {
                    maxRand = entry.getValue();
                    maxNode = entry.getKey();
                }
            }
            maxObservedRandom = maxRand;
            maxObservedCandidate = maxNode;

            if (maxNode.equals(myNodeName)) {
                int votes = 1;
                for (String peer : peers) {
                    if (peer.contains(myNodeName)) continue;
                    ManagedChannel channel = ManagedChannelBuilder.forTarget(peer).usePlaintext().build();
                    try {
                        NodeServiceGrpc.NodeServiceBlockingStub stub = NodeServiceGrpc.newBlockingStub(channel);
                        VoteResponse resp = stub.requestVote(VoteRequest.newBuilder()
                                .setCandidateName(myNodeName)
                                .setTerm(currentTerm)
                                .setCandidateRandom(myRandom)
                                .build());
                        if (resp.getVoteGranted()) votes++;
                    } catch (Exception ignored) {} finally {
                        channel.shutdown();
                    }
                }
                if (votes > peers.size() / 2) {
                    System.out.println(myNodeName + " is LEADER! with votes: " + votes);
                    becomeLeader();
                } else {
                    state = NodeState.FOLLOWER;
                    System.out.println(myNodeName + " failed to become leader, became FOLLOWER.");
                }
            } else {
                votedFor = maxNode;
                state = NodeState.FOLLOWER;
                System.out.println(myNodeName + " voted for " + maxNode + " and became FOLLOWER.");
                coreReporterService.reportVoteToCore(myNodeName, maxNode, currentTerm);
            }
        }
    }

    @Override
    public void exchangeCandidateInfo(CandidateInfo req, StreamObserver<CandidateInfo> responseObserver) {
        responseObserver.onNext(CandidateInfo.newBuilder().setNodeName(myNodeName).setRandomNumber(myRandom).build());
        responseObserver.onCompleted();
    }

    @Override
    public void requestVote(VoteRequest req, StreamObserver<VoteResponse> responseObserver) {
        boolean grantVote = false;
        synchronized (electionLock) {
            if (req.getTerm() < currentTerm) {
                grantVote = false;
            } else if (req.getTerm() > currentTerm) {
                currentTerm = req.getTerm();
                state = NodeState.FOLLOWER;
                votedFor = null;
                maxObservedRandom = req.getCandidateRandom();
                maxObservedCandidate = req.getCandidateName();
            }
            if ((votedFor == null || votedFor.equals(req.getCandidateName())) && req.getTerm() == currentTerm) {
                if (req.getCandidateRandom() == maxObservedRandom && req.getCandidateName().equals(maxObservedCandidate)) {
                    grantVote = true;
                    votedFor = req.getCandidateName();
                    coreReporterService.reportVoteToCore(myNodeName, req.getCandidateName(), req.getTerm());
                }
            }
        }
        responseObserver.onNext(VoteResponse.newBuilder().setVoteGranted(grantVote).setTerm(currentTerm).build());
        responseObserver.onCompleted();
    }

    @Override
    public void announceLeader(node.LeaderMessage request, StreamObserver<node.Ack> responseObserver) {
        synchronized (electionLock) {
            if (!request.getLeaderName().equals(myNodeName)) {
                currentLeader = request.getLeaderName();
                state = NodeState.FOLLOWER;
                votedFor = null;
                System.out.println(myNodeName + " accepted " + currentLeader + " as LEADER.");
                reportLeaderToCore(currentLeader);
            }
        }
        responseObserver.onNext(node.Ack.newBuilder().setSuccess(true).build());
        responseObserver.onCompleted();
    }

    private void becomeLeader() {
        state = NodeState.LEADER;
        currentLeader = myNodeName;
        System.out.println(myNodeName + " is LEADER!");
        for (String peer : peers) {
            if (peer.contains(myNodeName)) continue;
            ManagedChannel channel = ManagedChannelBuilder.forTarget(peer).usePlaintext().build();
            try {
                NodeServiceGrpc.NodeServiceBlockingStub stub = NodeServiceGrpc.newBlockingStub(channel);
                stub.announceLeader(node.LeaderMessage.newBuilder().setLeaderName(myNodeName).build());
            } catch (Exception ignored) {
            } finally {
                channel.shutdown();
            }
        }
        reportLeaderToCore(currentLeader);
        startHeartbeat();
    }

    private void startHeartbeat() {
        heartbeatFuture = scheduler.scheduleAtFixedRate(() -> {
            if (state != NodeState.LEADER) return;
            for (String peer : peers) {
                if (peer.contains(myNodeName)) continue;
                ManagedChannel channel = ManagedChannelBuilder.forTarget(peer).usePlaintext().build();
                try {
                    NodeServiceGrpc.NodeServiceBlockingStub stub = NodeServiceGrpc.newBlockingStub(channel);
                    stub.announceLeader(node.LeaderMessage.newBuilder().setLeaderName(myNodeName).build());
                } catch (Exception ignored) {
                } finally {
                    channel.shutdown();
                }
            }
        }, 0, 500, TimeUnit.MILLISECONDS);
    }

    private void reportLeaderToCore(String leaderName) {
        coreReporterService.reportLeaderToCore(leaderName);
    }

    private void startNodeServer() {
        try {
            int port = Integer.parseInt(myNodeName.split("-")[1]) + 6000;
            grpcServer = ServerBuilder.forPort(port)
                    .addService(this)
                    .addService(privateKeyReceiverService) // اگر قصد تزریق نداری
                    .build()
                    .start();
            System.out.println(myNodeName + " Node gRPC server started on port " + port);
            grpcServer.awaitTermination();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public void shutdownGrpcServer() {
        if (grpcServer != null && !grpcServer.isShutdown()) {
            grpcServer.shutdownNow();
            System.out.println("✅ gRPC Server shut down.");
        }
    }

}