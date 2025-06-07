package org.parsa.grpcservicenode.service;

import io.grpc.stub.StreamObserver;
import net.devh.boot.grpc.server.service.GrpcService;
import org.parsa.grpcservicenode.HelloProto;
import org.parsa.grpcservicenode.HelloServiceGrpc;

@GrpcService
public class HelloServiceImpl extends HelloServiceGrpc.HelloServiceImplBase {
    @Override
    public void sayHello(HelloProto.HelloRequest request, StreamObserver<HelloProto.HelloResponse> responseObserver) {
        String greeting = "Hello " + request.getName() + " Welcome to my world!";
        HelloProto.HelloResponse response = HelloProto.HelloResponse.newBuilder()
                .setMessage(greeting)
                .build();
        responseObserver.onNext(response);
        responseObserver.onCompleted();
    }
}


