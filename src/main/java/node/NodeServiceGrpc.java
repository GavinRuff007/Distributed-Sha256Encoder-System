package node;

import static io.grpc.MethodDescriptor.generateFullMethodName;

/**
 */
@io.grpc.stub.annotations.GrpcGenerated
public final class NodeServiceGrpc {

  private NodeServiceGrpc() {}

  public static final java.lang.String SERVICE_NAME = "node.NodeService";

  // Static method descriptors that strictly reflect the proto.
  private static volatile io.grpc.MethodDescriptor<node.ReadyMessage,
      node.Ack> getAnnounceReadyMethod;

  @io.grpc.stub.annotations.RpcMethod(
      fullMethodName = SERVICE_NAME + '/' + "AnnounceReady",
      requestType = node.ReadyMessage.class,
      responseType = node.Ack.class,
      methodType = io.grpc.MethodDescriptor.MethodType.UNARY)
  public static io.grpc.MethodDescriptor<node.ReadyMessage,
      node.Ack> getAnnounceReadyMethod() {
    io.grpc.MethodDescriptor<node.ReadyMessage, node.Ack> getAnnounceReadyMethod;
    if ((getAnnounceReadyMethod = NodeServiceGrpc.getAnnounceReadyMethod) == null) {
      synchronized (NodeServiceGrpc.class) {
        if ((getAnnounceReadyMethod = NodeServiceGrpc.getAnnounceReadyMethod) == null) {
          NodeServiceGrpc.getAnnounceReadyMethod = getAnnounceReadyMethod =
              io.grpc.MethodDescriptor.<node.ReadyMessage, node.Ack>newBuilder()
              .setType(io.grpc.MethodDescriptor.MethodType.UNARY)
              .setFullMethodName(generateFullMethodName(SERVICE_NAME, "AnnounceReady"))
              .setSampledToLocalTracing(true)
              .setRequestMarshaller(io.grpc.protobuf.ProtoUtils.marshaller(
                  node.ReadyMessage.getDefaultInstance()))
              .setResponseMarshaller(io.grpc.protobuf.ProtoUtils.marshaller(
                  node.Ack.getDefaultInstance()))
              .setSchemaDescriptor(new NodeServiceMethodDescriptorSupplier("AnnounceReady"))
              .build();
        }
      }
    }
    return getAnnounceReadyMethod;
  }

  private static volatile io.grpc.MethodDescriptor<node.LeaderMessage,
      node.Ack> getAnnounceLeaderMethod;

  @io.grpc.stub.annotations.RpcMethod(
      fullMethodName = SERVICE_NAME + '/' + "AnnounceLeader",
      requestType = node.LeaderMessage.class,
      responseType = node.Ack.class,
      methodType = io.grpc.MethodDescriptor.MethodType.UNARY)
  public static io.grpc.MethodDescriptor<node.LeaderMessage,
      node.Ack> getAnnounceLeaderMethod() {
    io.grpc.MethodDescriptor<node.LeaderMessage, node.Ack> getAnnounceLeaderMethod;
    if ((getAnnounceLeaderMethod = NodeServiceGrpc.getAnnounceLeaderMethod) == null) {
      synchronized (NodeServiceGrpc.class) {
        if ((getAnnounceLeaderMethod = NodeServiceGrpc.getAnnounceLeaderMethod) == null) {
          NodeServiceGrpc.getAnnounceLeaderMethod = getAnnounceLeaderMethod =
              io.grpc.MethodDescriptor.<node.LeaderMessage, node.Ack>newBuilder()
              .setType(io.grpc.MethodDescriptor.MethodType.UNARY)
              .setFullMethodName(generateFullMethodName(SERVICE_NAME, "AnnounceLeader"))
              .setSampledToLocalTracing(true)
              .setRequestMarshaller(io.grpc.protobuf.ProtoUtils.marshaller(
                  node.LeaderMessage.getDefaultInstance()))
              .setResponseMarshaller(io.grpc.protobuf.ProtoUtils.marshaller(
                  node.Ack.getDefaultInstance()))
              .setSchemaDescriptor(new NodeServiceMethodDescriptorSupplier("AnnounceLeader"))
              .build();
        }
      }
    }
    return getAnnounceLeaderMethod;
  }

  private static volatile io.grpc.MethodDescriptor<node.VoteRequest,
      node.VoteResponse> getRequestVoteMethod;

  @io.grpc.stub.annotations.RpcMethod(
      fullMethodName = SERVICE_NAME + '/' + "RequestVote",
      requestType = node.VoteRequest.class,
      responseType = node.VoteResponse.class,
      methodType = io.grpc.MethodDescriptor.MethodType.UNARY)
  public static io.grpc.MethodDescriptor<node.VoteRequest,
      node.VoteResponse> getRequestVoteMethod() {
    io.grpc.MethodDescriptor<node.VoteRequest, node.VoteResponse> getRequestVoteMethod;
    if ((getRequestVoteMethod = NodeServiceGrpc.getRequestVoteMethod) == null) {
      synchronized (NodeServiceGrpc.class) {
        if ((getRequestVoteMethod = NodeServiceGrpc.getRequestVoteMethod) == null) {
          NodeServiceGrpc.getRequestVoteMethod = getRequestVoteMethod =
              io.grpc.MethodDescriptor.<node.VoteRequest, node.VoteResponse>newBuilder()
              .setType(io.grpc.MethodDescriptor.MethodType.UNARY)
              .setFullMethodName(generateFullMethodName(SERVICE_NAME, "RequestVote"))
              .setSampledToLocalTracing(true)
              .setRequestMarshaller(io.grpc.protobuf.ProtoUtils.marshaller(
                  node.VoteRequest.getDefaultInstance()))
              .setResponseMarshaller(io.grpc.protobuf.ProtoUtils.marshaller(
                  node.VoteResponse.getDefaultInstance()))
              .setSchemaDescriptor(new NodeServiceMethodDescriptorSupplier("RequestVote"))
              .build();
        }
      }
    }
    return getRequestVoteMethod;
  }

  private static volatile io.grpc.MethodDescriptor<node.CandidateInfo,
      node.CandidateInfo> getExchangeCandidateInfoMethod;

  @io.grpc.stub.annotations.RpcMethod(
      fullMethodName = SERVICE_NAME + '/' + "ExchangeCandidateInfo",
      requestType = node.CandidateInfo.class,
      responseType = node.CandidateInfo.class,
      methodType = io.grpc.MethodDescriptor.MethodType.UNARY)
  public static io.grpc.MethodDescriptor<node.CandidateInfo,
      node.CandidateInfo> getExchangeCandidateInfoMethod() {
    io.grpc.MethodDescriptor<node.CandidateInfo, node.CandidateInfo> getExchangeCandidateInfoMethod;
    if ((getExchangeCandidateInfoMethod = NodeServiceGrpc.getExchangeCandidateInfoMethod) == null) {
      synchronized (NodeServiceGrpc.class) {
        if ((getExchangeCandidateInfoMethod = NodeServiceGrpc.getExchangeCandidateInfoMethod) == null) {
          NodeServiceGrpc.getExchangeCandidateInfoMethod = getExchangeCandidateInfoMethod =
              io.grpc.MethodDescriptor.<node.CandidateInfo, node.CandidateInfo>newBuilder()
              .setType(io.grpc.MethodDescriptor.MethodType.UNARY)
              .setFullMethodName(generateFullMethodName(SERVICE_NAME, "ExchangeCandidateInfo"))
              .setSampledToLocalTracing(true)
              .setRequestMarshaller(io.grpc.protobuf.ProtoUtils.marshaller(
                  node.CandidateInfo.getDefaultInstance()))
              .setResponseMarshaller(io.grpc.protobuf.ProtoUtils.marshaller(
                  node.CandidateInfo.getDefaultInstance()))
              .setSchemaDescriptor(new NodeServiceMethodDescriptorSupplier("ExchangeCandidateInfo"))
              .build();
        }
      }
    }
    return getExchangeCandidateInfoMethod;
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
    default void announceReady(node.ReadyMessage request,
        io.grpc.stub.StreamObserver<node.Ack> responseObserver) {
      io.grpc.stub.ServerCalls.asyncUnimplementedUnaryCall(getAnnounceReadyMethod(), responseObserver);
    }

    /**
     */
    default void announceLeader(node.LeaderMessage request,
        io.grpc.stub.StreamObserver<node.Ack> responseObserver) {
      io.grpc.stub.ServerCalls.asyncUnimplementedUnaryCall(getAnnounceLeaderMethod(), responseObserver);
    }

    /**
     */
    default void requestVote(node.VoteRequest request,
        io.grpc.stub.StreamObserver<node.VoteResponse> responseObserver) {
      io.grpc.stub.ServerCalls.asyncUnimplementedUnaryCall(getRequestVoteMethod(), responseObserver);
    }

    /**
     * <pre>
     * متد جدید
     * </pre>
     */
    default void exchangeCandidateInfo(node.CandidateInfo request,
        io.grpc.stub.StreamObserver<node.CandidateInfo> responseObserver) {
      io.grpc.stub.ServerCalls.asyncUnimplementedUnaryCall(getExchangeCandidateInfoMethod(), responseObserver);
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
    public void announceReady(node.ReadyMessage request,
        io.grpc.stub.StreamObserver<node.Ack> responseObserver) {
      io.grpc.stub.ClientCalls.asyncUnaryCall(
          getChannel().newCall(getAnnounceReadyMethod(), getCallOptions()), request, responseObserver);
    }

    /**
     */
    public void announceLeader(node.LeaderMessage request,
        io.grpc.stub.StreamObserver<node.Ack> responseObserver) {
      io.grpc.stub.ClientCalls.asyncUnaryCall(
          getChannel().newCall(getAnnounceLeaderMethod(), getCallOptions()), request, responseObserver);
    }

    /**
     */
    public void requestVote(node.VoteRequest request,
        io.grpc.stub.StreamObserver<node.VoteResponse> responseObserver) {
      io.grpc.stub.ClientCalls.asyncUnaryCall(
          getChannel().newCall(getRequestVoteMethod(), getCallOptions()), request, responseObserver);
    }

    /**
     * <pre>
     * متد جدید
     * </pre>
     */
    public void exchangeCandidateInfo(node.CandidateInfo request,
        io.grpc.stub.StreamObserver<node.CandidateInfo> responseObserver) {
      io.grpc.stub.ClientCalls.asyncUnaryCall(
          getChannel().newCall(getExchangeCandidateInfoMethod(), getCallOptions()), request, responseObserver);
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
    public node.Ack announceReady(node.ReadyMessage request) throws io.grpc.StatusException {
      return io.grpc.stub.ClientCalls.blockingUnaryCall(
          getChannel(), getAnnounceReadyMethod(), getCallOptions(), request);
    }

    /**
     */
    public node.Ack announceLeader(node.LeaderMessage request) throws io.grpc.StatusException {
      return io.grpc.stub.ClientCalls.blockingUnaryCall(
          getChannel(), getAnnounceLeaderMethod(), getCallOptions(), request);
    }

    /**
     */
    public node.VoteResponse requestVote(node.VoteRequest request) throws io.grpc.StatusException {
      return io.grpc.stub.ClientCalls.blockingUnaryCall(
          getChannel(), getRequestVoteMethod(), getCallOptions(), request);
    }

    /**
     * <pre>
     * متد جدید
     * </pre>
     */
    public node.CandidateInfo exchangeCandidateInfo(node.CandidateInfo request) throws io.grpc.StatusException {
      return io.grpc.stub.ClientCalls.blockingUnaryCall(
          getChannel(), getExchangeCandidateInfoMethod(), getCallOptions(), request);
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
    public node.Ack announceReady(node.ReadyMessage request) {
      return io.grpc.stub.ClientCalls.blockingUnaryCall(
          getChannel(), getAnnounceReadyMethod(), getCallOptions(), request);
    }

    /**
     */
    public node.Ack announceLeader(node.LeaderMessage request) {
      return io.grpc.stub.ClientCalls.blockingUnaryCall(
          getChannel(), getAnnounceLeaderMethod(), getCallOptions(), request);
    }

    /**
     */
    public node.VoteResponse requestVote(node.VoteRequest request) {
      return io.grpc.stub.ClientCalls.blockingUnaryCall(
          getChannel(), getRequestVoteMethod(), getCallOptions(), request);
    }

    /**
     * <pre>
     * متد جدید
     * </pre>
     */
    public node.CandidateInfo exchangeCandidateInfo(node.CandidateInfo request) {
      return io.grpc.stub.ClientCalls.blockingUnaryCall(
          getChannel(), getExchangeCandidateInfoMethod(), getCallOptions(), request);
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
    public com.google.common.util.concurrent.ListenableFuture<node.Ack> announceReady(
        node.ReadyMessage request) {
      return io.grpc.stub.ClientCalls.futureUnaryCall(
          getChannel().newCall(getAnnounceReadyMethod(), getCallOptions()), request);
    }

    /**
     */
    public com.google.common.util.concurrent.ListenableFuture<node.Ack> announceLeader(
        node.LeaderMessage request) {
      return io.grpc.stub.ClientCalls.futureUnaryCall(
          getChannel().newCall(getAnnounceLeaderMethod(), getCallOptions()), request);
    }

    /**
     */
    public com.google.common.util.concurrent.ListenableFuture<node.VoteResponse> requestVote(
        node.VoteRequest request) {
      return io.grpc.stub.ClientCalls.futureUnaryCall(
          getChannel().newCall(getRequestVoteMethod(), getCallOptions()), request);
    }

    /**
     * <pre>
     * متد جدید
     * </pre>
     */
    public com.google.common.util.concurrent.ListenableFuture<node.CandidateInfo> exchangeCandidateInfo(
        node.CandidateInfo request) {
      return io.grpc.stub.ClientCalls.futureUnaryCall(
          getChannel().newCall(getExchangeCandidateInfoMethod(), getCallOptions()), request);
    }
  }

  private static final int METHODID_ANNOUNCE_READY = 0;
  private static final int METHODID_ANNOUNCE_LEADER = 1;
  private static final int METHODID_REQUEST_VOTE = 2;
  private static final int METHODID_EXCHANGE_CANDIDATE_INFO = 3;

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
          serviceImpl.announceReady((node.ReadyMessage) request,
              (io.grpc.stub.StreamObserver<node.Ack>) responseObserver);
          break;
        case METHODID_ANNOUNCE_LEADER:
          serviceImpl.announceLeader((node.LeaderMessage) request,
              (io.grpc.stub.StreamObserver<node.Ack>) responseObserver);
          break;
        case METHODID_REQUEST_VOTE:
          serviceImpl.requestVote((node.VoteRequest) request,
              (io.grpc.stub.StreamObserver<node.VoteResponse>) responseObserver);
          break;
        case METHODID_EXCHANGE_CANDIDATE_INFO:
          serviceImpl.exchangeCandidateInfo((node.CandidateInfo) request,
              (io.grpc.stub.StreamObserver<node.CandidateInfo>) responseObserver);
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
              node.ReadyMessage,
              node.Ack>(
                service, METHODID_ANNOUNCE_READY)))
        .addMethod(
          getAnnounceLeaderMethod(),
          io.grpc.stub.ServerCalls.asyncUnaryCall(
            new MethodHandlers<
              node.LeaderMessage,
              node.Ack>(
                service, METHODID_ANNOUNCE_LEADER)))
        .addMethod(
          getRequestVoteMethod(),
          io.grpc.stub.ServerCalls.asyncUnaryCall(
            new MethodHandlers<
              node.VoteRequest,
              node.VoteResponse>(
                service, METHODID_REQUEST_VOTE)))
        .addMethod(
          getExchangeCandidateInfoMethod(),
          io.grpc.stub.ServerCalls.asyncUnaryCall(
            new MethodHandlers<
              node.CandidateInfo,
              node.CandidateInfo>(
                service, METHODID_EXCHANGE_CANDIDATE_INFO)))
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
              .addMethod(getAnnounceLeaderMethod())
              .addMethod(getRequestVoteMethod())
              .addMethod(getExchangeCandidateInfoMethod())
              .build();
        }
      }
    }
    return result;
  }
}
