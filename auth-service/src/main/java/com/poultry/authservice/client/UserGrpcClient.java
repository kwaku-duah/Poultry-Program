package com.poultry.authservice.client;

import com.poultry.authservice.GetUserByEmailRequest;
import com.poultry.authservice.UserResponse;
import com.poultry.authservice.UserServiceGrpc;
import com.poultry.authservice.dto.UserDto;
import io.grpc.ManagedChannel;
import io.grpc.ManagedChannelBuilder;
import org.springframework.stereotype.Service;

import java.util.Set;
import java.util.stream.Collectors;

@Service
public class UserGrpcClient {


    private final UserServiceGrpc.UserServiceBlockingStub blockingStub;

    public UserGrpcClient() {
        ManagedChannel channel = ManagedChannelBuilder.forAddress("localhost", 9090)
                .usePlaintext()
                .build();
        this.blockingStub = UserServiceGrpc.newBlockingStub(channel);
    }


    public UserDto getUserByEmail(String email) {
        GetUserByEmailRequest request = GetUserByEmailRequest.newBuilder()
                .setEmail(email)
                .build();
       UserResponse userResponse = blockingStub.getUserByEmail(request);

        Set<com.poultry.authservice.Role> mappedRoles =userResponse.getRolesList()
                .stream()
                .map(role -> com.poultry.authservice.Role.valueOf(role.name()))
                .collect(Collectors.toSet());

        return new UserDto(
               userResponse.getId(), userResponse.getEmail(), userResponse.getPassword(), mappedRoles);
    }
}
