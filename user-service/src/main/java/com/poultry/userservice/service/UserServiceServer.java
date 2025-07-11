package com.poultry.userservice.service;

import com.poultry.userservice.GetUserByEmailRequest;
import com.poultry.userservice.UserResponse;
import com.poultry.userservice.UserServiceGrpc;
import com.poultry.userservice.entity.User;
import com.poultry.userservice.repository.UserRepository;
import io.grpc.stub.StreamObserver;
import org.springframework.grpc.server.service.GrpcService;
import org.springframework.stereotype.Service;

@GrpcService
public class UserServiceServer extends UserServiceGrpc.UserServiceImplBase {

    private final UserRepository userRepository;

    public UserServiceServer(UserRepository userRepository) {
        this.userRepository = userRepository;
    }

    @Override
    public void getUserByEmail(GetUserByEmailRequest request, StreamObserver<UserResponse> responseObserver) {
        User user = userRepository.findByEmail(request.getEmail())
                .orElseThrow(()-> new RuntimeException("Email not found"));

        UserResponse response = UserResponse.newBuilder()
                .setId(user.getId())
                .setEmail(user.getEmail())
                .setPassword(user.getPassword())
                .build();
        responseObserver.onNext(response);
        responseObserver.onCompleted();
    }

}
