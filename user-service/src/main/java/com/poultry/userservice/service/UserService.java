package com.poultry.userservice.service;

import com.poultry.userservice.entity.User;
import com.poultry.userservice.repository.UserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;


public interface UserService {
    void addUser(User user);
}
