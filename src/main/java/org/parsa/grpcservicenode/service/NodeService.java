package org.parsa.grpcservicenode.service;

import io.grpc.ManagedChannel;
import io.grpc.ManagedChannelBuilder;
import io.grpc.stub.StreamObserver;
import jakarta.annotation.PostConstruct;
import org.parsa.grpcservicenode.proto.BroadcastMessage;
import org.parsa.grpcservicenode.proto.BroadcastServiceGrpc;
import org.parsa.grpcservicenode.proto.SubscribeRequest;
import org.springframework.stereotype.Service;

@Service
public class NodeService {

    private BroadcastServiceGrpc.BroadcastServiceStub asyncStub;

    @PostConstruct
    public void init() {
        ManagedChannel channel = ManagedChannelBuilder
                .forAddress("localhost", 50051)
                .usePlaintext()
                .build();

        asyncStub = BroadcastServiceGrpc.newStub(channel);

        subscribeToCore();

        new Thread(() -> {
            try {
                Thread.sleep(Long.MAX_VALUE);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }).start();
    }


    private void subscribeToCore() {
        String nodeId = "node-1";

        asyncStub.subscribe(
                SubscribeRequest.newBuilder().setNodeId(nodeId).build(),
                new StreamObserver<BroadcastMessage>() {
                    @Override
                    public void onNext(BroadcastMessage msg) {
                        System.out.println("Received new broadcast:");
                        System.out.println("Hash: " + msg.getHash());
                        System.out.println("Timestamp: " + msg.getTimestamp());
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

}
