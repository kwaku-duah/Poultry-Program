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




    @PreAuthorize("hasRole('FARMER')")
    @PostMapping
    public ResponseEntity<Void> addFarmer(
            @RequestHeader("X-Id") String farmerHeaderId,
            @Valid @RequestBody FarmRequestDto farmRequestDto) {
        Long farmerId = Long.parseLong(farmerHeaderId);
        farmService.createFarmer(farmerId, farmRequestDto);

        return ResponseEntity.status(HttpStatus.CREATED).build();

    }


    @PreAuthorize("hasRole('FARMER')")
    @GetMapping
    public  ResponseEntity<List<FarmResponseDto>> getAllFarmers(@RequestHeader("X-Id") String farmerHeaderId) {
        Long farmerId = Long.parseLong(farmerHeaderId);
        return ResponseEntity.ok(farmService.getAllFarmers(farmerId));
    }

    @PreAuthorize("hasAnyRole('ADMIN', 'VET')")
    @GetMapping("/all")
    public ResponseEntity<List<FarmResponseDto>> getAllFarms() {
        return ResponseEntity.ok(farmService.getAllFarms());
    }



    @PreAuthorize("hasRole('FARMER')")
    @PutMapping
    public ResponseEntity<Void> updateFarmer(@RequestHeader("X-Id") String farmerHeaderId, @Valid @RequestBody FarmRequestDto farmRequestDto) {
        Long farmerId = Long.parseLong(farmerHeaderId);
        farmService.updateFarmer(farmerId, farmRequestDto);
        return ResponseEntity.ok().build();
    }


    @PreAuthorize("hasRole('FARMER')")
    @DeleteMapping()
    public ResponseEntity<Void> deleteFarmer(@RequestHeader("X-Id") String farmerHeaderId) {
        Long farmerId = Long.parseLong(farmerHeaderId);
        farmService.deleteFarmer(farmerId);
        return ResponseEntity.noContent().build();
    }
}
