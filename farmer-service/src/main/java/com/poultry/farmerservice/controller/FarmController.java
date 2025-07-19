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
@PreAuthorize("hasRole('FARMER')")
public class FarmController {
    private final FarmService farmService;



    @PostMapping
    public ResponseEntity<Void> addFarmer(
            @RequestHeader("X-Id") String farmerHeaderId,
            @Valid @RequestBody FarmRequestDto farmRequestDto) {
        Long farmerId = Long.parseLong(farmerHeaderId);
        farmService.createFarmer(farmerId, farmRequestDto);

        return ResponseEntity.status(HttpStatus.CREATED).build();

    }

    @GetMapping
    public  ResponseEntity<List<FarmResponseDto>> getAllFarmers(@RequestHeader("X-Id") String farmerHeaderId) {
        Long farmerId = Long.parseLong(farmerHeaderId);
        return ResponseEntity.ok(farmService.getAllFarmers(farmerId));
    }


    @PutMapping
    public ResponseEntity<Void> updateFarmer(@RequestHeader("X-Id") String farmerHeaderId, @Valid @RequestBody FarmRequestDto farmRequestDto) {
        Long farmerId = Long.parseLong(farmerHeaderId);
        farmService.updateFarmer(farmerId, farmRequestDto);
        return ResponseEntity.ok().build();
    }


    @DeleteMapping()
    public ResponseEntity<Void> deleteFarmer(@RequestHeader("X-Id") String farmerHeaderId) {
        Long farmerId = Long.parseLong(farmerHeaderId);
        farmService.deleteFarmer(farmerId);
        return ResponseEntity.noContent().build();
    }
}
