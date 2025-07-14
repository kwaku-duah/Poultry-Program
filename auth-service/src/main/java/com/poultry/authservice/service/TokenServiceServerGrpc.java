package com.poultry.authservice.service;

import com.poultry.token.TokenRequest;
import com.poultry.token.TokenResponse;
import com.poultry.token.TokenValidatorGrpc;
import io.grpc.Metadata;
import io.grpc.Status;

import io.grpc.stub.StreamObserver;
import org.springframework.grpc.server.service.GrpcService;


@GrpcService
public class TokenServiceServerGrpc extends TokenValidatorGrpc.TokenValidatorImplBase {

    private final JwtService jwtService;

    public TokenServiceServerGrpc(JwtService jwtService) {
        this.jwtService = jwtService;
    }

    @Override
    public void validateToken(TokenRequest request, StreamObserver<TokenResponse> responseObserver) {
        try {
            String token = request.getToken();

            if (!jwtService.isValidToken(token)) {
                respondWithError(responseObserver, Status.UNAUTHENTICATED, "Invalid token");
                return;
            }

            String id = jwtService.extractUserId(token);
            String roles = jwtService.extractRole(token);

            TokenResponse tokenResponse = TokenResponse.newBuilder()
                    .setId(id)
                    .setRoles(roles)
                    .build();

            responseObserver.onNext(tokenResponse);
            responseObserver.onCompleted();

        } catch (Exception e) {
            respondWithError(responseObserver, Status.INTERNAL, "Internal server error: " + e.getMessage());
        }
    }

    private void respondWithError(StreamObserver<?> observer, Status status, String description) {
        Metadata metadata = new Metadata();
        observer.onError(status.withDescription(description).asException(metadata));
    }
}
