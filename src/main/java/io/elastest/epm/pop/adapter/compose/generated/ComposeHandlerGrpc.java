package io.elastest.epm.pop.adapter.compose.generated;

import static io.grpc.MethodDescriptor.generateFullMethodName;
import static io.grpc.stub.ClientCalls.asyncUnaryCall;
import static io.grpc.stub.ClientCalls.blockingUnaryCall;
import static io.grpc.stub.ClientCalls.futureUnaryCall;
import static io.grpc.stub.ServerCalls.asyncUnaryCall;
import static io.grpc.stub.ServerCalls.asyncUnimplementedUnaryCall;

/** */
@javax.annotation.Generated(
  value = "by gRPC proto compiler (version 1.5.0)",
  comments = "Source: client.proto"
)
public final class ComposeHandlerGrpc {

  private ComposeHandlerGrpc() {}

  public static final String SERVICE_NAME = "ComposeHandler";

  // Static method descriptors that strictly reflect the proto.
  @io.grpc.ExperimentalApi("https://github.com/grpc/grpc-java/issues/1901")
  public static final io.grpc.MethodDescriptor<ComposePackage, ResourceGroupCompose>
      METHOD_UP_COMPOSE =
          io.grpc.MethodDescriptor.<ComposePackage, ResourceGroupCompose>newBuilder()
              .setType(io.grpc.MethodDescriptor.MethodType.UNARY)
              .setFullMethodName(generateFullMethodName("ComposeHandler", "UpCompose"))
              .setRequestMarshaller(
                  io.grpc.protobuf.ProtoUtils.marshaller(ComposePackage.getDefaultInstance()))
              .setResponseMarshaller(
                  io.grpc.protobuf.ProtoUtils.marshaller(ResourceGroupCompose.getDefaultInstance()))
              .build();

  @io.grpc.ExperimentalApi("https://github.com/grpc/grpc-java/issues/1901")
  public static final io.grpc.MethodDescriptor<ComposeIdentifier, Empty> METHOD_REMOVE_COMPOSE =
      io.grpc.MethodDescriptor.<ComposeIdentifier, Empty>newBuilder()
          .setType(io.grpc.MethodDescriptor.MethodType.UNARY)
          .setFullMethodName(generateFullMethodName("ComposeHandler", "RemoveCompose"))
          .setRequestMarshaller(
              io.grpc.protobuf.ProtoUtils.marshaller(ComposeIdentifier.getDefaultInstance()))
          .setResponseMarshaller(io.grpc.protobuf.ProtoUtils.marshaller(Empty.getDefaultInstance()))
          .build();

  /** Creates a new async stub that supports all call types for the service */
  public static ComposeHandlerStub newStub(io.grpc.Channel channel) {
    return new ComposeHandlerStub(channel);
  }

  /**
   * Creates a new blocking-style stub that supports unary and streaming output calls on the service
   */
  public static ComposeHandlerBlockingStub newBlockingStub(io.grpc.Channel channel) {
    return new ComposeHandlerBlockingStub(channel);
  }

  /** Creates a new ListenableFuture-style stub that supports unary calls on the service */
  public static ComposeHandlerFutureStub newFutureStub(io.grpc.Channel channel) {
    return new ComposeHandlerFutureStub(channel);
  }

  /** */
  public abstract static class ComposeHandlerImplBase implements io.grpc.BindableService {

    /** */
    public void upCompose(
        ComposePackage request,
        io.grpc.stub.StreamObserver<ResourceGroupCompose> responseObserver) {
      asyncUnimplementedUnaryCall(METHOD_UP_COMPOSE, responseObserver);
    }

    /** */
    public void removeCompose(
        ComposeIdentifier request, io.grpc.stub.StreamObserver<Empty> responseObserver) {
      asyncUnimplementedUnaryCall(METHOD_REMOVE_COMPOSE, responseObserver);
    }

    @Override
    public final io.grpc.ServerServiceDefinition bindService() {
      return io.grpc.ServerServiceDefinition.builder(getServiceDescriptor())
          .addMethod(
              METHOD_UP_COMPOSE,
              asyncUnaryCall(
                  new MethodHandlers<ComposePackage, ResourceGroupCompose>(
                      this, METHODID_UP_COMPOSE)))
          .addMethod(
              METHOD_REMOVE_COMPOSE,
              asyncUnaryCall(
                  new MethodHandlers<ComposeIdentifier, Empty>(this, METHODID_REMOVE_COMPOSE)))
          .build();
    }
  }

  /** */
  public static final class ComposeHandlerStub
      extends io.grpc.stub.AbstractStub<ComposeHandlerStub> {
    private ComposeHandlerStub(io.grpc.Channel channel) {
      super(channel);
    }

    private ComposeHandlerStub(io.grpc.Channel channel, io.grpc.CallOptions callOptions) {
      super(channel, callOptions);
    }

    @Override
    protected ComposeHandlerStub build(io.grpc.Channel channel, io.grpc.CallOptions callOptions) {
      return new ComposeHandlerStub(channel, callOptions);
    }

    /** */
    public void upCompose(
        ComposePackage request,
        io.grpc.stub.StreamObserver<ResourceGroupCompose> responseObserver) {
      asyncUnaryCall(
          getChannel().newCall(METHOD_UP_COMPOSE, getCallOptions()), request, responseObserver);
    }

    /** */
    public void removeCompose(
        ComposeIdentifier request, io.grpc.stub.StreamObserver<Empty> responseObserver) {
      asyncUnaryCall(
          getChannel().newCall(METHOD_REMOVE_COMPOSE, getCallOptions()), request, responseObserver);
    }
  }

  /** */
  public static final class ComposeHandlerBlockingStub
      extends io.grpc.stub.AbstractStub<ComposeHandlerBlockingStub> {
    private ComposeHandlerBlockingStub(io.grpc.Channel channel) {
      super(channel);
    }

    private ComposeHandlerBlockingStub(io.grpc.Channel channel, io.grpc.CallOptions callOptions) {
      super(channel, callOptions);
    }

    @Override
    protected ComposeHandlerBlockingStub build(
        io.grpc.Channel channel, io.grpc.CallOptions callOptions) {
      return new ComposeHandlerBlockingStub(channel, callOptions);
    }

    /** */
    public ResourceGroupCompose upCompose(ComposePackage request) {
      return blockingUnaryCall(getChannel(), METHOD_UP_COMPOSE, getCallOptions(), request);
    }

    /** */
    public Empty removeCompose(ComposeIdentifier request) {
      return blockingUnaryCall(getChannel(), METHOD_REMOVE_COMPOSE, getCallOptions(), request);
    }
  }

  /** */
  public static final class ComposeHandlerFutureStub
      extends io.grpc.stub.AbstractStub<ComposeHandlerFutureStub> {
    private ComposeHandlerFutureStub(io.grpc.Channel channel) {
      super(channel);
    }

    private ComposeHandlerFutureStub(io.grpc.Channel channel, io.grpc.CallOptions callOptions) {
      super(channel, callOptions);
    }

    @Override
    protected ComposeHandlerFutureStub build(
        io.grpc.Channel channel, io.grpc.CallOptions callOptions) {
      return new ComposeHandlerFutureStub(channel, callOptions);
    }

    /** */
    public com.google.common.util.concurrent.ListenableFuture<ResourceGroupCompose> upCompose(
        ComposePackage request) {
      return futureUnaryCall(getChannel().newCall(METHOD_UP_COMPOSE, getCallOptions()), request);
    }

    /** */
    public com.google.common.util.concurrent.ListenableFuture<Empty> removeCompose(
        ComposeIdentifier request) {
      return futureUnaryCall(
          getChannel().newCall(METHOD_REMOVE_COMPOSE, getCallOptions()), request);
    }
  }

  private static final int METHODID_UP_COMPOSE = 0;
  private static final int METHODID_REMOVE_COMPOSE = 1;

  private static final class MethodHandlers<Req, Resp>
      implements io.grpc.stub.ServerCalls.UnaryMethod<Req, Resp>,
          io.grpc.stub.ServerCalls.ServerStreamingMethod<Req, Resp>,
          io.grpc.stub.ServerCalls.ClientStreamingMethod<Req, Resp>,
          io.grpc.stub.ServerCalls.BidiStreamingMethod<Req, Resp> {
    private final ComposeHandlerImplBase serviceImpl;
    private final int methodId;

    MethodHandlers(ComposeHandlerImplBase serviceImpl, int methodId) {
      this.serviceImpl = serviceImpl;
      this.methodId = methodId;
    }

    @Override
    @SuppressWarnings("unchecked")
    public void invoke(Req request, io.grpc.stub.StreamObserver<Resp> responseObserver) {
      switch (methodId) {
        case METHODID_UP_COMPOSE:
          serviceImpl.upCompose(
              (ComposePackage) request,
              (io.grpc.stub.StreamObserver<ResourceGroupCompose>) responseObserver);
          break;
        case METHODID_REMOVE_COMPOSE:
          serviceImpl.removeCompose(
              (ComposeIdentifier) request, (io.grpc.stub.StreamObserver<Empty>) responseObserver);
          break;
        default:
          throw new AssertionError();
      }
    }

    @Override
    @SuppressWarnings("unchecked")
    public io.grpc.stub.StreamObserver<Req> invoke(
        io.grpc.stub.StreamObserver<Resp> responseObserver) {
      switch (methodId) {
        default:
          throw new AssertionError();
      }
    }
  }

  private static final class ComposeHandlerDescriptorSupplier
      implements io.grpc.protobuf.ProtoFileDescriptorSupplier {
    @Override
    public com.google.protobuf.Descriptors.FileDescriptor getFileDescriptor() {
      return Client.getDescriptor();
    }
  }

  private static volatile io.grpc.ServiceDescriptor serviceDescriptor;

  public static io.grpc.ServiceDescriptor getServiceDescriptor() {
    io.grpc.ServiceDescriptor result = serviceDescriptor;
    if (result == null) {
      synchronized (ComposeHandlerGrpc.class) {
        result = serviceDescriptor;
        if (result == null) {
          serviceDescriptor =
              result =
                  io.grpc.ServiceDescriptor.newBuilder(SERVICE_NAME)
                      .setSchemaDescriptor(new ComposeHandlerDescriptorSupplier())
                      .addMethod(METHOD_UP_COMPOSE)
                      .addMethod(METHOD_REMOVE_COMPOSE)
                      .build();
        }
      }
    }
    return result;
  }
}
