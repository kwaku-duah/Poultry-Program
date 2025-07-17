package com.poultry.userservice.service;

import com.poultry.userservice.dto.UserRequest;



public interface UserService {
    void addUser(UserRequest request);
    void addFarmer(Long userId);
}
