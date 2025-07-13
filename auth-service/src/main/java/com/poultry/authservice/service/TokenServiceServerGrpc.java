package com.poultry.authservice.service;

import com.poultry.token.TokenRequest;
import com.poultry.token.TokenResponse;
import com.poultry.token.TokenValidatorGrpc;
import io.grpc.Status;
import io.grpc.stub.StreamObserver;
import org.springframework.grpc.server.service.GrpcService;

@GrpcService
public class TokenServiceServerGrpc extends TokenValidatorGrpc.TokenValidatorImplBase {
    private final JwtService jwtService;

    public TokenServiceServerGrpc(JwtService jwtService) {
        this.jwtService = jwtService;
    }

    public void validateToken(TokenRequest request, StreamObserver<TokenResponse> responseObserver) {
        String token = request.getToken();

        if (!jwtService.isValidToken(token)) {
            responseObserver.onError(Status.UNAUTHENTICATED.withDescription("Invalid token").asException());
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
    }
}
