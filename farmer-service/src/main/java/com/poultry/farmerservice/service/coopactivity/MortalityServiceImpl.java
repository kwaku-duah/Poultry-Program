package com.poultry.farmerservice.service.coopactivity;

import com.poultry.farmerservice.dto.coopactivitydto.MortalityRequestDto;
import com.poultry.farmerservice.dto.coopactivitydto.MortalityResponseDto;
import com.poultry.farmerservice.entity.Coop;
import com.poultry.farmerservice.entity.coupactivity.MortalityRecord;
import com.poultry.farmerservice.exception.ResourceNotFoundException;
import com.poultry.farmerservice.mapper.coopactivitymapper.CoopActivityMapper;
import com.poultry.farmerservice.repository.CoopRepository;
import com.poultry.farmerservice.repository.MortalityRecordRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
@RequiredArgsConstructor
public class MortalityServiceImpl implements MortalityService {
    private final MortalityRecordRepository repo;
    private final CoopRepository coopRepo;
    private final CoopActivityMapper mapper;

    @Transactional
    @Override
    public void addMortalityRecord(Long farmerId, MortalityRequestDto dto) {
        Coop coop = coopRepo.findByIdAndFarmer_FarmerId(dto.coopId(), farmerId)
                .orElseThrow(() -> new ResourceNotFoundException("Coop not found"));
        MortalityRecord record = mapper.toMortalityEntity(dto);
        record.setCoop(coop);
        repo.save(record);
    }

    @Transactional(readOnly = true)
    @Override
    public List<MortalityResponseDto> getMortalityRecords(Long coopId, Long farmerId) {
        return repo.findByCoop_IdAndCoop_Farmer_FarmerId(coopId, farmerId)
                .stream().map(mapper::toMortalityDto).toList();
    }

    @Transactional
    @Override
    public void updateMortalityRecord(Long id, Long coopId, Long farmerId, MortalityRequestDto dto) {
        MortalityRecord record = repo.findByIdAndCoop_IdAndCoop_Farmer_FarmerId(id, coopId, farmerId)
                .orElseThrow(() -> new ResourceNotFoundException("Mortality record not found"));
        record.setDate(dto.date());
        record.setNumberOfDeaths(dto.numberOfDeaths());
        record.setReason(dto.reason());
        record.setReport(dto.report());
        repo.save(record);
    }

    @Transactional
    @Override
    public void deleteMortalityRecord(Long id, Long coopId, Long farmerId) {
        MortalityRecord record = repo.findByIdAndCoop_IdAndCoop_Farmer_FarmerId(id, coopId, farmerId)
                .orElseThrow(() -> new ResourceNotFoundException("Mortality record not found"));
        repo.delete(record);
    }
}

