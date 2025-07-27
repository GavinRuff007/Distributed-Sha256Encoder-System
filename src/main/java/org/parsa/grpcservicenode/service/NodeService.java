package org.parsa.grpcservicenode.service;

import io.grpc.ManagedChannel;
import io.grpc.ManagedChannelBuilder;
import io.grpc.stub.StreamObserver;
import jakarta.annotation.PostConstruct;
import node.Node;
import node.NodeServiceGrpc;
import org.parsa.grpcservicenode.proto.BroadcastMessage;
import org.parsa.grpcservicenode.proto.BroadcastServiceGrpc;
import org.parsa.grpcservicenode.proto.SubscribeRequest;
import org.springframework.stereotype.Service;

import io.grpc.*;

import java.util.*;
import java.util.concurrent.ConcurrentHashMap;

@Service
public class NodeService extends NodeServiceGrpc.NodeServiceImplBase {

    private enum NodeState {
        INIT,
        READY,
        LEADER,
        FOLLOWER
    }

    private volatile NodeState state = NodeState.INIT;

    private BroadcastServiceGrpc.BroadcastServiceStub asyncStub;

    // حافظه برای نگهداری Nodeهای Ready
    private final Map<String, String> readyNodes = new ConcurrentHashMap<>();

    private final String myNodeName = "node-2"; // اسم Node فعلی
    private final List<String> peers = Arrays.asList("localhost:6001", "localhost:6002", "localhost:6003"); // Nodeهای دیگر

    @PostConstruct
    public void init() {
        // gRPC برای دریافت Broadcast از Core
        ManagedChannel coreChannel = ManagedChannelBuilder
                .forAddress("localhost", 50051)
                .usePlaintext()
                .build();
        asyncStub = BroadcastServiceGrpc.newStub(coreChannel);
        subscribeToCore();

        // gRPC Server برای دریافت پیام Ready از Nodeهای دیگر
        new Thread(this::startNodeServer).start();

        // جلوگیری از بسته شدن JVM
        new Thread(() -> {
            try {
                Thread.sleep(Long.MAX_VALUE);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }).start();
    }

    private void subscribeToCore() {
        asyncStub.subscribe(
                SubscribeRequest.newBuilder().setNodeId(myNodeName).build(),
                new StreamObserver<BroadcastMessage>() {
                    @Override
                    public void onNext(BroadcastMessage msg) {
                        System.out.println(myNodeName + " received broadcast: " + msg.getHash());
                        if (state == NodeState.INIT) {
                            state = NodeState.READY;
                            System.out.println(myNodeName + " is now READY");

                            // به همه Nodeها پیام بدهیم
                            announceReadyToPeers();
                        }
                    }

                    @Override
                    public void onError(Throwable t) {
                        System.err.println("Stream error: " + t.getMessage());
                    }

                    @Override
                    public void onCompleted() {
                        System.out.println("Stream closed by Core.");
                    }
                }
        );
    }

    // ارسال پیام Ready به همه Nodeهای دیگر
    private void announceReadyToPeers() {
        for (String peer : peers) {
            if (peer.contains(myNodeName)) continue; // به خودش پیام نده

            ManagedChannel channel = ManagedChannelBuilder.forTarget(peer).usePlaintext().build();
            NodeServiceGrpc.NodeServiceBlockingStub stub = NodeServiceGrpc.newBlockingStub(channel);

            Node.ReadyMessage msg = Node.ReadyMessage.newBuilder()
                    .setNodeName(myNodeName)
                    .setState("Ready")
                    .build();

            try {
                stub.announceReady(msg);
                System.out.println(myNodeName + " sent READY to " + peer);
            } catch (Exception e) {
                System.err.println("Failed to send READY to " + peer);
            }
        }
    }

    // این متد وقتی Node دیگر پیام Ready فرستاد صدا زده می‌شود
    @Override
    public void announceReady(Node.ReadyMessage request, StreamObserver<Node.Ack> responseObserver) {
        readyNodes.put(request.getNodeName(), request.getState());
        System.out.println("Received READY from " + request.getNodeName());

        responseObserver.onNext(Node.Ack.newBuilder().setSuccess(true).build());
        responseObserver.onCompleted();

        // اگر همه Nodeها Ready شدند → شروع RAFT
        if (readyNodes.size() == peers.size()) {
            startRaftElection();
        }
    }

    private void startRaftElection() {
        System.out.println("All nodes READY. Starting RAFT election...");
        // اینجا بعداً الگوریتم RAFT را اضافه می‌کنیم (Vote, Leader انتخاب، Heartbeat)
    }

    // راه‌اندازی gRPC Server برای Nodeها
    private void startNodeServer() {
        try {
            Server server = ServerBuilder.forPort(6002) // پورت مخصوص Node فعلی
                    .addService(this)
                    .build()
                    .start();

            System.out.println(myNodeName + " Node gRPC server started on port 6002");
            server.awaitTermination();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
