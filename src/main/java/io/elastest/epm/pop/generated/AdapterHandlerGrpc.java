package io.elastest.epm.pop.generated;

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
public final class AdapterHandlerGrpc {

  private AdapterHandlerGrpc() {}

  public static final String SERVICE_NAME = "AdapterHandler";

  // Static method descriptors that strictly reflect the proto.
  @io.grpc.ExperimentalApi("https://github.com/grpc/grpc-java/issues/1901")
  public static final io.grpc.MethodDescriptor<AdapterProto, ResourceIdentifier>
      METHOD_REGISTER_ADAPTER =
          io.grpc.MethodDescriptor.<AdapterProto, ResourceIdentifier>newBuilder()
              .setType(io.grpc.MethodDescriptor.MethodType.UNARY)
              .setFullMethodName(generateFullMethodName("AdapterHandler", "RegisterAdapter"))
              .setRequestMarshaller(
                  io.grpc.protobuf.ProtoUtils.marshaller(AdapterProto.getDefaultInstance()))
              .setResponseMarshaller(
                  io.grpc.protobuf.ProtoUtils.marshaller(ResourceIdentifier.getDefaultInstance()))
              .build();

  @io.grpc.ExperimentalApi("https://github.com/grpc/grpc-java/issues/1901")
  public static final io.grpc.MethodDescriptor<ResourceIdentifier, Empty> METHOD_DELETE_ADAPTER =
      io.grpc.MethodDescriptor.<ResourceIdentifier, Empty>newBuilder()
          .setType(io.grpc.MethodDescriptor.MethodType.UNARY)
          .setFullMethodName(generateFullMethodName("AdapterHandler", "DeleteAdapter"))
          .setRequestMarshaller(
              io.grpc.protobuf.ProtoUtils.marshaller(ResourceIdentifier.getDefaultInstance()))
          .setResponseMarshaller(io.grpc.protobuf.ProtoUtils.marshaller(Empty.getDefaultInstance()))
          .build();

  /** Creates a new async stub that supports all call types for the service */
  public static AdapterHandlerStub newStub(io.grpc.Channel channel) {
    return new AdapterHandlerStub(channel);
  }

  /**
   * Creates a new blocking-style stub that supports unary and streaming output calls on the service
   */
  public static AdapterHandlerBlockingStub newBlockingStub(io.grpc.Channel channel) {
    return new AdapterHandlerBlockingStub(channel);
  }

  /** Creates a new ListenableFuture-style stub that supports unary calls on the service */
  public static AdapterHandlerFutureStub newFutureStub(io.grpc.Channel channel) {
    return new AdapterHandlerFutureStub(channel);
  }

  /** */
  public abstract static class AdapterHandlerImplBase implements io.grpc.BindableService {

    /** */
    public void registerAdapter(
        AdapterProto request, io.grpc.stub.StreamObserver<ResourceIdentifier> responseObserver) {
      asyncUnimplementedUnaryCall(METHOD_REGISTER_ADAPTER, responseObserver);
    }

    /** */
    public void deleteAdapter(
        ResourceIdentifier request, io.grpc.stub.StreamObserver<Empty> responseObserver) {
      asyncUnimplementedUnaryCall(METHOD_DELETE_ADAPTER, responseObserver);
    }

    @java.lang.Override
    public final io.grpc.ServerServiceDefinition bindService() {
      return io.grpc.ServerServiceDefinition.builder(getServiceDescriptor())
          .addMethod(
              METHOD_REGISTER_ADAPTER,
              asyncUnaryCall(
                  new MethodHandlers<AdapterProto, ResourceIdentifier>(
                      this, METHODID_REGISTER_ADAPTER)))
          .addMethod(
              METHOD_DELETE_ADAPTER,
              asyncUnaryCall(
                  new MethodHandlers<ResourceIdentifier, Empty>(this, METHODID_DELETE_ADAPTER)))
          .build();
    }
  }

  /** */
  public static final class AdapterHandlerStub
      extends io.grpc.stub.AbstractStub<AdapterHandlerStub> {
    private AdapterHandlerStub(io.grpc.Channel channel) {
      super(channel);
    }

    private AdapterHandlerStub(io.grpc.Channel channel, io.grpc.CallOptions callOptions) {
      super(channel, callOptions);
    }

    @java.lang.Override
    protected AdapterHandlerStub build(io.grpc.Channel channel, io.grpc.CallOptions callOptions) {
      return new AdapterHandlerStub(channel, callOptions);
    }

    /** */
    public void registerAdapter(
        AdapterProto request, io.grpc.stub.StreamObserver<ResourceIdentifier> responseObserver) {
      asyncUnaryCall(
          getChannel().newCall(METHOD_REGISTER_ADAPTER, getCallOptions()),
          request,
          responseObserver);
    }

    /** */
    public void deleteAdapter(
        ResourceIdentifier request, io.grpc.stub.StreamObserver<Empty> responseObserver) {
      asyncUnaryCall(
          getChannel().newCall(METHOD_DELETE_ADAPTER, getCallOptions()), request, responseObserver);
    }
  }

  /** */
  public static final class AdapterHandlerBlockingStub
      extends io.grpc.stub.AbstractStub<AdapterHandlerBlockingStub> {
    private AdapterHandlerBlockingStub(io.grpc.Channel channel) {
      super(channel);
    }

    private AdapterHandlerBlockingStub(io.grpc.Channel channel, io.grpc.CallOptions callOptions) {
      super(channel, callOptions);
    }

    @java.lang.Override
    protected AdapterHandlerBlockingStub build(
        io.grpc.Channel channel, io.grpc.CallOptions callOptions) {
      return new AdapterHandlerBlockingStub(channel, callOptions);
    }

    /** */
    public ResourceIdentifier registerAdapter(AdapterProto request) {
      return blockingUnaryCall(getChannel(), METHOD_REGISTER_ADAPTER, getCallOptions(), request);
    }

    /** */
    public Empty deleteAdapter(ResourceIdentifier request) {
      return blockingUnaryCall(getChannel(), METHOD_DELETE_ADAPTER, getCallOptions(), request);
    }
  }

  /** */
  public static final class AdapterHandlerFutureStub
      extends io.grpc.stub.AbstractStub<AdapterHandlerFutureStub> {
    private AdapterHandlerFutureStub(io.grpc.Channel channel) {
      super(channel);
    }

    private AdapterHandlerFutureStub(io.grpc.Channel channel, io.grpc.CallOptions callOptions) {
      super(channel, callOptions);
    }

    @java.lang.Override
    protected AdapterHandlerFutureStub build(
        io.grpc.Channel channel, io.grpc.CallOptions callOptions) {
      return new AdapterHandlerFutureStub(channel, callOptions);
    }

    /** */
    public com.google.common.util.concurrent.ListenableFuture<ResourceIdentifier> registerAdapter(
        AdapterProto request) {
      return futureUnaryCall(
          getChannel().newCall(METHOD_REGISTER_ADAPTER, getCallOptions()), request);
    }

    /** */
    public com.google.common.util.concurrent.ListenableFuture<Empty> deleteAdapter(
        ResourceIdentifier request) {
      return futureUnaryCall(
          getChannel().newCall(METHOD_DELETE_ADAPTER, getCallOptions()), request);
    }
  }

  private static final int METHODID_REGISTER_ADAPTER = 0;
  private static final int METHODID_DELETE_ADAPTER = 1;

  private static final class MethodHandlers<Req, Resp>
      implements io.grpc.stub.ServerCalls.UnaryMethod<Req, Resp>,
          io.grpc.stub.ServerCalls.ServerStreamingMethod<Req, Resp>,
          io.grpc.stub.ServerCalls.ClientStreamingMethod<Req, Resp>,
          io.grpc.stub.ServerCalls.BidiStreamingMethod<Req, Resp> {
    private final AdapterHandlerImplBase serviceImpl;
    private final int methodId;

    MethodHandlers(AdapterHandlerImplBase serviceImpl, int methodId) {
      this.serviceImpl = serviceImpl;
      this.methodId = methodId;
    }

    @java.lang.Override
    @java.lang.SuppressWarnings("unchecked")
    public void invoke(Req request, io.grpc.stub.StreamObserver<Resp> responseObserver) {
      switch (methodId) {
        case METHODID_REGISTER_ADAPTER:
          serviceImpl.registerAdapter(
              (AdapterProto) request,
              (io.grpc.stub.StreamObserver<ResourceIdentifier>) responseObserver);
          break;
        case METHODID_DELETE_ADAPTER:
          serviceImpl.deleteAdapter(
              (ResourceIdentifier) request, (io.grpc.stub.StreamObserver<Empty>) responseObserver);
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

  private static final class AdapterHandlerDescriptorSupplier
      implements io.grpc.protobuf.ProtoFileDescriptorSupplier {
    @java.lang.Override
    public com.google.protobuf.Descriptors.FileDescriptor getFileDescriptor() {
      return Client.getDescriptor();
    }
  }

  private static volatile io.grpc.ServiceDescriptor serviceDescriptor;

  public static io.grpc.ServiceDescriptor getServiceDescriptor() {
    io.grpc.ServiceDescriptor result = serviceDescriptor;
    if (result == null) {
      synchronized (AdapterHandlerGrpc.class) {
        result = serviceDescriptor;
        if (result == null) {
          serviceDescriptor =
              result =
                  io.grpc.ServiceDescriptor.newBuilder(SERVICE_NAME)
                      .setSchemaDescriptor(new AdapterHandlerDescriptorSupplier())
                      .addMethod(METHOD_REGISTER_ADAPTER)
                      .addMethod(METHOD_DELETE_ADAPTER)
                      .build();
        }
      }
    }
    return result;
  }
}
