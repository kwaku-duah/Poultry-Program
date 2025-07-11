package com.poultry.userservice.service;

import com.poultry.userservice.repository.UserRepository;
import org.springframework.grpc.server.service.GrpcService;
import org.springframework.stereotype.Service;

@GrpcService
public class UserServiceServer {

    private final UserRepository userRepository;

    public UserServiceServer(UserRepository userRepository) {
        this.userRepository = userRepository;
    }


}
