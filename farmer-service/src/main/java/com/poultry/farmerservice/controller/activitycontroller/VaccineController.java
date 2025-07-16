package com.poultry.farmerservice.controller.activitycontroller;


import com.poultry.farmerservice.dto.coopactivitydto.VaccineRequestDto;
import com.poultry.farmerservice.dto.coopactivitydto.VaccineResponseDto;
import com.poultry.farmerservice.service.coopactivity.VaccineService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/v1/vaccines")
@RequiredArgsConstructor
public class VaccineController {

    private final VaccineService vaccineService;

    @PreAuthorize("hasRole('USER')")
    @PostMapping
    public ResponseEntity<Void> create(@RequestHeader("X-Id") String farmerId,
                                       @Valid @RequestBody VaccineRequestDto dto) {
        vaccineService.addVaccineRecord(farmerId, dto);
        return ResponseEntity.status(HttpStatus.CREATED).build();
    }

    @PreAuthorize("hasRole('USER')")
    @GetMapping("/{coopId}")
    public ResponseEntity<List<VaccineResponseDto>> getAll(@RequestHeader("X-Id") String farmerId,
                                                           @PathVariable Long coopId) {
        return ResponseEntity.ok(vaccineService.getVaccineRecords(coopId, farmerId));
    }

    @PreAuthorize("hasRole('USER')")
    @PutMapping("/{id}/{coopId}")
    public ResponseEntity<Void> update(@RequestHeader("X-Id") String farmerId,
                                       @PathVariable Long id,
                                       @PathVariable Long coopId,
                                       @Valid @RequestBody VaccineRequestDto dto) {
        vaccineService.updateVaccineRecord(id, coopId, farmerId, dto);
        return ResponseEntity.ok().build();
    }

    @PreAuthorize("hasRole('USER')")
    @DeleteMapping("/{id}/{coopId}")
    public ResponseEntity<Void> delete(@RequestHeader("X-Id") String farmerId,
                                       @PathVariable Long id,
                                       @PathVariable Long coopId) {
        vaccineService.deleteVaccineRecord(id, coopId, farmerId);
        return ResponseEntity.noContent().build();
    }
}

