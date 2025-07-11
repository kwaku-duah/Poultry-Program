package com.poultry.userservice.service;

import com.poultry.userservice.entity.User;
import com.poultry.userservice.mapper.UserMapper;
import com.poultry.userservice.repository.UserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class UserServiceImpl extends UserService{

    private final UserRepository userRepository;
    private final UserMapper userMapper;

    @Override
    public void addUser(User user) {

    }
}
