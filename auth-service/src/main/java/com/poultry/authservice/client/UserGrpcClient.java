package com.poultry.authservice.client;

import com.poultry.authservice.GetUserByEmailRequest;
import com.poultry.authservice.UserResponse;
import com.poultry.authservice.UserServiceGrpc;
import lombok.RequiredArgsConstructor;
import net.devh.boot.grpc.client.inject.GrpcClient;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class UserGrpcClient {

    @GrpcClient("user-service")
    private UserServiceGrpc.UserServiceBlockingStub blockingStub;

    public UserResponse getUserByEmail(String email) {
        GetUserByEmailRequest request = GetUserByEmailRequest.newBuilder()
                .setEmail(email)
                .build();
        return blockingStub.getUserByEmail(request);
    }
}
