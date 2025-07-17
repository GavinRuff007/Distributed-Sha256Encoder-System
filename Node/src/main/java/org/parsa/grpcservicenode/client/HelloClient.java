package org.parsa.grpcservicenode.client;

import net.devh.boot.grpc.client.inject.GrpcClient;
import org.parsa.grpcservicenode.HelloProto;
import org.parsa.grpcservicenode.HelloServiceGrpc;
import org.springframework.stereotype.Component;
import io.grpc.ManagedChannel;
import io.grpc.ManagedChannelBuilder;

