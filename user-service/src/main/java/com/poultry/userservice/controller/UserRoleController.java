package com.poultry.userservice.controller;


import com.poultry.userservice.dto.UserResponseDto;
import com.poultry.userservice.service.UserService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequiredArgsConstructor
@PreAuthorize("hasAnyRole('ADMIN', 'FARMER')")
@RequestMapping("/api/v1/vets")
public class UserRoleController {
    private final UserService userService;

    @GetMapping
    public ResponseEntity<List<UserResponseDto>> getAllVets() {
        return new ResponseEntity<>(userService.allUsers(), HttpStatus.OK);
    }
}
