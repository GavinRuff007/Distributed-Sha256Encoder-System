package node;

import static io.grpc.MethodDescriptor.generateFullMethodName;

/**
 */
@io.grpc.stub.annotations.GrpcGenerated
public final class NodeServiceGrpc {

  private NodeServiceGrpc() {}

  public static final java.lang.String SERVICE_NAME = "node.NodeService";

  // Static method descriptors that strictly reflect the proto.
  private static volatile io.grpc.MethodDescriptor<node.Node.ReadyMessage,
      node.Node.Ack> getAnnounceReadyMethod;

  @io.grpc.stub.annotations.RpcMethod(
      fullMethodName = SERVICE_NAME + '/' + "AnnounceReady",
      requestType = node.Node.ReadyMessage.class,
      responseType = node.Node.Ack.class,
      methodType = io.grpc.MethodDescriptor.MethodType.UNARY)
  public static io.grpc.MethodDescriptor<node.Node.ReadyMessage,
      node.Node.Ack> getAnnounceReadyMethod() {
    io.grpc.MethodDescriptor<node.Node.ReadyMessage, node.Node.Ack> getAnnounceReadyMethod;
    if ((getAnnounceReadyMethod = NodeServiceGrpc.getAnnounceReadyMethod) == null) {
      synchronized (NodeServiceGrpc.class) {
        if ((getAnnounceReadyMethod = NodeServiceGrpc.getAnnounceReadyMethod) == null) {
          NodeServiceGrpc.getAnnounceReadyMethod = getAnnounceReadyMethod =
              io.grpc.MethodDescriptor.<node.Node.ReadyMessage, node.Node.Ack>newBuilder()
              .setType(io.grpc.MethodDescriptor.MethodType.UNARY)
              .setFullMethodName(generateFullMethodName(SERVICE_NAME, "AnnounceReady"))
              .setSampledToLocalTracing(true)
              .setRequestMarshaller(io.grpc.protobuf.ProtoUtils.marshaller(
                  node.Node.ReadyMessage.getDefaultInstance()))
              .setResponseMarshaller(io.grpc.protobuf.ProtoUtils.marshaller(
                  node.Node.Ack.getDefaultInstance()))
              .setSchemaDescriptor(new NodeServiceMethodDescriptorSupplier("AnnounceReady"))
              .build();
        }
      }
    }
    return getAnnounceReadyMethod;
  }

  /**
   * Creates a new async stub that supports all call types for the service
   */
  public static NodeServiceStub newStub(io.grpc.Channel channel) {
    io.grpc.stub.AbstractStub.StubFactory<NodeServiceStub> factory =
      new io.grpc.stub.AbstractStub.StubFactory<NodeServiceStub>() {
        @java.lang.Override
        public NodeServiceStub newStub(io.grpc.Channel channel, io.grpc.CallOptions callOptions) {
          return new NodeServiceStub(channel, callOptions);
        }
      };
    return NodeServiceStub.newStub(factory, channel);
  }

  /**
   * Creates a new blocking-style stub that supports all types of calls on the service
   */
  public static NodeServiceBlockingV2Stub newBlockingV2Stub(
      io.grpc.Channel channel) {
    io.grpc.stub.AbstractStub.StubFactory<NodeServiceBlockingV2Stub> factory =
      new io.grpc.stub.AbstractStub.StubFactory<NodeServiceBlockingV2Stub>() {
        @java.lang.Override
        public NodeServiceBlockingV2Stub newStub(io.grpc.Channel channel, io.grpc.CallOptions callOptions) {
          return new NodeServiceBlockingV2Stub(channel, callOptions);
        }
      };
    return NodeServiceBlockingV2Stub.newStub(factory, channel);
  }

  /**
   * Creates a new blocking-style stub that supports unary and streaming output calls on the service
   */
  public static NodeServiceBlockingStub newBlockingStub(
      io.grpc.Channel channel) {
    io.grpc.stub.AbstractStub.StubFactory<NodeServiceBlockingStub> factory =
      new io.grpc.stub.AbstractStub.StubFactory<NodeServiceBlockingStub>() {
        @java.lang.Override
        public NodeServiceBlockingStub newStub(io.grpc.Channel channel, io.grpc.CallOptions callOptions) {
          return new NodeServiceBlockingStub(channel, callOptions);
        }
      };
    return NodeServiceBlockingStub.newStub(factory, channel);
  }

  /**
   * Creates a new ListenableFuture-style stub that supports unary calls on the service
   */
  public static NodeServiceFutureStub newFutureStub(
      io.grpc.Channel channel) {
    io.grpc.stub.AbstractStub.StubFactory<NodeServiceFutureStub> factory =
      new io.grpc.stub.AbstractStub.StubFactory<NodeServiceFutureStub>() {
        @java.lang.Override
        public NodeServiceFutureStub newStub(io.grpc.Channel channel, io.grpc.CallOptions callOptions) {
          return new NodeServiceFutureStub(channel, callOptions);
        }
      };
    return NodeServiceFutureStub.newStub(factory, channel);
  }

  /**
   */
  public interface AsyncService {

    /**
     */
    default void announceReady(node.Node.ReadyMessage request,
        io.grpc.stub.StreamObserver<node.Node.Ack> responseObserver) {
      io.grpc.stub.ServerCalls.asyncUnimplementedUnaryCall(getAnnounceReadyMethod(), responseObserver);
    }
  }

  /**
   * Base class for the server implementation of the service NodeService.
   */
  public static abstract class NodeServiceImplBase
      implements io.grpc.BindableService, AsyncService {

    @java.lang.Override public final io.grpc.ServerServiceDefinition bindService() {
      return NodeServiceGrpc.bindService(this);
    }
  }

  /**
   * A stub to allow clients to do asynchronous rpc calls to service NodeService.
   */
  public static final class NodeServiceStub
      extends io.grpc.stub.AbstractAsyncStub<NodeServiceStub> {
    private NodeServiceStub(
        io.grpc.Channel channel, io.grpc.CallOptions callOptions) {
      super(channel, callOptions);
    }

    @java.lang.Override
    protected NodeServiceStub build(
        io.grpc.Channel channel, io.grpc.CallOptions callOptions) {
      return new NodeServiceStub(channel, callOptions);
    }

    /**
     */
    public void announceReady(node.Node.ReadyMessage request,
        io.grpc.stub.StreamObserver<node.Node.Ack> responseObserver) {
      io.grpc.stub.ClientCalls.asyncUnaryCall(
          getChannel().newCall(getAnnounceReadyMethod(), getCallOptions()), request, responseObserver);
    }
  }

  /**
   * A stub to allow clients to do synchronous rpc calls to service NodeService.
   */
  public static final class NodeServiceBlockingV2Stub
      extends io.grpc.stub.AbstractBlockingStub<NodeServiceBlockingV2Stub> {
    private NodeServiceBlockingV2Stub(
        io.grpc.Channel channel, io.grpc.CallOptions callOptions) {
      super(channel, callOptions);
    }

    @java.lang.Override
    protected NodeServiceBlockingV2Stub build(
        io.grpc.Channel channel, io.grpc.CallOptions callOptions) {
      return new NodeServiceBlockingV2Stub(channel, callOptions);
    }

    /**
     */
    public node.Node.Ack announceReady(node.Node.ReadyMessage request) {
      return io.grpc.stub.ClientCalls.blockingUnaryCall(
              getChannel(), getAnnounceReadyMethod(), getCallOptions(), request);
    }
  }

  /**
   * A stub to allow clients to do limited synchronous rpc calls to service NodeService.
   */
  public static final class NodeServiceBlockingStub
      extends io.grpc.stub.AbstractBlockingStub<NodeServiceBlockingStub> {
    private NodeServiceBlockingStub(
        io.grpc.Channel channel, io.grpc.CallOptions callOptions) {
      super(channel, callOptions);
    }

    @java.lang.Override
    protected NodeServiceBlockingStub build(
        io.grpc.Channel channel, io.grpc.CallOptions callOptions) {
      return new NodeServiceBlockingStub(channel, callOptions);
    }

    /**
     */
    public node.Node.Ack announceReady(node.Node.ReadyMessage request) {
      return io.grpc.stub.ClientCalls.blockingUnaryCall(
          getChannel(), getAnnounceReadyMethod(), getCallOptions(), request);
    }
  }

  /**
   * A stub to allow clients to do ListenableFuture-style rpc calls to service NodeService.
   */
  public static final class NodeServiceFutureStub
      extends io.grpc.stub.AbstractFutureStub<NodeServiceFutureStub> {
    private NodeServiceFutureStub(
        io.grpc.Channel channel, io.grpc.CallOptions callOptions) {
      super(channel, callOptions);
    }

    @java.lang.Override
    protected NodeServiceFutureStub build(
        io.grpc.Channel channel, io.grpc.CallOptions callOptions) {
      return new NodeServiceFutureStub(channel, callOptions);
    }

    /**
     */
    public com.google.common.util.concurrent.ListenableFuture<node.Node.Ack> announceReady(
        node.Node.ReadyMessage request) {
      return io.grpc.stub.ClientCalls.futureUnaryCall(
          getChannel().newCall(getAnnounceReadyMethod(), getCallOptions()), request);
    }
  }

  private static final int METHODID_ANNOUNCE_READY = 0;

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
        case METHODID_ANNOUNCE_READY:
          serviceImpl.announceReady((node.Node.ReadyMessage) request,
              (io.grpc.stub.StreamObserver<node.Node.Ack>) responseObserver);
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
          getAnnounceReadyMethod(),
          io.grpc.stub.ServerCalls.asyncUnaryCall(
            new MethodHandlers<
              node.Node.ReadyMessage,
              node.Node.Ack>(
                service, METHODID_ANNOUNCE_READY)))
        .build();
  }

  private static abstract class NodeServiceBaseDescriptorSupplier
      implements io.grpc.protobuf.ProtoFileDescriptorSupplier, io.grpc.protobuf.ProtoServiceDescriptorSupplier {
    NodeServiceBaseDescriptorSupplier() {}

    @java.lang.Override
    public com.google.protobuf.Descriptors.FileDescriptor getFileDescriptor() {
      return node.Node.getDescriptor();
    }

    @java.lang.Override
    public com.google.protobuf.Descriptors.ServiceDescriptor getServiceDescriptor() {
      return getFileDescriptor().findServiceByName("NodeService");
    }
  }

  private static final class NodeServiceFileDescriptorSupplier
      extends NodeServiceBaseDescriptorSupplier {
    NodeServiceFileDescriptorSupplier() {}
  }

  private static final class NodeServiceMethodDescriptorSupplier
      extends NodeServiceBaseDescriptorSupplier
      implements io.grpc.protobuf.ProtoMethodDescriptorSupplier {
    private final java.lang.String methodName;

    NodeServiceMethodDescriptorSupplier(java.lang.String methodName) {
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
      synchronized (NodeServiceGrpc.class) {
        result = serviceDescriptor;
        if (result == null) {
          serviceDescriptor = result = io.grpc.ServiceDescriptor.newBuilder(SERVICE_NAME)
              .setSchemaDescriptor(new NodeServiceFileDescriptorSupplier())
              .addMethod(getAnnounceReadyMethod())
              .build();
        }
      }
    }
    return result;
  }
}
