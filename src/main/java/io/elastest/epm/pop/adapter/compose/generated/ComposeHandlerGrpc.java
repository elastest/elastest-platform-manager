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
  public static final io.grpc.MethodDescriptor<FileMessage, ResourceGroupCompose>
      METHOD_UP_COMPOSE =
          io.grpc.MethodDescriptor.<FileMessage, ResourceGroupCompose>newBuilder()
              .setType(io.grpc.MethodDescriptor.MethodType.UNARY)
              .setFullMethodName(generateFullMethodName("ComposeHandler", "UpCompose"))
              .setRequestMarshaller(
                  io.grpc.protobuf.ProtoUtils.marshaller(FileMessage.getDefaultInstance()))
              .setResponseMarshaller(
                  io.grpc.protobuf.ProtoUtils.marshaller(ResourceGroupCompose.getDefaultInstance()))
              .build();

  @io.grpc.ExperimentalApi("https://github.com/grpc/grpc-java/issues/1901")
  public static final io.grpc.MethodDescriptor<ResourceIdentifier, Empty> METHOD_REMOVE_COMPOSE =
      io.grpc.MethodDescriptor.<ResourceIdentifier, Empty>newBuilder()
          .setType(io.grpc.MethodDescriptor.MethodType.UNARY)
          .setFullMethodName(generateFullMethodName("ComposeHandler", "RemoveCompose"))
          .setRequestMarshaller(
              io.grpc.protobuf.ProtoUtils.marshaller(ResourceIdentifier.getDefaultInstance()))
          .setResponseMarshaller(io.grpc.protobuf.ProtoUtils.marshaller(Empty.getDefaultInstance()))
          .build();

  @io.grpc.ExperimentalApi("https://github.com/grpc/grpc-java/issues/1901")
  public static final io.grpc.MethodDescriptor<ResourceIdentifier, Empty> METHOD_STOP_CONTAINER =
      io.grpc.MethodDescriptor.<ResourceIdentifier, Empty>newBuilder()
          .setType(io.grpc.MethodDescriptor.MethodType.UNARY)
          .setFullMethodName(generateFullMethodName("ComposeHandler", "StopContainer"))
          .setRequestMarshaller(
              io.grpc.protobuf.ProtoUtils.marshaller(ResourceIdentifier.getDefaultInstance()))
          .setResponseMarshaller(io.grpc.protobuf.ProtoUtils.marshaller(Empty.getDefaultInstance()))
          .build();

  @io.grpc.ExperimentalApi("https://github.com/grpc/grpc-java/issues/1901")
  public static final io.grpc.MethodDescriptor<ResourceIdentifier, Empty> METHOD_START_CONTAINER =
      io.grpc.MethodDescriptor.<ResourceIdentifier, Empty>newBuilder()
          .setType(io.grpc.MethodDescriptor.MethodType.UNARY)
          .setFullMethodName(generateFullMethodName("ComposeHandler", "StartContainer"))
          .setRequestMarshaller(
              io.grpc.protobuf.ProtoUtils.marshaller(ResourceIdentifier.getDefaultInstance()))
          .setResponseMarshaller(io.grpc.protobuf.ProtoUtils.marshaller(Empty.getDefaultInstance()))
          .build();

  @io.grpc.ExperimentalApi("https://github.com/grpc/grpc-java/issues/1901")
  public static final io.grpc.MethodDescriptor<DockerRuntimeMessage, StringResponse>
      METHOD_EXECUTE_COMMAND =
          io.grpc.MethodDescriptor.<DockerRuntimeMessage, StringResponse>newBuilder()
              .setType(io.grpc.MethodDescriptor.MethodType.UNARY)
              .setFullMethodName(generateFullMethodName("ComposeHandler", "ExecuteCommand"))
              .setRequestMarshaller(
                  io.grpc.protobuf.ProtoUtils.marshaller(DockerRuntimeMessage.getDefaultInstance()))
              .setResponseMarshaller(
                  io.grpc.protobuf.ProtoUtils.marshaller(StringResponse.getDefaultInstance()))
              .build();

  @io.grpc.ExperimentalApi("https://github.com/grpc/grpc-java/issues/1901")
  public static final io.grpc.MethodDescriptor<DockerRuntimeMessage, FileMessage>
      METHOD_DOWNLOAD_FILE =
          io.grpc.MethodDescriptor.<DockerRuntimeMessage, FileMessage>newBuilder()
              .setType(io.grpc.MethodDescriptor.MethodType.UNARY)
              .setFullMethodName(generateFullMethodName("ComposeHandler", "DownloadFile"))
              .setRequestMarshaller(
                  io.grpc.protobuf.ProtoUtils.marshaller(DockerRuntimeMessage.getDefaultInstance()))
              .setResponseMarshaller(
                  io.grpc.protobuf.ProtoUtils.marshaller(FileMessage.getDefaultInstance()))
              .build();

  @io.grpc.ExperimentalApi("https://github.com/grpc/grpc-java/issues/1901")
  public static final io.grpc.MethodDescriptor<DockerRuntimeMessage, Empty> METHOD_UPLOAD_FILE =
      io.grpc.MethodDescriptor.<DockerRuntimeMessage, Empty>newBuilder()
          .setType(io.grpc.MethodDescriptor.MethodType.UNARY)
          .setFullMethodName(generateFullMethodName("ComposeHandler", "UploadFile"))
          .setRequestMarshaller(
              io.grpc.protobuf.ProtoUtils.marshaller(DockerRuntimeMessage.getDefaultInstance()))
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
        FileMessage request, io.grpc.stub.StreamObserver<ResourceGroupCompose> responseObserver) {
      asyncUnimplementedUnaryCall(METHOD_UP_COMPOSE, responseObserver);
    }

    /** */
    public void removeCompose(
        ResourceIdentifier request, io.grpc.stub.StreamObserver<Empty> responseObserver) {
      asyncUnimplementedUnaryCall(METHOD_REMOVE_COMPOSE, responseObserver);
    }

    /** */
    public void stopContainer(
        ResourceIdentifier request, io.grpc.stub.StreamObserver<Empty> responseObserver) {
      asyncUnimplementedUnaryCall(METHOD_STOP_CONTAINER, responseObserver);
    }

    /** */
    public void startContainer(
        ResourceIdentifier request, io.grpc.stub.StreamObserver<Empty> responseObserver) {
      asyncUnimplementedUnaryCall(METHOD_START_CONTAINER, responseObserver);
    }

    /** */
    public void executeCommand(
        DockerRuntimeMessage request,
        io.grpc.stub.StreamObserver<StringResponse> responseObserver) {
      asyncUnimplementedUnaryCall(METHOD_EXECUTE_COMMAND, responseObserver);
    }

    /** */
    public void downloadFile(
        DockerRuntimeMessage request, io.grpc.stub.StreamObserver<FileMessage> responseObserver) {
      asyncUnimplementedUnaryCall(METHOD_DOWNLOAD_FILE, responseObserver);
    }

    /** */
    public void uploadFile(
        DockerRuntimeMessage request, io.grpc.stub.StreamObserver<Empty> responseObserver) {
      asyncUnimplementedUnaryCall(METHOD_UPLOAD_FILE, responseObserver);
    }

    @Override
    public final io.grpc.ServerServiceDefinition bindService() {
      return io.grpc.ServerServiceDefinition.builder(getServiceDescriptor())
          .addMethod(
              METHOD_UP_COMPOSE,
              asyncUnaryCall(
                  new MethodHandlers<FileMessage, ResourceGroupCompose>(this, METHODID_UP_COMPOSE)))
          .addMethod(
              METHOD_REMOVE_COMPOSE,
              asyncUnaryCall(
                  new MethodHandlers<ResourceIdentifier, Empty>(this, METHODID_REMOVE_COMPOSE)))
          .addMethod(
              METHOD_STOP_CONTAINER,
              asyncUnaryCall(
                  new MethodHandlers<ResourceIdentifier, Empty>(this, METHODID_STOP_CONTAINER)))
          .addMethod(
              METHOD_START_CONTAINER,
              asyncUnaryCall(
                  new MethodHandlers<ResourceIdentifier, Empty>(this, METHODID_START_CONTAINER)))
          .addMethod(
              METHOD_EXECUTE_COMMAND,
              asyncUnaryCall(
                  new MethodHandlers<DockerRuntimeMessage, StringResponse>(
                      this, METHODID_EXECUTE_COMMAND)))
          .addMethod(
              METHOD_DOWNLOAD_FILE,
              asyncUnaryCall(
                  new MethodHandlers<DockerRuntimeMessage, FileMessage>(
                      this, METHODID_DOWNLOAD_FILE)))
          .addMethod(
              METHOD_UPLOAD_FILE,
              asyncUnaryCall(
                  new MethodHandlers<DockerRuntimeMessage, Empty>(this, METHODID_UPLOAD_FILE)))
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
        FileMessage request, io.grpc.stub.StreamObserver<ResourceGroupCompose> responseObserver) {
      asyncUnaryCall(
          getChannel().newCall(METHOD_UP_COMPOSE, getCallOptions()), request, responseObserver);
    }

    /** */
    public void removeCompose(
        ResourceIdentifier request, io.grpc.stub.StreamObserver<Empty> responseObserver) {
      asyncUnaryCall(
          getChannel().newCall(METHOD_REMOVE_COMPOSE, getCallOptions()), request, responseObserver);
    }

    /** */
    public void stopContainer(
        ResourceIdentifier request, io.grpc.stub.StreamObserver<Empty> responseObserver) {
      asyncUnaryCall(
          getChannel().newCall(METHOD_STOP_CONTAINER, getCallOptions()), request, responseObserver);
    }

    /** */
    public void startContainer(
        ResourceIdentifier request, io.grpc.stub.StreamObserver<Empty> responseObserver) {
      asyncUnaryCall(
          getChannel().newCall(METHOD_START_CONTAINER, getCallOptions()),
          request,
          responseObserver);
    }

    /** */
    public void executeCommand(
        DockerRuntimeMessage request,
        io.grpc.stub.StreamObserver<StringResponse> responseObserver) {
      asyncUnaryCall(
          getChannel().newCall(METHOD_EXECUTE_COMMAND, getCallOptions()),
          request,
          responseObserver);
    }

    /** */
    public void downloadFile(
        DockerRuntimeMessage request, io.grpc.stub.StreamObserver<FileMessage> responseObserver) {
      asyncUnaryCall(
          getChannel().newCall(METHOD_DOWNLOAD_FILE, getCallOptions()), request, responseObserver);
    }

    /** */
    public void uploadFile(
        DockerRuntimeMessage request, io.grpc.stub.StreamObserver<Empty> responseObserver) {
      asyncUnaryCall(
          getChannel().newCall(METHOD_UPLOAD_FILE, getCallOptions()), request, responseObserver);
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
    public ResourceGroupCompose upCompose(FileMessage request) {
      return blockingUnaryCall(getChannel(), METHOD_UP_COMPOSE, getCallOptions(), request);
    }

    /** */
    public Empty removeCompose(ResourceIdentifier request) {
      return blockingUnaryCall(getChannel(), METHOD_REMOVE_COMPOSE, getCallOptions(), request);
    }

    /** */
    public Empty stopContainer(ResourceIdentifier request) {
      return blockingUnaryCall(getChannel(), METHOD_STOP_CONTAINER, getCallOptions(), request);
    }

    /** */
    public Empty startContainer(ResourceIdentifier request) {
      return blockingUnaryCall(getChannel(), METHOD_START_CONTAINER, getCallOptions(), request);
    }

    /** */
    public StringResponse executeCommand(DockerRuntimeMessage request) {
      return blockingUnaryCall(getChannel(), METHOD_EXECUTE_COMMAND, getCallOptions(), request);
    }

    /** */
    public FileMessage downloadFile(DockerRuntimeMessage request) {
      return blockingUnaryCall(getChannel(), METHOD_DOWNLOAD_FILE, getCallOptions(), request);
    }

    /** */
    public Empty uploadFile(DockerRuntimeMessage request) {
      return blockingUnaryCall(getChannel(), METHOD_UPLOAD_FILE, getCallOptions(), request);
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
        FileMessage request) {
      return futureUnaryCall(getChannel().newCall(METHOD_UP_COMPOSE, getCallOptions()), request);
    }

    /** */
    public com.google.common.util.concurrent.ListenableFuture<Empty> removeCompose(
        ResourceIdentifier request) {
      return futureUnaryCall(
          getChannel().newCall(METHOD_REMOVE_COMPOSE, getCallOptions()), request);
    }

    /** */
    public com.google.common.util.concurrent.ListenableFuture<Empty> stopContainer(
        ResourceIdentifier request) {
      return futureUnaryCall(
          getChannel().newCall(METHOD_STOP_CONTAINER, getCallOptions()), request);
    }

    /** */
    public com.google.common.util.concurrent.ListenableFuture<Empty> startContainer(
        ResourceIdentifier request) {
      return futureUnaryCall(
          getChannel().newCall(METHOD_START_CONTAINER, getCallOptions()), request);
    }

    /** */
    public com.google.common.util.concurrent.ListenableFuture<StringResponse> executeCommand(
        DockerRuntimeMessage request) {
      return futureUnaryCall(
          getChannel().newCall(METHOD_EXECUTE_COMMAND, getCallOptions()), request);
    }

    /** */
    public com.google.common.util.concurrent.ListenableFuture<FileMessage> downloadFile(
        DockerRuntimeMessage request) {
      return futureUnaryCall(getChannel().newCall(METHOD_DOWNLOAD_FILE, getCallOptions()), request);
    }

    /** */
    public com.google.common.util.concurrent.ListenableFuture<Empty> uploadFile(
        DockerRuntimeMessage request) {
      return futureUnaryCall(getChannel().newCall(METHOD_UPLOAD_FILE, getCallOptions()), request);
    }
  }

  private static final int METHODID_UP_COMPOSE = 0;
  private static final int METHODID_REMOVE_COMPOSE = 1;
  private static final int METHODID_STOP_CONTAINER = 2;
  private static final int METHODID_START_CONTAINER = 3;
  private static final int METHODID_EXECUTE_COMMAND = 4;
  private static final int METHODID_DOWNLOAD_FILE = 5;
  private static final int METHODID_UPLOAD_FILE = 6;

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
              (FileMessage) request,
              (io.grpc.stub.StreamObserver<ResourceGroupCompose>) responseObserver);
          break;
        case METHODID_REMOVE_COMPOSE:
          serviceImpl.removeCompose(
              (ResourceIdentifier) request, (io.grpc.stub.StreamObserver<Empty>) responseObserver);
          break;
        case METHODID_STOP_CONTAINER:
          serviceImpl.stopContainer(
              (ResourceIdentifier) request, (io.grpc.stub.StreamObserver<Empty>) responseObserver);
          break;
        case METHODID_START_CONTAINER:
          serviceImpl.startContainer(
              (ResourceIdentifier) request, (io.grpc.stub.StreamObserver<Empty>) responseObserver);
          break;
        case METHODID_EXECUTE_COMMAND:
          serviceImpl.executeCommand(
              (DockerRuntimeMessage) request,
              (io.grpc.stub.StreamObserver<StringResponse>) responseObserver);
          break;
        case METHODID_DOWNLOAD_FILE:
          serviceImpl.downloadFile(
              (DockerRuntimeMessage) request,
              (io.grpc.stub.StreamObserver<FileMessage>) responseObserver);
          break;
        case METHODID_UPLOAD_FILE:
          serviceImpl.uploadFile(
              (DockerRuntimeMessage) request,
              (io.grpc.stub.StreamObserver<Empty>) responseObserver);
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
                      .addMethod(METHOD_STOP_CONTAINER)
                      .addMethod(METHOD_START_CONTAINER)
                      .addMethod(METHOD_EXECUTE_COMMAND)
                      .addMethod(METHOD_DOWNLOAD_FILE)
                      .addMethod(METHOD_UPLOAD_FILE)
                      .build();
        }
      }
    }
    return result;
  }
}
