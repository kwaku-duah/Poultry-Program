package com.poultry.farmerservice.controller;

import com.poultry.farmerservice.dto.FarmRequestDto;
import com.poultry.farmerservice.service.FarmService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequiredArgsConstructor
@RequestMapping("/api/v1/farmers")
public class FarmController {
    private final FarmService farmService;

    @PostMapping
    public ResponseEntity<Void> addFarmer(
            @RequestHeader("X-Id") String farmerId,
            @Valid @RequestBody FarmRequestDto farmRequestDto) {
        farmService.createFarmer(farmerId, farmRequestDto);

        return ResponseEntity.status(HttpStatus.CREATED).build();

    };
}
