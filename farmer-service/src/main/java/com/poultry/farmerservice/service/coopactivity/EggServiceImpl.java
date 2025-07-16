package com.poultry.farmerservice.service.coopactivity;

import com.poultry.farmerservice.dto.coopactivitydto.EggRequestDto;
import com.poultry.farmerservice.dto.coopactivitydto.EggResponseDto;
import com.poultry.farmerservice.entity.Coop;
import com.poultry.farmerservice.entity.coupactivity.EggsRecord;
import com.poultry.farmerservice.exception.ResourceNotFoundException;
import com.poultry.farmerservice.mapper.coopactivitymapper.CoopActivityMapper;
import com.poultry.farmerservice.repository.CoopRepository;
import com.poultry.farmerservice.repository.EggRecordRepository;
import com.poultry.farmerservice.repository.FarmRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
@RequiredArgsConstructor
public class EggServiceImpl implements EggService {

    private final EggRecordRepository eggRecordRepository;
    private final FarmRepository farmRepository;
    private final CoopRepository coopRepository;
    private final CoopActivityMapper coopMapper;

    @Transactional
    @Override
    public void addEggRecord(String farmerId, EggRequestDto eggRequestDto) {
        Coop coop = coopRepository.findByIdAndFarmerId_FarmerId(eggRequestDto.coopId(),farmerId)
                .orElseThrow(() -> new ResourceNotFoundException("Coop with " + eggRequestDto.coopId() + " not found"));
        EggsRecord record = coopMapper.toEggEntity(eggRequestDto);
        record.setCoop(coop);
        eggRecordRepository.save(record);
    }

    @Transactional(readOnly = true)
    @Override
    public List<EggResponseDto> getEggRecords(Long coopId, String farmerId) {
        return eggRecordRepository.findByCoop_IdAndCoop_Farmer_FarmerId(coopId,farmerId)
                .stream().map(coopMapper::toEggDto)
                .toList();
    }

    @Transactional(readOnly = true)
    @Override
    public void updateEggRecord(Long id, Long coopId, String farmerId, EggRequestDto eggRequestDto) {
        EggsRecord record = eggRecordRepository.findByIdAndCoop_IdAndCoop_Farmer_FarmerId(id, coopId,farmerId)
                .orElseThrow(() -> new ResourceNotFoundException("Coop with " + eggRequestDto.coopId() + " not found"));
        record.setDate(eggRequestDto.date());
        record.setNumberOfEggsTrays(eggRequestDto.numberOfEggsTrays());
        record.setNumberOfExtraEggs(eggRequestDto.numberOfExtraEggs());
        record.setReason(eggRequestDto.reason());
        eggRecordRepository.save(record);
    }

    @Transactional(readOnly = true)
    @Override
    public void deleteEggRecord(Long id, Long coopId, String farmerId) {
        EggsRecord record = eggRecordRepository.findByIdAndCoop_IdAndCoop_Farmer_FarmerId(id,coopId,farmerId)
                .orElseThrow(() -> new ResourceNotFoundException("Coop with " + coopId + " not found"));
        eggRecordRepository.delete(record);
    }
}
