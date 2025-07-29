package com.poultry.userservice.controller;

import com.poultry.userservice.Payload.ApiResponse;
import com.poultry.userservice.dto.UserRequest;

import com.poultry.userservice.dto.UserResponseDto;
import com.poultry.userservice.service.UserService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;


@RestController
@RequiredArgsConstructor
@RequestMapping("api/v1/users")
public class UserController {
    private final UserService userService;

    @PostMapping
    public ResponseEntity<ApiResponse> createUser(@RequestBody UserRequest request) {
        userService.addUser(request);
        return ResponseEntity.status(HttpStatus.CREATED)
                .body(new ApiResponse("User Created successfully"));
    }





}
