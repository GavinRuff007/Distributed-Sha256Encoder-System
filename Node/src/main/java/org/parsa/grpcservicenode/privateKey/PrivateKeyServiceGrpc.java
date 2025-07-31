package org.parsa.grpcservicenode.privateKey;

import static io.grpc.MethodDescriptor.generateFullMethodName;

/**
 */
@io.grpc.stub.annotations.GrpcGenerated
public final class PrivateKeyServiceGrpc {

  private PrivateKeyServiceGrpc() {}

  public static final java.lang.String SERVICE_NAME = "privatekey.PrivateKeyService";

  // Static method descriptors that strictly reflect the proto.
  private static volatile io.grpc.MethodDescriptor<org.parsa.grpcservicenode.privateKey.PrivateKeyMessage,
      org.parsa.grpcservicenode.privateKey.Ack> getReceivePrivateKeyMethod;

  @io.grpc.stub.annotations.RpcMethod(
      fullMethodName = SERVICE_NAME + '/' + "ReceivePrivateKey",
      requestType = org.parsa.grpcservicenode.privateKey.PrivateKeyMessage.class,
      responseType = org.parsa.grpcservicenode.privateKey.Ack.class,
      methodType = io.grpc.MethodDescriptor.MethodType.UNARY)
  public static io.grpc.MethodDescriptor<org.parsa.grpcservicenode.privateKey.PrivateKeyMessage,
      org.parsa.grpcservicenode.privateKey.Ack> getReceivePrivateKeyMethod() {
    io.grpc.MethodDescriptor<org.parsa.grpcservicenode.privateKey.PrivateKeyMessage, org.parsa.grpcservicenode.privateKey.Ack> getReceivePrivateKeyMethod;
    if ((getReceivePrivateKeyMethod = PrivateKeyServiceGrpc.getReceivePrivateKeyMethod) == null) {
      synchronized (PrivateKeyServiceGrpc.class) {
        if ((getReceivePrivateKeyMethod = PrivateKeyServiceGrpc.getReceivePrivateKeyMethod) == null) {
          PrivateKeyServiceGrpc.getReceivePrivateKeyMethod = getReceivePrivateKeyMethod =
              io.grpc.MethodDescriptor.<org.parsa.grpcservicenode.privateKey.PrivateKeyMessage, org.parsa.grpcservicenode.privateKey.Ack>newBuilder()
              .setType(io.grpc.MethodDescriptor.MethodType.UNARY)
              .setFullMethodName(generateFullMethodName(SERVICE_NAME, "ReceivePrivateKey"))
              .setSampledToLocalTracing(true)
              .setRequestMarshaller(io.grpc.protobuf.ProtoUtils.marshaller(
                  org.parsa.grpcservicenode.privateKey.PrivateKeyMessage.getDefaultInstance()))
              .setResponseMarshaller(io.grpc.protobuf.ProtoUtils.marshaller(
                  org.parsa.grpcservicenode.privateKey.Ack.getDefaultInstance()))
              .setSchemaDescriptor(new PrivateKeyServiceMethodDescriptorSupplier("ReceivePrivateKey"))
              .build();
        }
      }
    }
    return getReceivePrivateKeyMethod;
  }

  /**
   * Creates a new async stub that supports all call types for the service
   */
  public static PrivateKeyServiceStub newStub(io.grpc.Channel channel) {
    io.grpc.stub.AbstractStub.StubFactory<PrivateKeyServiceStub> factory =
      new io.grpc.stub.AbstractStub.StubFactory<PrivateKeyServiceStub>() {
        @java.lang.Override
        public PrivateKeyServiceStub newStub(io.grpc.Channel channel, io.grpc.CallOptions callOptions) {
          return new PrivateKeyServiceStub(channel, callOptions);
        }
      };
    return PrivateKeyServiceStub.newStub(factory, channel);
  }

  /**
   * Creates a new blocking-style stub that supports all types of calls on the service
   */
  public static PrivateKeyServiceBlockingV2Stub newBlockingV2Stub(
      io.grpc.Channel channel) {
    io.grpc.stub.AbstractStub.StubFactory<PrivateKeyServiceBlockingV2Stub> factory =
      new io.grpc.stub.AbstractStub.StubFactory<PrivateKeyServiceBlockingV2Stub>() {
        @java.lang.Override
        public PrivateKeyServiceBlockingV2Stub newStub(io.grpc.Channel channel, io.grpc.CallOptions callOptions) {
          return new PrivateKeyServiceBlockingV2Stub(channel, callOptions);
        }
      };
    return PrivateKeyServiceBlockingV2Stub.newStub(factory, channel);
  }

  /**
   * Creates a new blocking-style stub that supports unary and streaming output calls on the service
   */
  public static PrivateKeyServiceBlockingStub newBlockingStub(
      io.grpc.Channel channel) {
    io.grpc.stub.AbstractStub.StubFactory<PrivateKeyServiceBlockingStub> factory =
      new io.grpc.stub.AbstractStub.StubFactory<PrivateKeyServiceBlockingStub>() {
        @java.lang.Override
        public PrivateKeyServiceBlockingStub newStub(io.grpc.Channel channel, io.grpc.CallOptions callOptions) {
          return new PrivateKeyServiceBlockingStub(channel, callOptions);
        }
      };
    return PrivateKeyServiceBlockingStub.newStub(factory, channel);
  }

  /**
   * Creates a new ListenableFuture-style stub that supports unary calls on the service
   */
  public static PrivateKeyServiceFutureStub newFutureStub(
      io.grpc.Channel channel) {
    io.grpc.stub.AbstractStub.StubFactory<PrivateKeyServiceFutureStub> factory =
      new io.grpc.stub.AbstractStub.StubFactory<PrivateKeyServiceFutureStub>() {
        @java.lang.Override
        public PrivateKeyServiceFutureStub newStub(io.grpc.Channel channel, io.grpc.CallOptions callOptions) {
          return new PrivateKeyServiceFutureStub(channel, callOptions);
        }
      };
    return PrivateKeyServiceFutureStub.newStub(factory, channel);
  }

  /**
   */
  public interface AsyncService {

    /**
     */
    default void receivePrivateKey(org.parsa.grpcservicenode.privateKey.PrivateKeyMessage request,
        io.grpc.stub.StreamObserver<org.parsa.grpcservicenode.privateKey.Ack> responseObserver) {
      io.grpc.stub.ServerCalls.asyncUnimplementedUnaryCall(getReceivePrivateKeyMethod(), responseObserver);
    }
  }

  /**
   * Base class for the server implementation of the service PrivateKeyService.
   */
  public static abstract class PrivateKeyServiceImplBase
      implements io.grpc.BindableService, AsyncService {

    @java.lang.Override public final io.grpc.ServerServiceDefinition bindService() {
      return PrivateKeyServiceGrpc.bindService(this);
    }
  }

  /**
   * A stub to allow clients to do asynchronous rpc calls to service PrivateKeyService.
   */
  public static final class PrivateKeyServiceStub
      extends io.grpc.stub.AbstractAsyncStub<PrivateKeyServiceStub> {
    private PrivateKeyServiceStub(
        io.grpc.Channel channel, io.grpc.CallOptions callOptions) {
      super(channel, callOptions);
    }

    @java.lang.Override
    protected PrivateKeyServiceStub build(
        io.grpc.Channel channel, io.grpc.CallOptions callOptions) {
      return new PrivateKeyServiceStub(channel, callOptions);
    }

    /**
     */
    public void receivePrivateKey(org.parsa.grpcservicenode.privateKey.PrivateKeyMessage request,
        io.grpc.stub.StreamObserver<org.parsa.grpcservicenode.privateKey.Ack> responseObserver) {
      io.grpc.stub.ClientCalls.asyncUnaryCall(
          getChannel().newCall(getReceivePrivateKeyMethod(), getCallOptions()), request, responseObserver);
    }
  }

  /**
   * A stub to allow clients to do synchronous rpc calls to service PrivateKeyService.
   */
  public static final class PrivateKeyServiceBlockingV2Stub
      extends io.grpc.stub.AbstractBlockingStub<PrivateKeyServiceBlockingV2Stub> {
    private PrivateKeyServiceBlockingV2Stub(
        io.grpc.Channel channel, io.grpc.CallOptions callOptions) {
      super(channel, callOptions);
    }

    @java.lang.Override
    protected PrivateKeyServiceBlockingV2Stub build(
        io.grpc.Channel channel, io.grpc.CallOptions callOptions) {
      return new PrivateKeyServiceBlockingV2Stub(channel, callOptions);
    }

    /**
     */
    public org.parsa.grpcservicenode.privateKey.Ack receivePrivateKey(org.parsa.grpcservicenode.privateKey.PrivateKeyMessage request) throws io.grpc.StatusException {
      return io.grpc.stub.ClientCalls.blockingUnaryCall(
          getChannel(), getReceivePrivateKeyMethod(), getCallOptions(), request);
    }
  }

  /**
   * A stub to allow clients to do limited synchronous rpc calls to service PrivateKeyService.
   */
  public static final class PrivateKeyServiceBlockingStub
      extends io.grpc.stub.AbstractBlockingStub<PrivateKeyServiceBlockingStub> {
    private PrivateKeyServiceBlockingStub(
        io.grpc.Channel channel, io.grpc.CallOptions callOptions) {
      super(channel, callOptions);
    }

    @java.lang.Override
    protected PrivateKeyServiceBlockingStub build(
        io.grpc.Channel channel, io.grpc.CallOptions callOptions) {
      return new PrivateKeyServiceBlockingStub(channel, callOptions);
    }

    /**
     */
    public org.parsa.grpcservicenode.privateKey.Ack receivePrivateKey(org.parsa.grpcservicenode.privateKey.PrivateKeyMessage request) {
      return io.grpc.stub.ClientCalls.blockingUnaryCall(
          getChannel(), getReceivePrivateKeyMethod(), getCallOptions(), request);
    }
  }

  /**
   * A stub to allow clients to do ListenableFuture-style rpc calls to service PrivateKeyService.
   */
  public static final class PrivateKeyServiceFutureStub
      extends io.grpc.stub.AbstractFutureStub<PrivateKeyServiceFutureStub> {
    private PrivateKeyServiceFutureStub(
        io.grpc.Channel channel, io.grpc.CallOptions callOptions) {
      super(channel, callOptions);
    }

    @java.lang.Override
    protected PrivateKeyServiceFutureStub build(
        io.grpc.Channel channel, io.grpc.CallOptions callOptions) {
      return new PrivateKeyServiceFutureStub(channel, callOptions);
    }

    /**
     */
    public com.google.common.util.concurrent.ListenableFuture<org.parsa.grpcservicenode.privateKey.Ack> receivePrivateKey(
        org.parsa.grpcservicenode.privateKey.PrivateKeyMessage request) {
      return io.grpc.stub.ClientCalls.futureUnaryCall(
          getChannel().newCall(getReceivePrivateKeyMethod(), getCallOptions()), request);
    }
  }

  private static final int METHODID_RECEIVE_PRIVATE_KEY = 0;

  private static final class MethodHandlers<Req, Resp> implements
      io.grpc.stub.ServerCalls.UnaryMethod<Req, Resp>,
      io.grpc.stub.ServerCalls.ServerStreamingMethod<Req, Resp>,
      io.grpc.stub.ServerCalls.ClientStreamingMethod<Req, Resp>,
      io.grpc.stub.ServerCalls.BidiStreamingMethod<Req, Resp> {
    private final AsyncService serviceImpl;
    private final int methodId;

    MethodHandlers(AsyncService serviceImpl, int methodId) {
      this.serviceImpl = serviceImpl;
      this.methodId = methodId;
    }

    @java.lang.Override
    @java.lang.SuppressWarnings("unchecked")
    public void invoke(Req request, io.grpc.stub.StreamObserver<Resp> responseObserver) {
      switch (methodId) {
        case METHODID_RECEIVE_PRIVATE_KEY:
          serviceImpl.receivePrivateKey((org.parsa.grpcservicenode.privateKey.PrivateKeyMessage) request,
              (io.grpc.stub.StreamObserver<org.parsa.grpcservicenode.privateKey.Ack>) responseObserver);
          break;
        default:
          throw new AssertionError();
      }
    }

    @java.lang.Override
    @java.lang.SuppressWarnings("unchecked")
    public io.grpc.stub.StreamObserver<Req> invoke(
        io.grpc.stub.StreamObserver<Resp> responseObserver) {
      switch (methodId) {
        default:
          throw new AssertionError();
      }
    }
  }

  public static final io.grpc.ServerServiceDefinition bindService(AsyncService service) {
    return io.grpc.ServerServiceDefinition.builder(getServiceDescriptor())
        .addMethod(
          getReceivePrivateKeyMethod(),
          io.grpc.stub.ServerCalls.asyncUnaryCall(
            new MethodHandlers<
              org.parsa.grpcservicenode.privateKey.PrivateKeyMessage,
              org.parsa.grpcservicenode.privateKey.Ack>(
                service, METHODID_RECEIVE_PRIVATE_KEY)))
        .build();
  }

  private static abstract class PrivateKeyServiceBaseDescriptorSupplier
      implements io.grpc.protobuf.ProtoFileDescriptorSupplier, io.grpc.protobuf.ProtoServiceDescriptorSupplier {
    PrivateKeyServiceBaseDescriptorSupplier() {}

    @java.lang.Override
    public com.google.protobuf.Descriptors.FileDescriptor getFileDescriptor() {
      return org.parsa.grpcservicenode.privateKey.PrivateKey.getDescriptor();
    }

    @java.lang.Override
    public com.google.protobuf.Descriptors.ServiceDescriptor getServiceDescriptor() {
      return getFileDescriptor().findServiceByName("PrivateKeyService");
    }
  }

  private static final class PrivateKeyServiceFileDescriptorSupplier
      extends PrivateKeyServiceBaseDescriptorSupplier {
    PrivateKeyServiceFileDescriptorSupplier() {}
  }

  private static final class PrivateKeyServiceMethodDescriptorSupplier
      extends PrivateKeyServiceBaseDescriptorSupplier
      implements io.grpc.protobuf.ProtoMethodDescriptorSupplier {
    private final java.lang.String methodName;

    PrivateKeyServiceMethodDescriptorSupplier(java.lang.String methodName) {
      this.methodName = methodName;
    }

    @java.lang.Override
    public com.google.protobuf.Descriptors.MethodDescriptor getMethodDescriptor() {
      return getServiceDescriptor().findMethodByName(methodName);
    }
  }

  private static volatile io.grpc.ServiceDescriptor serviceDescriptor;

  public static io.grpc.ServiceDescriptor getServiceDescriptor() {
    io.grpc.ServiceDescriptor result = serviceDescriptor;
    if (result == null) {
      synchronized (PrivateKeyServiceGrpc.class) {
        result = serviceDescriptor;
        if (result == null) {
          serviceDescriptor = result = io.grpc.ServiceDescriptor.newBuilder(SERVICE_NAME)
              .setSchemaDescriptor(new PrivateKeyServiceFileDescriptorSupplier())
              .addMethod(getReceivePrivateKeyMethod())
              .build();
        }
      }
    }
    return result;
  }
}
