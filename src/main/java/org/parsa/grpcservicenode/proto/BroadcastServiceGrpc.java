package org.parsa.grpcservicenode.proto;

import static io.grpc.MethodDescriptor.generateFullMethodName;

/**
 */
@io.grpc.stub.annotations.GrpcGenerated
public final class BroadcastServiceGrpc {

  private BroadcastServiceGrpc() {}

  public static final java.lang.String SERVICE_NAME = "broadcast.BroadcastService";

  // Static method descriptors that strictly reflect the proto.
  private static volatile io.grpc.MethodDescriptor<org.parsa.grpcservicenode.proto.SubscribeRequest,
      org.parsa.grpcservicenode.proto.BroadcastMessage> getSubscribeMethod;

  @io.grpc.stub.annotations.RpcMethod(
      fullMethodName = SERVICE_NAME + '/' + "Subscribe",
      requestType = org.parsa.grpcservicenode.proto.SubscribeRequest.class,
      responseType = org.parsa.grpcservicenode.proto.BroadcastMessage.class,
      methodType = io.grpc.MethodDescriptor.MethodType.SERVER_STREAMING)
  public static io.grpc.MethodDescriptor<org.parsa.grpcservicenode.proto.SubscribeRequest,
      org.parsa.grpcservicenode.proto.BroadcastMessage> getSubscribeMethod() {
    io.grpc.MethodDescriptor<org.parsa.grpcservicenode.proto.SubscribeRequest, org.parsa.grpcservicenode.proto.BroadcastMessage> getSubscribeMethod;
    if ((getSubscribeMethod = BroadcastServiceGrpc.getSubscribeMethod) == null) {
      synchronized (BroadcastServiceGrpc.class) {
        if ((getSubscribeMethod = BroadcastServiceGrpc.getSubscribeMethod) == null) {
          BroadcastServiceGrpc.getSubscribeMethod = getSubscribeMethod =
              io.grpc.MethodDescriptor.<org.parsa.grpcservicenode.proto.SubscribeRequest, org.parsa.grpcservicenode.proto.BroadcastMessage>newBuilder()
              .setType(io.grpc.MethodDescriptor.MethodType.SERVER_STREAMING)
              .setFullMethodName(generateFullMethodName(SERVICE_NAME, "Subscribe"))
              .setSampledToLocalTracing(true)
              .setRequestMarshaller(io.grpc.protobuf.ProtoUtils.marshaller(
                  org.parsa.grpcservicenode.proto.SubscribeRequest.getDefaultInstance()))
              .setResponseMarshaller(io.grpc.protobuf.ProtoUtils.marshaller(
                  org.parsa.grpcservicenode.proto.BroadcastMessage.getDefaultInstance()))
              .setSchemaDescriptor(new BroadcastServiceMethodDescriptorSupplier("Subscribe"))
              .build();
        }
      }
    }
    return getSubscribeMethod;
  }

  /**
   * Creates a new async stub that supports all call types for the service
   */
  public static BroadcastServiceStub newStub(io.grpc.Channel channel) {
    io.grpc.stub.AbstractStub.StubFactory<BroadcastServiceStub> factory =
      new io.grpc.stub.AbstractStub.StubFactory<BroadcastServiceStub>() {
        @java.lang.Override
        public BroadcastServiceStub newStub(io.grpc.Channel channel, io.grpc.CallOptions callOptions) {
          return new BroadcastServiceStub(channel, callOptions);
        }
      };
    return BroadcastServiceStub.newStub(factory, channel);
  }

  /**
   * Creates a new blocking-style stub that supports all types of calls on the service
   */
  public static BroadcastServiceBlockingV2Stub newBlockingV2Stub(
      io.grpc.Channel channel) {
    io.grpc.stub.AbstractStub.StubFactory<BroadcastServiceBlockingV2Stub> factory =
      new io.grpc.stub.AbstractStub.StubFactory<BroadcastServiceBlockingV2Stub>() {
        @java.lang.Override
        public BroadcastServiceBlockingV2Stub newStub(io.grpc.Channel channel, io.grpc.CallOptions callOptions) {
          return new BroadcastServiceBlockingV2Stub(channel, callOptions);
        }
      };
    return BroadcastServiceBlockingV2Stub.newStub(factory, channel);
  }

  /**
   * Creates a new blocking-style stub that supports unary and streaming output calls on the service
   */
  public static BroadcastServiceBlockingStub newBlockingStub(
      io.grpc.Channel channel) {
    io.grpc.stub.AbstractStub.StubFactory<BroadcastServiceBlockingStub> factory =
      new io.grpc.stub.AbstractStub.StubFactory<BroadcastServiceBlockingStub>() {
        @java.lang.Override
        public BroadcastServiceBlockingStub newStub(io.grpc.Channel channel, io.grpc.CallOptions callOptions) {
          return new BroadcastServiceBlockingStub(channel, callOptions);
        }
      };
    return BroadcastServiceBlockingStub.newStub(factory, channel);
  }

  /**
   * Creates a new ListenableFuture-style stub that supports unary calls on the service
   */
  public static BroadcastServiceFutureStub newFutureStub(
      io.grpc.Channel channel) {
    io.grpc.stub.AbstractStub.StubFactory<BroadcastServiceFutureStub> factory =
      new io.grpc.stub.AbstractStub.StubFactory<BroadcastServiceFutureStub>() {
        @java.lang.Override
        public BroadcastServiceFutureStub newStub(io.grpc.Channel channel, io.grpc.CallOptions callOptions) {
          return new BroadcastServiceFutureStub(channel, callOptions);
        }
      };
    return BroadcastServiceFutureStub.newStub(factory, channel);
  }

  /**
   */
  public interface AsyncService {

    /**
     * <pre>
     * Nodeها به این متد Subscribe می‌کنند تا Stream پیام‌ها را دریافت کنند
     * </pre>
     */
    default void subscribe(org.parsa.grpcservicenode.proto.SubscribeRequest request,
        io.grpc.stub.StreamObserver<org.parsa.grpcservicenode.proto.BroadcastMessage> responseObserver) {
      io.grpc.stub.ServerCalls.asyncUnimplementedUnaryCall(getSubscribeMethod(), responseObserver);
    }
  }

  /**
   * Base class for the server implementation of the service BroadcastService.
   */
  public static abstract class BroadcastServiceImplBase
      implements io.grpc.BindableService, AsyncService {

    @java.lang.Override public final io.grpc.ServerServiceDefinition bindService() {
      return BroadcastServiceGrpc.bindService(this);
    }
  }

  /**
   * A stub to allow clients to do asynchronous rpc calls to service BroadcastService.
   */
  public static final class BroadcastServiceStub
      extends io.grpc.stub.AbstractAsyncStub<BroadcastServiceStub> {
    private BroadcastServiceStub(
        io.grpc.Channel channel, io.grpc.CallOptions callOptions) {
      super(channel, callOptions);
    }

    @java.lang.Override
    protected BroadcastServiceStub build(
        io.grpc.Channel channel, io.grpc.CallOptions callOptions) {
      return new BroadcastServiceStub(channel, callOptions);
    }

    /**
     * <pre>
     * Nodeها به این متد Subscribe می‌کنند تا Stream پیام‌ها را دریافت کنند
     * </pre>
     */
    public void subscribe(org.parsa.grpcservicenode.proto.SubscribeRequest request,
        io.grpc.stub.StreamObserver<org.parsa.grpcservicenode.proto.BroadcastMessage> responseObserver) {
      io.grpc.stub.ClientCalls.asyncServerStreamingCall(
          getChannel().newCall(getSubscribeMethod(), getCallOptions()), request, responseObserver);
    }
  }

  /**
   * A stub to allow clients to do synchronous rpc calls to service BroadcastService.
   */
  public static final class BroadcastServiceBlockingV2Stub
      extends io.grpc.stub.AbstractBlockingStub<BroadcastServiceBlockingV2Stub> {
    private BroadcastServiceBlockingV2Stub(
        io.grpc.Channel channel, io.grpc.CallOptions callOptions) {
      super(channel, callOptions);
    }

    @java.lang.Override
    protected BroadcastServiceBlockingV2Stub build(
        io.grpc.Channel channel, io.grpc.CallOptions callOptions) {
      return new BroadcastServiceBlockingV2Stub(channel, callOptions);
    }

    /**
     * <pre>
     * Nodeها به این متد Subscribe می‌کنند تا Stream پیام‌ها را دریافت کنند
     * </pre>
     */
    @io.grpc.ExperimentalApi("https://github.com/grpc/grpc-java/issues/10918")
    public io.grpc.stub.BlockingClientCall<?, org.parsa.grpcservicenode.proto.BroadcastMessage>
        subscribe(org.parsa.grpcservicenode.proto.SubscribeRequest request) {
      return io.grpc.stub.ClientCalls.blockingV2ServerStreamingCall(
          getChannel(), getSubscribeMethod(), getCallOptions(), request);
    }
  }

  /**
   * A stub to allow clients to do limited synchronous rpc calls to service BroadcastService.
   */
  public static final class BroadcastServiceBlockingStub
      extends io.grpc.stub.AbstractBlockingStub<BroadcastServiceBlockingStub> {
    private BroadcastServiceBlockingStub(
        io.grpc.Channel channel, io.grpc.CallOptions callOptions) {
      super(channel, callOptions);
    }

    @java.lang.Override
    protected BroadcastServiceBlockingStub build(
        io.grpc.Channel channel, io.grpc.CallOptions callOptions) {
      return new BroadcastServiceBlockingStub(channel, callOptions);
    }

    /**
     * <pre>
     * Nodeها به این متد Subscribe می‌کنند تا Stream پیام‌ها را دریافت کنند
     * </pre>
     */
    public java.util.Iterator<org.parsa.grpcservicenode.proto.BroadcastMessage> subscribe(
        org.parsa.grpcservicenode.proto.SubscribeRequest request) {
      return io.grpc.stub.ClientCalls.blockingServerStreamingCall(
          getChannel(), getSubscribeMethod(), getCallOptions(), request);
    }
  }

  /**
   * A stub to allow clients to do ListenableFuture-style rpc calls to service BroadcastService.
   */
  public static final class BroadcastServiceFutureStub
      extends io.grpc.stub.AbstractFutureStub<BroadcastServiceFutureStub> {
    private BroadcastServiceFutureStub(
        io.grpc.Channel channel, io.grpc.CallOptions callOptions) {
      super(channel, callOptions);
    }

    @java.lang.Override
    protected BroadcastServiceFutureStub build(
        io.grpc.Channel channel, io.grpc.CallOptions callOptions) {
      return new BroadcastServiceFutureStub(channel, callOptions);
    }
  }

  private static final int METHODID_SUBSCRIBE = 0;

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
        case METHODID_SUBSCRIBE:
          serviceImpl.subscribe((org.parsa.grpcservicenode.proto.SubscribeRequest) request,
              (io.grpc.stub.StreamObserver<org.parsa.grpcservicenode.proto.BroadcastMessage>) responseObserver);
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
          getSubscribeMethod(),
          io.grpc.stub.ServerCalls.asyncServerStreamingCall(
            new MethodHandlers<
              org.parsa.grpcservicenode.proto.SubscribeRequest,
              org.parsa.grpcservicenode.proto.BroadcastMessage>(
                service, METHODID_SUBSCRIBE)))
        .build();
  }

  private static abstract class BroadcastServiceBaseDescriptorSupplier
      implements io.grpc.protobuf.ProtoFileDescriptorSupplier, io.grpc.protobuf.ProtoServiceDescriptorSupplier {
    BroadcastServiceBaseDescriptorSupplier() {}

    @java.lang.Override
    public com.google.protobuf.Descriptors.FileDescriptor getFileDescriptor() {
      return org.parsa.grpcservicenode.proto.Broadcast.getDescriptor();
    }

    @java.lang.Override
    public com.google.protobuf.Descriptors.ServiceDescriptor getServiceDescriptor() {
      return getFileDescriptor().findServiceByName("BroadcastService");
    }
  }

  private static final class BroadcastServiceFileDescriptorSupplier
      extends BroadcastServiceBaseDescriptorSupplier {
    BroadcastServiceFileDescriptorSupplier() {}
  }

  private static final class BroadcastServiceMethodDescriptorSupplier
      extends BroadcastServiceBaseDescriptorSupplier
      implements io.grpc.protobuf.ProtoMethodDescriptorSupplier {
    private final java.lang.String methodName;

    BroadcastServiceMethodDescriptorSupplier(java.lang.String methodName) {
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
      synchronized (BroadcastServiceGrpc.class) {
        result = serviceDescriptor;
        if (result == null) {
          serviceDescriptor = result = io.grpc.ServiceDescriptor.newBuilder(SERVICE_NAME)
              .setSchemaDescriptor(new BroadcastServiceFileDescriptorSupplier())
              .addMethod(getSubscribeMethod())
              .build();
        }
      }
    }
    return result;
  }
}
