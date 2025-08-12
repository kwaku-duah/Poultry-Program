package com.poultry.userservice.controller;

import com.poultry.userservice.Payload.ApiResponse;
import com.poultry.userservice.dto.Oauth2RresponseDto;
import com.poultry.userservice.dto.UserRequest;
import com.poultry.userservice.service.UserService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.security.oauth2.core.user.OAuth2User;
import org.springframework.web.bind.annotation.*;



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

    @PostMapping("/oauth2/signup")
    public ResponseEntity<Oauth2RresponseDto> signup(@AuthenticationPrincipal OAuth2User oAuth2User) {
        Oauth2RresponseDto response = userService.oauth2Signup(oAuth2User);
        return ResponseEntity.ok(response);
    }





}
