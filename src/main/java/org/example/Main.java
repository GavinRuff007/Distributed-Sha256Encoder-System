package org.example;


import io.grpc.ManagedChannel;
import io.grpc.ManagedChannelBuilder;
import org.example.HelloProto;
import org.example.HelloServiceGrpc;

public class Main {
    public static void main(String[] args) {
        ManagedChannel channel = ManagedChannelBuilder.forAddress("localhost", 9090)
                .usePlaintext()
                .build();

        HelloServiceGrpc.HelloServiceBlockingStub stub = HelloServiceGrpc.newBlockingStub(channel);

        HelloProto.HelloResponse response = stub.sayHello(HelloProto.HelloRequest.newBuilder()
                .setName("Parsa")
                .build());

        System.out.println(response.getMessage());

        channel.shutdown();
    }
}
