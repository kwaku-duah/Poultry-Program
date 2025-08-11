package com.poultry.userservice.service;

import com.poultry.userservice.dto.UserRequest;
import com.poultry.userservice.dto.UserResponseDto;

import java.util.List;


public interface UserService {
    void addUser(UserRequest request);
    void addFarmer(Long userId);
    void addVet(Long userId);
    void addSupplier(Long userId);
    List<UserResponseDto> allUsers();



}
