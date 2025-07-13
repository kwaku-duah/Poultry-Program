package com.poultry.userservice.service;

import com.poultry.userservice.GetUserByEmailRequest;
import com.poultry.userservice.Role;
import com.poultry.userservice.UserResponse;
import com.poultry.userservice.UserServiceGrpc;
import com.poultry.userservice.entity.User;
import com.poultry.userservice.exception.ResourceNotFoundException;
import com.poultry.userservice.repository.UserRepository;
import io.grpc.stub.StreamObserver;
import org.springframework.grpc.server.service.GrpcService;

import java.util.stream.Collectors;

@GrpcService
public class UserServiceServer extends UserServiceGrpc.UserServiceImplBase {

    private final UserRepository userRepository;

    public UserServiceServer(UserRepository userRepository) {
        this.userRepository = userRepository;
    }

    @Override
    public void getUserByEmail(GetUserByEmailRequest request, StreamObserver<UserResponse> responseObserver) {
        User user = userRepository.findByEmail(request.getEmail())
                .orElseThrow(()-> new ResourceNotFoundException("Email " + request.getEmail() + " not found"));

        UserResponse response = UserResponse.newBuilder()
                .setId(user.getId())
                .setFullName(user.getFullName())
                .setPassword(user.getPassword())
                .setEmail(user.getEmail())
                .addAllRoles(
                        user.getRoles().stream()
                                .map(role -> Role.valueOf(role.name()))
                                .collect(Collectors.toList())
                )
                .build();
        responseObserver.onNext(response);
        responseObserver.onCompleted();
    }

}
