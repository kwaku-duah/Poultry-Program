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
@PreAuthorize("hasRole('FARMER')")
public class VaccineController {

    private final VaccineService vaccineService;


    @PostMapping
    public ResponseEntity<Void> create(@RequestHeader("X-Id") String farmerHeaderId,
                                       @Valid @RequestBody VaccineRequestDto dto) {
        Long farmerId = Long.parseLong(farmerHeaderId);
        vaccineService.addVaccineRecord(farmerId, dto);
        return ResponseEntity.status(HttpStatus.CREATED).build();
    }


    @GetMapping("/{coopId}")
    public ResponseEntity<List<VaccineResponseDto>> getAll(@RequestHeader("X-Id") String farmerHeaderId,
                                                           @PathVariable Long coopId) {
        Long farmerId = Long.parseLong(farmerHeaderId);
        return ResponseEntity.ok(vaccineService.getVaccineRecords(coopId, farmerId));
    }


    @PutMapping("/{id}/{coopId}")
    public ResponseEntity<Void> update(@RequestHeader("X-Id") String farmerHeaderId,
                                       @PathVariable Long id,
                                       @PathVariable Long coopId,
                                       @Valid @RequestBody VaccineRequestDto dto) {
        Long farmerId = Long.parseLong(farmerHeaderId);
        vaccineService.updateVaccineRecord(id, coopId, farmerId, dto);
        return ResponseEntity.ok().build();
    }


    @DeleteMapping("/{id}/{coopId}")
    public ResponseEntity<Void> delete(@RequestHeader("X-Id") String farmerHeaderId,
                                       @PathVariable Long id,
                                       @PathVariable Long coopId) {
        Long farmerId = Long.parseLong(farmerHeaderId);
        vaccineService.deleteVaccineRecord(id, coopId, farmerId);
        return ResponseEntity.noContent().build();
    }
}

