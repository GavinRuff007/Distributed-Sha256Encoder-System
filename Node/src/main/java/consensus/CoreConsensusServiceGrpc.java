package consensus;

import static io.grpc.MethodDescriptor.generateFullMethodName;

/**
 */
@io.grpc.stub.annotations.GrpcGenerated
public final class CoreConsensusServiceGrpc {

  private CoreConsensusServiceGrpc() {}

  public static final java.lang.String SERVICE_NAME = "consensus.CoreConsensusService";

  // Static method descriptors that strictly reflect the proto.
  private static volatile io.grpc.MethodDescriptor<consensus.LeaderMessage,
      consensus.Ack> getReportLeaderMethod;

  @io.grpc.stub.annotations.RpcMethod(
      fullMethodName = SERVICE_NAME + '/' + "ReportLeader",
      requestType = consensus.LeaderMessage.class,
      responseType = consensus.Ack.class,
      methodType = io.grpc.MethodDescriptor.MethodType.UNARY)
  public static io.grpc.MethodDescriptor<consensus.LeaderMessage,
      consensus.Ack> getReportLeaderMethod() {
    io.grpc.MethodDescriptor<consensus.LeaderMessage, consensus.Ack> getReportLeaderMethod;
    if ((getReportLeaderMethod = CoreConsensusServiceGrpc.getReportLeaderMethod) == null) {
      synchronized (CoreConsensusServiceGrpc.class) {
        if ((getReportLeaderMethod = CoreConsensusServiceGrpc.getReportLeaderMethod) == null) {
          CoreConsensusServiceGrpc.getReportLeaderMethod = getReportLeaderMethod =
              io.grpc.MethodDescriptor.<consensus.LeaderMessage, consensus.Ack>newBuilder()
              .setType(io.grpc.MethodDescriptor.MethodType.UNARY)
              .setFullMethodName(generateFullMethodName(SERVICE_NAME, "ReportLeader"))
              .setSampledToLocalTracing(true)
              .setRequestMarshaller(io.grpc.protobuf.ProtoUtils.marshaller(
                  consensus.LeaderMessage.getDefaultInstance()))
              .setResponseMarshaller(io.grpc.protobuf.ProtoUtils.marshaller(
                  consensus.Ack.getDefaultInstance()))
              .setSchemaDescriptor(new CoreConsensusServiceMethodDescriptorSupplier("ReportLeader"))
              .build();
        }
      }
    }
    return getReportLeaderMethod;
  }

  private static volatile io.grpc.MethodDescriptor<consensus.PrivateKeyRequest,
      consensus.PrivateKeyResponse> getSendPrivateKeyMethod;

  @io.grpc.stub.annotations.RpcMethod(
      fullMethodName = SERVICE_NAME + '/' + "SendPrivateKey",
      requestType = consensus.PrivateKeyRequest.class,
      responseType = consensus.PrivateKeyResponse.class,
      methodType = io.grpc.MethodDescriptor.MethodType.UNARY)
  public static io.grpc.MethodDescriptor<consensus.PrivateKeyRequest,
      consensus.PrivateKeyResponse> getSendPrivateKeyMethod() {
    io.grpc.MethodDescriptor<consensus.PrivateKeyRequest, consensus.PrivateKeyResponse> getSendPrivateKeyMethod;
    if ((getSendPrivateKeyMethod = CoreConsensusServiceGrpc.getSendPrivateKeyMethod) == null) {
      synchronized (CoreConsensusServiceGrpc.class) {
        if ((getSendPrivateKeyMethod = CoreConsensusServiceGrpc.getSendPrivateKeyMethod) == null) {
          CoreConsensusServiceGrpc.getSendPrivateKeyMethod = getSendPrivateKeyMethod =
              io.grpc.MethodDescriptor.<consensus.PrivateKeyRequest, consensus.PrivateKeyResponse>newBuilder()
              .setType(io.grpc.MethodDescriptor.MethodType.UNARY)
              .setFullMethodName(generateFullMethodName(SERVICE_NAME, "SendPrivateKey"))
              .setSampledToLocalTracing(true)
              .setRequestMarshaller(io.grpc.protobuf.ProtoUtils.marshaller(
                  consensus.PrivateKeyRequest.getDefaultInstance()))
              .setResponseMarshaller(io.grpc.protobuf.ProtoUtils.marshaller(
                  consensus.PrivateKeyResponse.getDefaultInstance()))
              .setSchemaDescriptor(new CoreConsensusServiceMethodDescriptorSupplier("SendPrivateKey"))
              .build();
        }
      }
    }
    return getSendPrivateKeyMethod;
  }

  private static volatile io.grpc.MethodDescriptor<consensus.VoteReport,
      consensus.Ack> getReportVoteMethod;

  @io.grpc.stub.annotations.RpcMethod(
      fullMethodName = SERVICE_NAME + '/' + "ReportVote",
      requestType = consensus.VoteReport.class,
      responseType = consensus.Ack.class,
      methodType = io.grpc.MethodDescriptor.MethodType.UNARY)
  public static io.grpc.MethodDescriptor<consensus.VoteReport,
      consensus.Ack> getReportVoteMethod() {
    io.grpc.MethodDescriptor<consensus.VoteReport, consensus.Ack> getReportVoteMethod;
    if ((getReportVoteMethod = CoreConsensusServiceGrpc.getReportVoteMethod) == null) {
      synchronized (CoreConsensusServiceGrpc.class) {
        if ((getReportVoteMethod = CoreConsensusServiceGrpc.getReportVoteMethod) == null) {
          CoreConsensusServiceGrpc.getReportVoteMethod = getReportVoteMethod =
              io.grpc.MethodDescriptor.<consensus.VoteReport, consensus.Ack>newBuilder()
              .setType(io.grpc.MethodDescriptor.MethodType.UNARY)
              .setFullMethodName(generateFullMethodName(SERVICE_NAME, "ReportVote"))
              .setSampledToLocalTracing(true)
              .setRequestMarshaller(io.grpc.protobuf.ProtoUtils.marshaller(
                  consensus.VoteReport.getDefaultInstance()))
              .setResponseMarshaller(io.grpc.protobuf.ProtoUtils.marshaller(
                  consensus.Ack.getDefaultInstance()))
              .setSchemaDescriptor(new CoreConsensusServiceMethodDescriptorSupplier("ReportVote"))
              .build();
        }
      }
    }
    return getReportVoteMethod;
  }

  /**
   * Creates a new async stub that supports all call types for the service
   */
  public static CoreConsensusServiceStub newStub(io.grpc.Channel channel) {
    io.grpc.stub.AbstractStub.StubFactory<CoreConsensusServiceStub> factory =
      new io.grpc.stub.AbstractStub.StubFactory<CoreConsensusServiceStub>() {
        @java.lang.Override
        public CoreConsensusServiceStub newStub(io.grpc.Channel channel, io.grpc.CallOptions callOptions) {
          return new CoreConsensusServiceStub(channel, callOptions);
        }
      };
    return CoreConsensusServiceStub.newStub(factory, channel);
  }

  /**
   * Creates a new blocking-style stub that supports all types of calls on the service
   */
  public static CoreConsensusServiceBlockingV2Stub newBlockingV2Stub(
      io.grpc.Channel channel) {
    io.grpc.stub.AbstractStub.StubFactory<CoreConsensusServiceBlockingV2Stub> factory =
      new io.grpc.stub.AbstractStub.StubFactory<CoreConsensusServiceBlockingV2Stub>() {
        @java.lang.Override
        public CoreConsensusServiceBlockingV2Stub newStub(io.grpc.Channel channel, io.grpc.CallOptions callOptions) {
          return new CoreConsensusServiceBlockingV2Stub(channel, callOptions);
        }
      };
    return CoreConsensusServiceBlockingV2Stub.newStub(factory, channel);
  }

  /**
   * Creates a new blocking-style stub that supports unary and streaming output calls on the service
   */
  public static CoreConsensusServiceBlockingStub newBlockingStub(
      io.grpc.Channel channel) {
    io.grpc.stub.AbstractStub.StubFactory<CoreConsensusServiceBlockingStub> factory =
      new io.grpc.stub.AbstractStub.StubFactory<CoreConsensusServiceBlockingStub>() {
        @java.lang.Override
        public CoreConsensusServiceBlockingStub newStub(io.grpc.Channel channel, io.grpc.CallOptions callOptions) {
          return new CoreConsensusServiceBlockingStub(channel, callOptions);
        }
      };
    return CoreConsensusServiceBlockingStub.newStub(factory, channel);
  }

  /**
   * Creates a new ListenableFuture-style stub that supports unary calls on the service
   */
  public static CoreConsensusServiceFutureStub newFutureStub(
      io.grpc.Channel channel) {
    io.grpc.stub.AbstractStub.StubFactory<CoreConsensusServiceFutureStub> factory =
      new io.grpc.stub.AbstractStub.StubFactory<CoreConsensusServiceFutureStub>() {
        @java.lang.Override
        public CoreConsensusServiceFutureStub newStub(io.grpc.Channel channel, io.grpc.CallOptions callOptions) {
          return new CoreConsensusServiceFutureStub(channel, callOptions);
        }
      };
    return CoreConsensusServiceFutureStub.newStub(factory, channel);
  }

  /**
   */
  public interface AsyncService {

    /**
     * <pre>
     * هر Node به Core می‌گوید "لیدر من کیست"
     * </pre>
     */
    default void reportLeader(consensus.LeaderMessage request,
        io.grpc.stub.StreamObserver<consensus.Ack> responseObserver) {
      io.grpc.stub.ServerCalls.asyncUnimplementedUnaryCall(getReportLeaderMethod(), responseObserver);
    }

    /**
     * <pre>
     * Core می‌تواند (اختیاری) کلید خصوصی را به Node بدهد
     * </pre>
     */
    default void sendPrivateKey(consensus.PrivateKeyRequest request,
        io.grpc.stub.StreamObserver<consensus.PrivateKeyResponse> responseObserver) {
      io.grpc.stub.ServerCalls.asyncUnimplementedUnaryCall(getSendPrivateKeyMethod(), responseObserver);
    }

    /**
     * <pre>
     * هر Node رأی خودش را به Core گزارش می‌دهد
     * </pre>
     */
    default void reportVote(consensus.VoteReport request,
        io.grpc.stub.StreamObserver<consensus.Ack> responseObserver) {
      io.grpc.stub.ServerCalls.asyncUnimplementedUnaryCall(getReportVoteMethod(), responseObserver);
    }
  }

  /**
   * Base class for the server implementation of the service CoreConsensusService.
   */
  public static abstract class CoreConsensusServiceImplBase
      implements io.grpc.BindableService, AsyncService {

    @java.lang.Override public final io.grpc.ServerServiceDefinition bindService() {
      return CoreConsensusServiceGrpc.bindService(this);
    }
  }

  /**
   * A stub to allow clients to do asynchronous rpc calls to service CoreConsensusService.
   */
  public static final class CoreConsensusServiceStub
      extends io.grpc.stub.AbstractAsyncStub<CoreConsensusServiceStub> {
    private CoreConsensusServiceStub(
        io.grpc.Channel channel, io.grpc.CallOptions callOptions) {
      super(channel, callOptions);
    }

    @java.lang.Override
    protected CoreConsensusServiceStub build(
        io.grpc.Channel channel, io.grpc.CallOptions callOptions) {
      return new CoreConsensusServiceStub(channel, callOptions);
    }

    /**
     * <pre>
     * هر Node به Core می‌گوید "لیدر من کیست"
     * </pre>
     */
    public void reportLeader(consensus.LeaderMessage request,
        io.grpc.stub.StreamObserver<consensus.Ack> responseObserver) {
      io.grpc.stub.ClientCalls.asyncUnaryCall(
          getChannel().newCall(getReportLeaderMethod(), getCallOptions()), request, responseObserver);
    }

    /**
     * <pre>
     * Core می‌تواند (اختیاری) کلید خصوصی را به Node بدهد
     * </pre>
     */
    public void sendPrivateKey(consensus.PrivateKeyRequest request,
        io.grpc.stub.StreamObserver<consensus.PrivateKeyResponse> responseObserver) {
      io.grpc.stub.ClientCalls.asyncUnaryCall(
          getChannel().newCall(getSendPrivateKeyMethod(), getCallOptions()), request, responseObserver);
    }

    /**
     * <pre>
     * هر Node رأی خودش را به Core گزارش می‌دهد
     * </pre>
     */
    public void reportVote(consensus.VoteReport request,
        io.grpc.stub.StreamObserver<consensus.Ack> responseObserver) {
      io.grpc.stub.ClientCalls.asyncUnaryCall(
          getChannel().newCall(getReportVoteMethod(), getCallOptions()), request, responseObserver);
    }
  }

  /**
   * A stub to allow clients to do synchronous rpc calls to service CoreConsensusService.
   */
  public static final class CoreConsensusServiceBlockingV2Stub
      extends io.grpc.stub.AbstractBlockingStub<CoreConsensusServiceBlockingV2Stub> {
    private CoreConsensusServiceBlockingV2Stub(
        io.grpc.Channel channel, io.grpc.CallOptions callOptions) {
      super(channel, callOptions);
    }

    @java.lang.Override
    protected CoreConsensusServiceBlockingV2Stub build(
        io.grpc.Channel channel, io.grpc.CallOptions callOptions) {
      return new CoreConsensusServiceBlockingV2Stub(channel, callOptions);
    }

    /**
     * <pre>
     * هر Node به Core می‌گوید "لیدر من کیست"
     * </pre>
     */
    public consensus.Ack reportLeader(consensus.LeaderMessage request) throws io.grpc.StatusException {
      return io.grpc.stub.ClientCalls.blockingUnaryCall(
          getChannel(), getReportLeaderMethod(), getCallOptions(), request);
    }

    /**
     * <pre>
     * Core می‌تواند (اختیاری) کلید خصوصی را به Node بدهد
     * </pre>
     */
    public consensus.PrivateKeyResponse sendPrivateKey(consensus.PrivateKeyRequest request) throws io.grpc.StatusException {
      return io.grpc.stub.ClientCalls.blockingUnaryCall(
          getChannel(), getSendPrivateKeyMethod(), getCallOptions(), request);
    }

    /**
     * <pre>
     * هر Node رأی خودش را به Core گزارش می‌دهد
     * </pre>
     */
    public consensus.Ack reportVote(consensus.VoteReport request) throws io.grpc.StatusException {
      return io.grpc.stub.ClientCalls.blockingUnaryCall(
          getChannel(), getReportVoteMethod(), getCallOptions(), request);
    }
  }

  /**
   * A stub to allow clients to do limited synchronous rpc calls to service CoreConsensusService.
   */
  public static final class CoreConsensusServiceBlockingStub
      extends io.grpc.stub.AbstractBlockingStub<CoreConsensusServiceBlockingStub> {
    private CoreConsensusServiceBlockingStub(
        io.grpc.Channel channel, io.grpc.CallOptions callOptions) {
      super(channel, callOptions);
    }

    @java.lang.Override
    protected CoreConsensusServiceBlockingStub build(
        io.grpc.Channel channel, io.grpc.CallOptions callOptions) {
      return new CoreConsensusServiceBlockingStub(channel, callOptions);
    }

    /**
     * <pre>
     * هر Node به Core می‌گوید "لیدر من کیست"
     * </pre>
     */
    public consensus.Ack reportLeader(consensus.LeaderMessage request) {
      return io.grpc.stub.ClientCalls.blockingUnaryCall(
          getChannel(), getReportLeaderMethod(), getCallOptions(), request);
    }

    /**
     * <pre>
     * Core می‌تواند (اختیاری) کلید خصوصی را به Node بدهد
     * </pre>
     */
    public consensus.PrivateKeyResponse sendPrivateKey(consensus.PrivateKeyRequest request) {
      return io.grpc.stub.ClientCalls.blockingUnaryCall(
          getChannel(), getSendPrivateKeyMethod(), getCallOptions(), request);
    }

    /**
     * <pre>
     * هر Node رأی خودش را به Core گزارش می‌دهد
     * </pre>
     */
    public consensus.Ack reportVote(consensus.VoteReport request) {
      return io.grpc.stub.ClientCalls.blockingUnaryCall(
          getChannel(), getReportVoteMethod(), getCallOptions(), request);
    }
  }

  /**
   * A stub to allow clients to do ListenableFuture-style rpc calls to service CoreConsensusService.
   */
  public static final class CoreConsensusServiceFutureStub
      extends io.grpc.stub.AbstractFutureStub<CoreConsensusServiceFutureStub> {
    private CoreConsensusServiceFutureStub(
        io.grpc.Channel channel, io.grpc.CallOptions callOptions) {
      super(channel, callOptions);
    }

    @java.lang.Override
    protected CoreConsensusServiceFutureStub build(
        io.grpc.Channel channel, io.grpc.CallOptions callOptions) {
      return new CoreConsensusServiceFutureStub(channel, callOptions);
    }

    /**
     * <pre>
     * هر Node به Core می‌گوید "لیدر من کیست"
     * </pre>
     */
    public com.google.common.util.concurrent.ListenableFuture<consensus.Ack> reportLeader(
        consensus.LeaderMessage request) {
      return io.grpc.stub.ClientCalls.futureUnaryCall(
          getChannel().newCall(getReportLeaderMethod(), getCallOptions()), request);
    }

    /**
     * <pre>
     * Core می‌تواند (اختیاری) کلید خصوصی را به Node بدهد
     * </pre>
     */
    public com.google.common.util.concurrent.ListenableFuture<consensus.PrivateKeyResponse> sendPrivateKey(
        consensus.PrivateKeyRequest request) {
      return io.grpc.stub.ClientCalls.futureUnaryCall(
          getChannel().newCall(getSendPrivateKeyMethod(), getCallOptions()), request);
    }

    /**
     * <pre>
     * هر Node رأی خودش را به Core گزارش می‌دهد
     * </pre>
     */
    public com.google.common.util.concurrent.ListenableFuture<consensus.Ack> reportVote(
        consensus.VoteReport request) {
      return io.grpc.stub.ClientCalls.futureUnaryCall(
          getChannel().newCall(getReportVoteMethod(), getCallOptions()), request);
    }
  }

  private static final int METHODID_REPORT_LEADER = 0;
  private static final int METHODID_SEND_PRIVATE_KEY = 1;
  private static final int METHODID_REPORT_VOTE = 2;

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
        case METHODID_REPORT_LEADER:
          serviceImpl.reportLeader((consensus.LeaderMessage) request,
              (io.grpc.stub.StreamObserver<consensus.Ack>) responseObserver);
          break;
        case METHODID_SEND_PRIVATE_KEY:
          serviceImpl.sendPrivateKey((consensus.PrivateKeyRequest) request,
              (io.grpc.stub.StreamObserver<consensus.PrivateKeyResponse>) responseObserver);
          break;
        case METHODID_REPORT_VOTE:
          serviceImpl.reportVote((consensus.VoteReport) request,
              (io.grpc.stub.StreamObserver<consensus.Ack>) responseObserver);
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
          getReportLeaderMethod(),
          io.grpc.stub.ServerCalls.asyncUnaryCall(
            new MethodHandlers<
              consensus.LeaderMessage,
              consensus.Ack>(
                service, METHODID_REPORT_LEADER)))
        .addMethod(
          getSendPrivateKeyMethod(),
          io.grpc.stub.ServerCalls.asyncUnaryCall(
            new MethodHandlers<
              consensus.PrivateKeyRequest,
              consensus.PrivateKeyResponse>(
                service, METHODID_SEND_PRIVATE_KEY)))
        .addMethod(
          getReportVoteMethod(),
          io.grpc.stub.ServerCalls.asyncUnaryCall(
            new MethodHandlers<
              consensus.VoteReport,
              consensus.Ack>(
                service, METHODID_REPORT_VOTE)))
        .build();
  }

  private static abstract class CoreConsensusServiceBaseDescriptorSupplier
      implements io.grpc.protobuf.ProtoFileDescriptorSupplier, io.grpc.protobuf.ProtoServiceDescriptorSupplier {
    CoreConsensusServiceBaseDescriptorSupplier() {}

    @java.lang.Override
    public com.google.protobuf.Descriptors.FileDescriptor getFileDescriptor() {
      return consensus.Consensus.getDescriptor();
    }

    @java.lang.Override
    public com.google.protobuf.Descriptors.ServiceDescriptor getServiceDescriptor() {
      return getFileDescriptor().findServiceByName("CoreConsensusService");
    }
  }

  private static final class CoreConsensusServiceFileDescriptorSupplier
      extends CoreConsensusServiceBaseDescriptorSupplier {
    CoreConsensusServiceFileDescriptorSupplier() {}
  }

  private static final class CoreConsensusServiceMethodDescriptorSupplier
      extends CoreConsensusServiceBaseDescriptorSupplier
      implements io.grpc.protobuf.ProtoMethodDescriptorSupplier {
    private final java.lang.String methodName;

    CoreConsensusServiceMethodDescriptorSupplier(java.lang.String methodName) {
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
      synchronized (CoreConsensusServiceGrpc.class) {
        result = serviceDescriptor;
        if (result == null) {
          serviceDescriptor = result = io.grpc.ServiceDescriptor.newBuilder(SERVICE_NAME)
              .setSchemaDescriptor(new CoreConsensusServiceFileDescriptorSupplier())
              .addMethod(getReportLeaderMethod())
              .addMethod(getSendPrivateKeyMethod())
              .addMethod(getReportVoteMethod())
              .build();
        }
      }
    }
    return result;
  }
}
