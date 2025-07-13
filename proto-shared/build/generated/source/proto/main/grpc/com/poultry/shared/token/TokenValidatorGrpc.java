package com.poultry.shared.token;

import static io.grpc.MethodDescriptor.generateFullMethodName;

/**
 */
@javax.annotation.Generated(
    value = "by gRPC proto compiler (version 1.61.1)",
    comments = "Source: token_validator.proto")
@io.grpc.stub.annotations.GrpcGenerated
public final class TokenValidatorGrpc {

  private TokenValidatorGrpc() {}

  public static final java.lang.String SERVICE_NAME = "TokenValidator";

  // Static method descriptors that strictly reflect the proto.
  private static volatile io.grpc.MethodDescriptor<com.poultry.shared.token.TokenRequest,
      com.poultry.shared.token.TokenResponse> getValidateTokenMethod;

  @io.grpc.stub.annotations.RpcMethod(
      fullMethodName = SERVICE_NAME + '/' + "ValidateToken",
      requestType = com.poultry.shared.token.TokenRequest.class,
      responseType = com.poultry.shared.token.TokenResponse.class,
      methodType = io.grpc.MethodDescriptor.MethodType.UNARY)
  public static io.grpc.MethodDescriptor<com.poultry.shared.token.TokenRequest,
      com.poultry.shared.token.TokenResponse> getValidateTokenMethod() {
    io.grpc.MethodDescriptor<com.poultry.shared.token.TokenRequest, com.poultry.shared.token.TokenResponse> getValidateTokenMethod;
    if ((getValidateTokenMethod = TokenValidatorGrpc.getValidateTokenMethod) == null) {
      synchronized (TokenValidatorGrpc.class) {
        if ((getValidateTokenMethod = TokenValidatorGrpc.getValidateTokenMethod) == null) {
          TokenValidatorGrpc.getValidateTokenMethod = getValidateTokenMethod =
              io.grpc.MethodDescriptor.<com.poultry.shared.token.TokenRequest, com.poultry.shared.token.TokenResponse>newBuilder()
              .setType(io.grpc.MethodDescriptor.MethodType.UNARY)
              .setFullMethodName(generateFullMethodName(SERVICE_NAME, "ValidateToken"))
              .setSampledToLocalTracing(true)
              .setRequestMarshaller(io.grpc.protobuf.ProtoUtils.marshaller(
                  com.poultry.shared.token.TokenRequest.getDefaultInstance()))
              .setResponseMarshaller(io.grpc.protobuf.ProtoUtils.marshaller(
                  com.poultry.shared.token.TokenResponse.getDefaultInstance()))
              .setSchemaDescriptor(new TokenValidatorMethodDescriptorSupplier("ValidateToken"))
              .build();
        }
      }
    }
    return getValidateTokenMethod;
  }

  /**
   * Creates a new async stub that supports all call types for the service
   */
  public static TokenValidatorStub newStub(io.grpc.Channel channel) {
    io.grpc.stub.AbstractStub.StubFactory<TokenValidatorStub> factory =
      new io.grpc.stub.AbstractStub.StubFactory<TokenValidatorStub>() {
        @java.lang.Override
        public TokenValidatorStub newStub(io.grpc.Channel channel, io.grpc.CallOptions callOptions) {
          return new TokenValidatorStub(channel, callOptions);
        }
      };
    return TokenValidatorStub.newStub(factory, channel);
  }

  /**
   * Creates a new blocking-style stub that supports unary and streaming output calls on the service
   */
  public static TokenValidatorBlockingStub newBlockingStub(
      io.grpc.Channel channel) {
    io.grpc.stub.AbstractStub.StubFactory<TokenValidatorBlockingStub> factory =
      new io.grpc.stub.AbstractStub.StubFactory<TokenValidatorBlockingStub>() {
        @java.lang.Override
        public TokenValidatorBlockingStub newStub(io.grpc.Channel channel, io.grpc.CallOptions callOptions) {
          return new TokenValidatorBlockingStub(channel, callOptions);
        }
      };
    return TokenValidatorBlockingStub.newStub(factory, channel);
  }

  /**
   * Creates a new ListenableFuture-style stub that supports unary calls on the service
   */
  public static TokenValidatorFutureStub newFutureStub(
      io.grpc.Channel channel) {
    io.grpc.stub.AbstractStub.StubFactory<TokenValidatorFutureStub> factory =
      new io.grpc.stub.AbstractStub.StubFactory<TokenValidatorFutureStub>() {
        @java.lang.Override
        public TokenValidatorFutureStub newStub(io.grpc.Channel channel, io.grpc.CallOptions callOptions) {
          return new TokenValidatorFutureStub(channel, callOptions);
        }
      };
    return TokenValidatorFutureStub.newStub(factory, channel);
  }

  /**
   */
  public interface AsyncService {

    /**
     */
    default void validateToken(com.poultry.shared.token.TokenRequest request,
        io.grpc.stub.StreamObserver<com.poultry.shared.token.TokenResponse> responseObserver) {
      io.grpc.stub.ServerCalls.asyncUnimplementedUnaryCall(getValidateTokenMethod(), responseObserver);
    }
  }

  /**
   * Base class for the server implementation of the service TokenValidator.
   */
  public static abstract class TokenValidatorImplBase
      implements io.grpc.BindableService, AsyncService {

    @java.lang.Override public final io.grpc.ServerServiceDefinition bindService() {
      return TokenValidatorGrpc.bindService(this);
    }
  }

  /**
   * A stub to allow clients to do asynchronous rpc calls to service TokenValidator.
   */
  public static final class TokenValidatorStub
      extends io.grpc.stub.AbstractAsyncStub<TokenValidatorStub> {
    private TokenValidatorStub(
        io.grpc.Channel channel, io.grpc.CallOptions callOptions) {
      super(channel, callOptions);
    }

    @java.lang.Override
    protected TokenValidatorStub build(
        io.grpc.Channel channel, io.grpc.CallOptions callOptions) {
      return new TokenValidatorStub(channel, callOptions);
    }

    /**
     */
    public void validateToken(com.poultry.shared.token.TokenRequest request,
        io.grpc.stub.StreamObserver<com.poultry.shared.token.TokenResponse> responseObserver) {
      io.grpc.stub.ClientCalls.asyncUnaryCall(
          getChannel().newCall(getValidateTokenMethod(), getCallOptions()), request, responseObserver);
    }
  }

  /**
   * A stub to allow clients to do synchronous rpc calls to service TokenValidator.
   */
  public static final class TokenValidatorBlockingStub
      extends io.grpc.stub.AbstractBlockingStub<TokenValidatorBlockingStub> {
    private TokenValidatorBlockingStub(
        io.grpc.Channel channel, io.grpc.CallOptions callOptions) {
      super(channel, callOptions);
    }

    @java.lang.Override
    protected TokenValidatorBlockingStub build(
        io.grpc.Channel channel, io.grpc.CallOptions callOptions) {
      return new TokenValidatorBlockingStub(channel, callOptions);
    }

    /**
     */
    public com.poultry.shared.token.TokenResponse validateToken(com.poultry.shared.token.TokenRequest request) {
      return io.grpc.stub.ClientCalls.blockingUnaryCall(
          getChannel(), getValidateTokenMethod(), getCallOptions(), request);
    }
  }

  /**
   * A stub to allow clients to do ListenableFuture-style rpc calls to service TokenValidator.
   */
  public static final class TokenValidatorFutureStub
      extends io.grpc.stub.AbstractFutureStub<TokenValidatorFutureStub> {
    private TokenValidatorFutureStub(
        io.grpc.Channel channel, io.grpc.CallOptions callOptions) {
      super(channel, callOptions);
    }

    @java.lang.Override
    protected TokenValidatorFutureStub build(
        io.grpc.Channel channel, io.grpc.CallOptions callOptions) {
      return new TokenValidatorFutureStub(channel, callOptions);
    }

    /**
     */
    public com.google.common.util.concurrent.ListenableFuture<com.poultry.shared.token.TokenResponse> validateToken(
        com.poultry.shared.token.TokenRequest request) {
      return io.grpc.stub.ClientCalls.futureUnaryCall(
          getChannel().newCall(getValidateTokenMethod(), getCallOptions()), request);
    }
  }

  private static final int METHODID_VALIDATE_TOKEN = 0;

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
        case METHODID_VALIDATE_TOKEN:
          serviceImpl.validateToken((com.poultry.shared.token.TokenRequest) request,
              (io.grpc.stub.StreamObserver<com.poultry.shared.token.TokenResponse>) responseObserver);
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
          getValidateTokenMethod(),
          io.grpc.stub.ServerCalls.asyncUnaryCall(
            new MethodHandlers<
              com.poultry.shared.token.TokenRequest,
              com.poultry.shared.token.TokenResponse>(
                service, METHODID_VALIDATE_TOKEN)))
        .build();
  }

  private static abstract class TokenValidatorBaseDescriptorSupplier
      implements io.grpc.protobuf.ProtoFileDescriptorSupplier, io.grpc.protobuf.ProtoServiceDescriptorSupplier {
    TokenValidatorBaseDescriptorSupplier() {}

    @java.lang.Override
    public com.google.protobuf.Descriptors.FileDescriptor getFileDescriptor() {
      return com.poultry.shared.token.TokenProto.getDescriptor();
    }

    @java.lang.Override
    public com.google.protobuf.Descriptors.ServiceDescriptor getServiceDescriptor() {
      return getFileDescriptor().findServiceByName("TokenValidator");
    }
  }

  private static final class TokenValidatorFileDescriptorSupplier
      extends TokenValidatorBaseDescriptorSupplier {
    TokenValidatorFileDescriptorSupplier() {}
  }

  private static final class TokenValidatorMethodDescriptorSupplier
      extends TokenValidatorBaseDescriptorSupplier
      implements io.grpc.protobuf.ProtoMethodDescriptorSupplier {
    private final java.lang.String methodName;

    TokenValidatorMethodDescriptorSupplier(java.lang.String methodName) {
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
      synchronized (TokenValidatorGrpc.class) {
        result = serviceDescriptor;
        if (result == null) {
          serviceDescriptor = result = io.grpc.ServiceDescriptor.newBuilder(SERVICE_NAME)
              .setSchemaDescriptor(new TokenValidatorFileDescriptorSupplier())
              .addMethod(getValidateTokenMethod())
              .build();
        }
      }
    }
    return result;
  }
}
