package com.poultry.farmerservice.controller;


import com.poultry.farmerservice.dto.CoopRequestDto;
import com.poultry.farmerservice.dto.CoopResponseDto;
import com.poultry.farmerservice.service.CoopService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequiredArgsConstructor
@RequestMapping("/api/v1/coops")
@PreAuthorize("hasRole('FARMER')")
public class CoopController {

    private final CoopService coopService;
/**  ideally should be role FARMER, would be updated later
 * */


    @PostMapping
    public ResponseEntity<Void> createCoop(
            @RequestHeader("X-Id") String farmerHeaderId,
            @Valid @RequestBody CoopRequestDto coopRequestDto) {
        Long farmerId = Long.parseLong(farmerHeaderId);
        coopService.addCoop(farmerId,coopRequestDto);
        return ResponseEntity.status(HttpStatus.CREATED).build();
    }

    @GetMapping
    public ResponseEntity<List<CoopResponseDto>> getAllCoops(
            @RequestHeader("X-Id") String farmerHeaderId) {
        Long farmerId =Long.parseLong(farmerHeaderId);
        return ResponseEntity.ok(coopService.getAllCoops(farmerId));
    }

    @GetMapping("/{id}")
    public ResponseEntity<CoopResponseDto> getSingleCoop(
            @RequestHeader("X-Id") String farmerHeaderId,
            @PathVariable Long id) {
        Long farmerId = Long.parseLong(farmerHeaderId);
        return ResponseEntity.ok(coopService.specificCoop(farmerId, id));
    }


    @PutMapping("/{id}")
    public ResponseEntity<Void> updateCoop(
            @RequestHeader("X-Id") String farmerHeaderId,
            @PathVariable Long id,
            @Valid @RequestBody CoopRequestDto coopRequestDto) {
        Long farmerId = Long.parseLong(farmerHeaderId);
        coopService.updateCoop(id, farmerId, coopRequestDto);
        return ResponseEntity.ok().build();
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteCoop(
            @RequestHeader("X-Id") String farmerHeaderId,
            @PathVariable Long id) {
        Long farmerId = Long.parseLong(farmerHeaderId);
        coopService.deleteCoop(id, farmerId);
        return ResponseEntity.noContent().build();
    }
}
