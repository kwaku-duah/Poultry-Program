package com.poultry.authservice.controller;


import jakarta.servlet.http.HttpServletRequest;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/v1/test/see")
public class WorkController {

    @GetMapping()
    public ResponseEntity<String> testControl() {
        System.out.println(">>>>> /v1/test/see endpoint hit");
        return ResponseEntity.ok("Testing to see if it works, this is great");
    }

    @GetMapping("/headers")
    @PreAuthorize("hasRole('USER')")
    public ResponseEntity<String> extractHeaders(HttpServletRequest request) {
        String userId = request.getHeader("X-Id");
        String roles = request.getHeader("X-Roles");

        System.out.println("User ID from header: " + userId);
        System.out.println("Roles from header: " + roles);

        return ResponseEntity.ok(
                "User ID: " + userId + ", Roles: " + roles
        );


    }

    @GetMapping("/real")
    @PreAuthorize("hasRole('ADMIN')")
    public ResponseEntity<String> extractHeader(HttpServletRequest request) {
        String userId = request.getHeader("X-Id");
        String roles = request.getHeader("X-Roles");

        System.out.println("User ID from header: " + userId);
        System.out.println("Roles from header: " + roles);

        return ResponseEntity.ok(
                "User ID: " + userId + ", Roles: " + roles
        );

    }
}