package com.poultry.userservice.service;

import com.poultry.userservice.Payload.ApiResponse;
import com.poultry.userservice.dto.UserRequest;
import com.poultry.userservice.entity.User;
import com.poultry.userservice.exception.DuplicateResourceException;
import com.poultry.userservice.exception.ResourceNotFoundException;
import com.poultry.userservice.mapper.UserMapper;
import com.poultry.userservice.repository.UserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class UserServiceImpl implements UserService{

    private final UserRepository userRepository;
    private final UserMapper userMapper;

    @Override
    public void addUser(UserRequest userRequest) {
        if (userRepository.existsByEmail(userRequest.email())) {
            throw new DuplicateResourceException("Email " + userRequest.email() +  " already exists");
        }
        User user = userMapper.toEntity(userRequest);
        userRepository.save(user);

    }
}
