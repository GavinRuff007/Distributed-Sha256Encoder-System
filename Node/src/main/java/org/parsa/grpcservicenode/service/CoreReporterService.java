package org.parsa.grpcservicenode.service;

import consensus.Ack;
import consensus.CoreConsensusServiceGrpc;
import consensus.LeaderMessage;
import consensus.VoteReport;
import io.grpc.ManagedChannel;
import io.grpc.ManagedChannelBuilder;
import org.springframework.stereotype.Service;

@Service
public class CoreReporterService {

    private final String host = "localhost";
    private final int port = 6000;

    public void reportLeaderToCore(String leaderName) {
        ManagedChannel channel = ManagedChannelBuilder.forAddress(host, port)
                .usePlaintext()
                .build();
        CoreConsensusServiceGrpc.CoreConsensusServiceBlockingStub stub =
                CoreConsensusServiceGrpc.newBlockingStub(channel);
        LeaderMessage msg = LeaderMessage.newBuilder()
                .setLeaderName(leaderName)
                .build();
        try {
            Ack ack = stub.reportLeader(msg);
            if (ack.getSuccess()) {
                System.out.println("Leader reported to Core: " + leaderName);
            } else {
                System.err.println("Failed to report leader to Core!");
            }
        } catch (Exception e) {
            System.err.println("Error reporting leader to Core: " + e.getMessage());
        } finally {
            channel.shutdown();
        }
    }

    // متد گزارش Vote (به Core): مقدار term را هم باید پاس بدهی!
    public void reportVoteToCore(String voterNode, String candidateNode, int term) {
        ManagedChannel channel = ManagedChannelBuilder.forAddress(host, port)
                .usePlaintext()
                .build();
        CoreConsensusServiceGrpc.CoreConsensusServiceBlockingStub stub =
                CoreConsensusServiceGrpc.newBlockingStub(channel);
        VoteReport msg = VoteReport.newBuilder()
                .setVoter(voterNode)
                .setCandidate(candidateNode)
                .setTerm(term)
                .build();
        try {
            Ack ack = stub.reportVote(msg);
            if (ack.getSuccess()) {
                System.out.println("Vote reported to Core: " + voterNode + " -> " + candidateNode + " [term=" + term + "]");
            } else {
                System.err.println("Failed to report vote to Core!");
            }
        } catch (Exception e) {
            System.err.println("Error reporting vote to Core: " + e.getMessage());
        } finally {
            channel.shutdown();
        }
    }
}
