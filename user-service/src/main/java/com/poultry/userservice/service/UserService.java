package com.poultry.userservice.service;

import com.poultry.userservice.dto.Oauth2Access;
import com.poultry.userservice.dto.Oauth2RresponseDto;
import com.poultry.userservice.dto.UserRequest;
import com.poultry.userservice.dto.UserResponseDto;
import org.springframework.security.oauth2.core.user.OAuth2User;

import java.util.List;


public interface UserService {
    void addUser(UserRequest request);
    Oauth2RresponseDto oauth2Signup(OAuth2User oauth2User);

    void addFarmer(Long userId);
    void addVet(Long userId);
    void addSupplier(Long userId);
    List<UserResponseDto> allUsers();



}
