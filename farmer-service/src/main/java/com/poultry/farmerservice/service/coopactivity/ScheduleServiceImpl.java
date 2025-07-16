package com.poultry.farmerservice.service.coopactivity;

import com.poultry.farmerservice.dto.coopactivitydto.ScheduleRequestDto;
import com.poultry.farmerservice.dto.coopactivitydto.ScheduleResponseDto;
import com.poultry.farmerservice.entity.Coop;
import com.poultry.farmerservice.entity.coupactivity.ScheduleRecord;
import com.poultry.farmerservice.exception.ResourceNotFoundException;
import com.poultry.farmerservice.mapper.coopactivitymapper.CoopActivityMapper;
import com.poultry.farmerservice.repository.CoopRepository;
import com.poultry.farmerservice.repository.ScheduleRecordRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
@RequiredArgsConstructor
public class ScheduleServiceImpl implements ScheduleService {
    private final ScheduleRecordRepository repo;
    private final CoopRepository coopRepo;
    private final CoopActivityMapper mapper;

    @Transactional
    @Override
    public void addSchedule(String farmerId, ScheduleRequestDto dto) {
        Coop coop = coopRepo.findByIdAndFarmer_FarmerId(dto.coopId(), farmerId)
                .orElseThrow(() -> new ResourceNotFoundException("Coop not found"));
        ScheduleRecord record = mapper.toScheduledEntity(dto);
        record.setCoop(coop);
        repo.save(record);
    }

    @Transactional(readOnly = true)
    @Override
    public List<ScheduleResponseDto> getSchedules(Long coopId, String farmerId) {
        return repo.findByCoop_IdAndCoop_Farmer_FarmerId(coopId, farmerId)
                .stream().map(mapper::toScheduleDto).toList();
    }

    @Transactional
    @Override
    public void updateSchedule(Long id, Long coopId, String farmerId, ScheduleRequestDto dto) {
        ScheduleRecord record = repo.findByIdAndCoop_IdAndCoop_Farmer_FarmerId(id, coopId, farmerId)
                .orElseThrow(() -> new ResourceNotFoundException("Schedule not found"));
        record.setDate(dto.date());
        record.setRemindStartDate(dto.remindDate().toLocalDate());
        record.setTitle(dto.title());
        record.setDescription(dto.description());
        record.setStatus(dto.status());
        repo.save(record);
    }

    @Transactional
    @Override
    public void deleteSchedule(Long id, Long coopId, String farmerId) {
        ScheduleRecord record = repo.findByIdAndCoop_IdAndCoop_Farmer_FarmerId(id, coopId, farmerId)
                .orElseThrow(() -> new ResourceNotFoundException("Schedule not found"));
        repo.delete(record);
    }
}

