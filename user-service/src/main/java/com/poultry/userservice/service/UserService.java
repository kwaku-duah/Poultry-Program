package com.poultry.userservice.service;

import com.poultry.userservice.Payload.ApiResponse;
import com.poultry.userservice.dto.UserRequest;
import com.poultry.userservice.entity.User;



public interface UserService {
    Void addUser(UserRequest request);
}
