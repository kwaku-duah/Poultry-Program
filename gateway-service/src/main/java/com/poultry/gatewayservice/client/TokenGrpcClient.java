package com.poultry.gatewayservice.client;



import com.poultry.token.TokenRequest;
import com.poultry.token.TokenResponse;
import com.poultry.token.TokenValidatorGrpc;
import io.grpc.ManagedChannel;
import io.grpc.ManagedChannelBuilder;
import org.springframework.stereotype.Service;

import java.util.Map;

@Service
public class TokenGrpcClient {
    private final TokenValidatorGrpc.TokenValidatorBlockingStub blockingStub;

    public TokenGrpcClient() {
        ManagedChannel channel = ManagedChannelBuilder.forAddress("localhost",9090)
                .usePlaintext()
                .build();
        this.blockingStub = TokenValidatorGrpc.newBlockingStub(channel);
    }

    public Map< String, String> validateToken(String token) {
        TokenRequest request = TokenRequest.newBuilder().setToken(token).build();
        TokenResponse response = blockingStub.validateToken(request);
        return Map.of(
                "id",response.getId(),
                "roles",response.getRoles()
        );
    }
}
