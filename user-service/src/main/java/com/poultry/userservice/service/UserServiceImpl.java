package com.poultry.userservice.service;

import com.poultry.userservice.dto.UserRequest;
import com.poultry.userservice.entity.Role;
import com.poultry.userservice.entity.User;
import com.poultry.userservice.exception.DuplicateResourceException;
import com.poultry.userservice.mapper.UserMapper;
import com.poultry.userservice.repository.UserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.EnumSet;

@Service
@RequiredArgsConstructor
public class UserServiceImpl implements UserService{

    private final UserRepository userRepository;
    private final UserMapper userMapper;
    private final BCryptPasswordEncoder bCryptPasswordEncoder;


    @Override
    public void addUser(UserRequest userRequest) {
        if (userRepository.existsByEmail(userRequest.email())) {
            throw new DuplicateResourceException("Email " + userRequest.email() +  " already exists");
        }

        User user = userMapper.toEntity(userRequest);
        String encodedPassword = bCryptPasswordEncoder.encode(userRequest.password());
        user.setPassword(encodedPassword);
        /*all new users added to the system assume role ROLE_USER, until
        upgrade, to protect the application against injection
        * */
        user.setRoles(EnumSet.of(Role.ROLE_USER));
        userRepository.save(user);


    }

    @Override
    public void addFarmer() {

    }

    ;
}
