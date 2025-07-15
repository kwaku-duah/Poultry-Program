package com.poultry.farmerservice.controller;


import com.poultry.farmerservice.dto.CoopRequestDto;
import com.poultry.farmerservice.dto.CoopResponseDto;
import com.poultry.farmerservice.service.CoopService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequiredArgsConstructor
@RequestMapping("/api/v1/coops")
public class CoopController {

    private final CoopService coopService;


    @PostMapping
    public ResponseEntity<Void> createCoop(
            @RequestHeader("X-Id") String farmerId,
            @Valid @RequestBody CoopRequestDto coopRequestDto) {
        coopService.addCoop(farmerId,coopRequestDto);
        return ResponseEntity.status(HttpStatus.CREATED).build();
    }

    @GetMapping
    public ResponseEntity<List<CoopResponseDto>> getAllCoops(
            @RequestHeader("X-Id") String farmerId) {
        return ResponseEntity.ok(coopService.getAllCoops(farmerId));
    }

    @GetMapping("/{id}")
    public ResponseEntity<CoopResponseDto> getSingleCoop(
            @RequestHeader("X-Id") String farmerId,
            @PathVariable Long id) {
        return ResponseEntity.ok(coopService.specificCoop(farmerId, id));
    }

    @PutMapping("/{id}")
    public ResponseEntity<Void> updateCoop(
            @RequestHeader("X-Id") String farmerId,
            @PathVariable Long id,
            @Valid @RequestBody CoopRequestDto coopRequestDto) {
        coopService.updateCoop(id, farmerId, coopRequestDto);
        return ResponseEntity.ok().build();
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteCoop(
            @RequestHeader("X-Id") String farmerId,
            @PathVariable Long id) {
        coopService.deleteCoop(id, farmerId);
        return ResponseEntity.noContent().build();
    }
}
