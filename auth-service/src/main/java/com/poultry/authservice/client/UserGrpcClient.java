package com.poultry.authservice.client;

import com.poultry.authservice.GetUserByEmailRequest;
import com.poultry.authservice.UserResponse;
import com.poultry.authservice.UserServiceGrpc;
import io.grpc.ManagedChannel;
import io.grpc.ManagedChannelBuilder;
import org.springframework.stereotype.Service;


@Service
public class UserGrpcClient {


    private final UserServiceGrpc.UserServiceBlockingStub blockingStub;

    public UserGrpcClient() {
        ManagedChannel channel = ManagedChannelBuilder.forAddress("localhost", 9090)
                .usePlaintext()
                .build();
        this.blockingStub = UserServiceGrpc.newBlockingStub(channel);
    }


    public UserResponse getUserByEmail(String email) {
        GetUserByEmailRequest request = GetUserByEmailRequest.newBuilder()
                .setEmail(email)
                .build();
       return blockingStub.getUserByEmail(request);

    }
}
