package com.poultry.farmerservice.service.coopactivity;

import com.poultry.farmerservice.dto.coopactivitydto.EggRequestDto;
import com.poultry.farmerservice.dto.coopactivitydto.EggResponseDto;
import com.poultry.farmerservice.entity.Coop;
import com.poultry.farmerservice.mapper.coopactivitymapper.CoopActivityMapper;
import com.poultry.farmerservice.repository.CoopRepository;
import com.poultry.farmerservice.repository.EggRecordRepository;
import com.poultry.farmerservice.repository.FarmRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class EggServiceImpl implements EggService {

    private final EggRecordRepository eggRecordRepository;
    private final FarmRepository farmRepository;
    private final CoopRepository coopRepository;
    private final CoopActivityMapper coopMapper;

    @Override
    public void addEggRecord(String farmerId, EggRequestDto eggRequestDto) {
        Coop coop = coopRepository.
    }

    @Override
    public List<EggResponseDto> getEggRecords(Long coopId, String farmerId) {
        return eggRecordRepository.findByCoop_IdAndCoop_Farmer_FarmerId(coopId,farmerId)
                .stream().map(coopMapper::toEggDto)
                .toList();
    }

    @Override
    public void updateEggRecord(Long id, Long coopId, String farmerId, EggRequestDto eggRequestDto) {

    }

    @Override
    public void deleteEggRecord(Long id, Long coopId, String farmerId) {

    }
}
