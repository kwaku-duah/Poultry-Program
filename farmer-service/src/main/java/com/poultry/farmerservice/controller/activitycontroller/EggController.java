package com.poultry.farmerservice.controller.activitycontroller;

import com.poultry.farmerservice.dto.coopactivitydto.EggRequestDto;
import com.poultry.farmerservice.dto.coopactivitydto.EggResponseDto;
import com.poultry.farmerservice.service.coopactivity.EggService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/v1/eggs")
@PreAuthorize("hasRole('FARMER')")
@RequiredArgsConstructor
public class EggController {

    private final EggService eggService;

    @PostMapping
    public ResponseEntity<Void> create(@RequestHeader("X-Id") String farmerHeaderId,
                                       @Valid @RequestBody EggRequestDto dto) {
        Long farmerId = Long.parseLong(farmerHeaderId);
        eggService.addEggRecord(farmerId, dto);
        return ResponseEntity.status(HttpStatus.CREATED).build();
    }


    @GetMapping("/{coopId}")
    public ResponseEntity<List<EggResponseDto>> getAll(@RequestHeader("X-Id") String farmerHeaderId,
                                                       @PathVariable Long coopId) {
        Long farmerId = Long.parseLong(farmerHeaderId);
        return ResponseEntity.ok(eggService.getEggRecords(coopId, farmerId));
    }

    @PutMapping("/{id}/{coopId}")
    public ResponseEntity<Void> update(@RequestHeader("X-Id") String farmerHeaderId,
                                       @PathVariable Long id,
                                       @PathVariable Long coopId,
                                       @Valid @RequestBody EggRequestDto dto) {
        Long farmerId = Long.parseLong(farmerHeaderId);
        eggService.updateEggRecord(id, coopId, farmerId, dto);
        return ResponseEntity.ok().build();
    }


    @DeleteMapping("/{id}/{coopId}")
    public ResponseEntity<Void> delete(@RequestHeader("X-Id") String farmerHeaderId,
                                       @PathVariable Long id,
                                       @PathVariable Long coopId) {
        Long farmerId = Long.parseLong(farmerHeaderId);
        eggService.deleteEggRecord(id, coopId, farmerId);
        return ResponseEntity.noContent().build();
    }
}
