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
@RequiredArgsConstructor
@RequestMapping("/api/v1/mortality")

@PreAuthorize("hasRole('FARMER')")
public class MortalityController {

    private final MortalityService mortalityService;



    @PostMapping
    public ResponseEntity<Void> create(@RequestHeader("X-Id") String farmerHeaderId,
                                       @Valid @RequestBody MortalityRequestDto dto) {
        Long farmerId = Long.parseLong(farmerHeaderId);
        mortalityService.addMortalityRecord(farmerId, dto);
        return ResponseEntity.status(HttpStatus.CREATED).build();
    }


    @GetMapping("/{coopId}")
    public ResponseEntity<List<MortalityResponseDto>> getAll(@RequestHeader("X-Id") String farmerHeaderId,
                                                             @PathVariable Long coopId) {
        Long farmerId = Long.parseLong(farmerHeaderId);
        return ResponseEntity.ok(mortalityService.getMortalityRecords(coopId, farmerId));
    }


    @PutMapping("/{id}/{coopId}")
    public ResponseEntity<Void> update(@RequestHeader("X-Id") String farmerHeaderId,
                                       @PathVariable Long id,
                                       @PathVariable Long coopId,
                                       @Valid @RequestBody MortalityRequestDto dto) {
        Long farmerId = Long.parseLong(farmerHeaderId);
        mortalityService.updateMortalityRecord(id, coopId, farmerId, dto);
        return ResponseEntity.ok().build();
    }


    @DeleteMapping("/{id}/{coopId}")
    public ResponseEntity<Void> delete(@RequestHeader("X-Id") String farmerHeaderId,
                                       @PathVariable Long id,
                                       @PathVariable Long coopId) {
        Long farmerId = Long.parseLong(farmerHeaderId);

        mortalityService.deleteMortalityRecord(id, coopId, farmerId);
        return ResponseEntity.noContent().build();
    }
}

