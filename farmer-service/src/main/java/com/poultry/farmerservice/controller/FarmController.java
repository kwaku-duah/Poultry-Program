package com.poultry.farmerservice.controller;

import com.poultry.farmerservice.dto.FarmRequestDto;
import com.poultry.farmerservice.dto.FarmResponseDto;
import com.poultry.farmerservice.service.FarmService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequiredArgsConstructor
@RequestMapping("/api/v1/farmers")
public class FarmController {
    private final FarmService farmService;


    @PreAuthorize("hasRole('USER')")
    @PostMapping
    public ResponseEntity<Void> addFarmer(
            @RequestHeader("X-Id") String farmerId,
            @Valid @RequestBody FarmRequestDto farmRequestDto) {
        farmService.createFarmer(farmerId, farmRequestDto);

        return ResponseEntity.status(HttpStatus.CREATED).build();

    }

    @PreAuthorize("hasRole('USER')")
    @GetMapping
    public  ResponseEntity<List<FarmResponseDto>> getAllFarmers() {
        return ResponseEntity.ok(farmService.getAllFarmers());
    }

    @PreAuthorize("hasRole('USER')")
    @PutMapping
    public ResponseEntity<Void> updateFarmer(@RequestHeader("X-Id") String farmerId, @Valid @RequestBody FarmRequestDto farmRequestDto) {
        farmService.updateFarmer(farmerId, farmRequestDto);
        return ResponseEntity.ok().build();
    }

    @PreAuthorize("hasRole('USER')")
    @DeleteMapping()
    public ResponseEntity<Void> deleteFarmer(@RequestHeader("X-Id") String farmerId) {
        farmService.deleteFarmer(farmerId);
        return ResponseEntity.noContent().build();
    }
}
