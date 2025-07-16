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
@RequestMapping("/api/v1/schedules")
@RequiredArgsConstructor
public class ScheduleController {

    private final ScheduleService scheduleService;

    @PreAuthorize("hasRole('USER')")
    @PostMapping
    public ResponseEntity<Void> create(@RequestHeader("X-Id") String farmerId,
                                       @Valid @RequestBody ScheduleRequestDto dto) {
        scheduleService.addSchedule(farmerId, dto);
        return ResponseEntity.status(HttpStatus.CREATED).build();
    }

    @PreAuthorize("hasRole('USER')")
    @GetMapping("/{coopId}")
    public ResponseEntity<List<ScheduleResponseDto>> getAll(@RequestHeader("X-Id") String farmerId,
                                                            @PathVariable Long coopId) {
        return ResponseEntity.ok(scheduleService.getSchedules(coopId, farmerId));
    }

    @PreAuthorize("hasRole('USER')")
    @PutMapping("/{id}/{coopId}")
    public ResponseEntity<Void> update(@RequestHeader("X-Id") String farmerId,
                                       @PathVariable Long id,
                                       @PathVariable Long coopId,
                                       @Valid @RequestBody ScheduleRequestDto dto) {
        scheduleService.updateSchedule(id, coopId, farmerId, dto);
        return ResponseEntity.ok().build();
    }

    @PreAuthorize("hasRole('USER')")
    @DeleteMapping("/{id}/{coopId}")
    public ResponseEntity<Void> delete(@RequestHeader("X-Id") String farmerId,
                                       @PathVariable Long id,
                                       @PathVariable Long coopId) {
        scheduleService.deleteSchedule(id, coopId, farmerId);
        return ResponseEntity.noContent().build();
    }
}

