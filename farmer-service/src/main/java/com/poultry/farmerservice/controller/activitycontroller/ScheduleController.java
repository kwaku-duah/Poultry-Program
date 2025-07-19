package com.poultry.farmerservice.controller.activitycontroller;


import com.poultry.farmerservice.dto.coopactivitydto.ScheduleRequestDto;
import com.poultry.farmerservice.dto.coopactivitydto.ScheduleResponseDto;
import com.poultry.farmerservice.service.coopactivity.ScheduleService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequiredArgsConstructor
@RequestMapping("/api/v1/schedules")
@PreAuthorize("hasRole('FARMER')")
public class ScheduleController {

    private final ScheduleService scheduleService;


    @PostMapping
    public ResponseEntity<Void> create(@RequestHeader("X-Id") String farmerHeaderId,
                                       @Valid @RequestBody ScheduleRequestDto dto) {
        Long farmerId = Long.parseLong(farmerHeaderId);
        scheduleService.addSchedule(farmerId, dto);
        return ResponseEntity.status(HttpStatus.CREATED).build();
    }


    @GetMapping("/{coopId}")
    public ResponseEntity<List<ScheduleResponseDto>> getAll(@RequestHeader("X-Id") String farmerHeaderId,
                                                            @PathVariable Long coopId) {
        Long farmerId = Long.parseLong(farmerHeaderId);
        return ResponseEntity.ok(scheduleService.getSchedules(coopId, farmerId));
    }


    @PutMapping("/{id}/{coopId}")
    public ResponseEntity<Void> update(@RequestHeader("X-Id") String farmerHeaderId,
                                       @PathVariable Long id,
                                       @PathVariable Long coopId,
                                       @Valid @RequestBody ScheduleRequestDto dto) {
        Long farmerId = Long.parseLong(farmerHeaderId);
        scheduleService.updateSchedule(id, coopId, farmerId, dto);
        return ResponseEntity.ok().build();
    }


    @DeleteMapping("/{id}/{coopId}")
    public ResponseEntity<Void> delete(@RequestHeader("X-Id") String farmerHeaderId,
                                       @PathVariable Long id,
                                       @PathVariable Long coopId) {
        Long farmerId = Long.parseLong(farmerHeaderId);
        scheduleService.deleteSchedule(id, coopId, farmerId);
        return ResponseEntity.noContent().build();
    }
}

