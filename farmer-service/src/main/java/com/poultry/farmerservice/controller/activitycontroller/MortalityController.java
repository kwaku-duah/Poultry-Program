package com.poultry.farmerservice.controller.activitycontroller;


import com.poultry.farmerservice.dto.coopactivitydto.MortalityRequestDto;
import com.poultry.farmerservice.dto.coopactivitydto.MortalityResponseDto;
import com.poultry.farmerservice.service.coopactivity.MortalityService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/v1/mortality")
@RequiredArgsConstructor
public class MortalityController {

    private final MortalityService mortalityService;

    @PreAuthorize("hasRole('USER')")
    @PostMapping
    public ResponseEntity<Void> create(@RequestHeader("X-Id") String farmerId,
                                       @Valid @RequestBody MortalityRequestDto dto) {
        mortalityService.addMortalityRecord(farmerId, dto);
        return ResponseEntity.status(HttpStatus.CREATED).build();
    }

    @PreAuthorize("hasRole('USER')")
    @GetMapping("/{coopId}")
    public ResponseEntity<List<MortalityResponseDto>> getAll(@RequestHeader("X-Id") String farmerId,
                                                             @PathVariable Long coopId) {
        return ResponseEntity.ok(mortalityService.getMortalityRecords(coopId, farmerId));
    }

    @PreAuthorize("hasRole('USER')")
    @PutMapping("/{id}/{coopId}")
    public ResponseEntity<Void> update(@RequestHeader("X-Id") String farmerId,
                                       @PathVariable Long id,
                                       @PathVariable Long coopId,
                                       @Valid @RequestBody MortalityRequestDto dto) {
        mortalityService.updateMortalityRecord(id, coopId, farmerId, dto);
        return ResponseEntity.ok().build();
    }

    @PreAuthorize("hasRole('USER')")
    @DeleteMapping("/{id}/{coopId}")
    public ResponseEntity<Void> delete(@RequestHeader("X-Id") String farmerId,
                                       @PathVariable Long id,
                                       @PathVariable Long coopId) {
        mortalityService.deleteMortalityRecord(id, coopId, farmerId);
        return ResponseEntity.noContent().build();
    }
}

