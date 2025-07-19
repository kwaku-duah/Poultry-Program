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
@PreAuthorize("hasRole('FARMER')")
@RequiredArgsConstructor
public class FinancialController {

    private final FinancialService financialService;


    @PostMapping
    public ResponseEntity<Void> create(@RequestHeader("X-Id") String farmerHeaderId,
                                       @Valid @RequestBody FinancialRequestDto dto) {
        Long farmerId = Long.parseLong(farmerHeaderId);
        financialService.addFinancialRecord(farmerId, dto);
        return ResponseEntity.status(HttpStatus.CREATED).build();
    }


    @GetMapping("/{coopId}")
    public ResponseEntity<List<FinancialResponseDto>> getAll(@RequestHeader("X-Id") String farmerHeaderId,
                                                             @PathVariable Long coopId) {
        Long farmerId = Long.parseLong(farmerHeaderId);
        return ResponseEntity.ok(financialService.getFinancialRecords(coopId, farmerId));
    }


    @PutMapping("/{id}/{coopId}")
    public ResponseEntity<Void> update(@RequestHeader("X-Id") String farmerHeaderId,
                                       @PathVariable Long id,
                                       @PathVariable Long coopId,
                                       @Valid @RequestBody FinancialRequestDto dto) {
        Long farmerId = Long.parseLong(farmerHeaderId);
        financialService.updateFinancialRecord(id, coopId, farmerId, dto);
        return ResponseEntity.ok().build();
    }


    @DeleteMapping("/{id}/{coopId}")
    public ResponseEntity<Void> delete(@RequestHeader("X-Id") String farmerHeaderId,
                                       @PathVariable Long id,
                                       @PathVariable Long coopId) {
        Long farmerId = Long.parseLong(farmerHeaderId);
        financialService.deleteFinancialRecord(id, coopId, farmerId);
        return ResponseEntity.noContent().build();
    }
}

