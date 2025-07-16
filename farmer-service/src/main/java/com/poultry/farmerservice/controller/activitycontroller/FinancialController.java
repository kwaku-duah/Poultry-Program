package com.poultry.farmerservice.controller.activitycontroller;

import com.poultry.farmerservice.dto.coopactivitydto.FinancialRequestDto;
import com.poultry.farmerservice.dto.coopactivitydto.FinancialResponseDto;
import com.poultry.farmerservice.service.coopactivity.FinancialService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/v1/financials")
@RequiredArgsConstructor
public class FinancialController {

    private final FinancialService financialService;

    @PreAuthorize("hasRole('USER')")
    @PostMapping
    public ResponseEntity<Void> create(@RequestHeader("X-Id") String farmerId,
                                       @Valid @RequestBody FinancialRequestDto dto) {
        financialService.addFinancialRecord(farmerId, dto);
        return ResponseEntity.status(HttpStatus.CREATED).build();
    }

    @PreAuthorize("hasRole('USER')")
    @GetMapping("/{coopId}")
    public ResponseEntity<List<FinancialResponseDto>> getAll(@RequestHeader("X-Id") String farmerId,
                                                             @PathVariable Long coopId) {
        return ResponseEntity.ok(financialService.getFinancialRecords(coopId, farmerId));
    }

    @PreAuthorize("hasRole('USER')")
    @PutMapping("/{id}/{coopId}")
    public ResponseEntity<Void> update(@RequestHeader("X-Id") String farmerId,
                                       @PathVariable Long id,
                                       @PathVariable Long coopId,
                                       @Valid @RequestBody FinancialRequestDto dto) {
        financialService.updateFinancialRecord(id, coopId, farmerId, dto);
        return ResponseEntity.ok().build();
    }

    @PreAuthorize("hasRole('USER')")
    @DeleteMapping("/{id}/{coopId}")
    public ResponseEntity<Void> delete(@RequestHeader("X-Id") String farmerId,
                                       @PathVariable Long id,
                                       @PathVariable Long coopId) {
        financialService.deleteFinancialRecord(id, coopId, farmerId);
        return ResponseEntity.noContent().build();
    }
}

