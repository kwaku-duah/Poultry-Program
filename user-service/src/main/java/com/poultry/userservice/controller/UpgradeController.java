package com.poultry.userservice.controller;

import com.poultry.userservice.Payload.ApiResponse;
import com.poultry.userservice.service.UserService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestHeader;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequiredArgsConstructor
@RequestMapping("/api/v1/upgrade")
public class UpgradeController {
    private final UserService userService;

    @PostMapping("/farmer")
    public ResponseEntity<ApiResponse> updateFarmer(@RequestHeader("X-Id") String userIdHeader) {
        Long userId = Long.parseLong(userIdHeader);
        userService.addFarmer(userId);
        return ResponseEntity.ok(new ApiResponse("User Role Upgraded Successfully To Farmer"));
    };

    @PostMapping("/vet")
    public ResponseEntity<ApiResponse> updateVet(@RequestHeader("X-Id") String userIdHeader) {
        Long userId = Long.parseLong(userIdHeader);
        userService.addFarmer(userId);
        return ResponseEntity.ok(new ApiResponse("User Role Upgraded Successfully To Farmer"));
    };

    @PostMapping("/supplier")
    public ResponseEntity<ApiResponse> updateSupplier(@RequestHeader("X-Id") String userIdHeader) {
        Long userId = Long.parseLong(userIdHeader);
        userService.addFarmer(userId);
        return ResponseEntity.ok(new ApiResponse("User Role Upgraded Successfully To Farmer"));
    };
}
