package com.poultry.farmerservice.repository;

import com.poultry.farmerservice.entity.coupactivity.MortalityRecord;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;
import java.util.Optional;

public interface MortalityRecordRepository extends JpaRepository<MortalityRecord, Long> {
    List<MortalityRecord> findByCoop_IdAndCoop_Farmer_FarmerId(Long coopId, Long farmerId);
    Optional<MortalityRecord> findByIdAndCoop_IdAndCoop_Farmer_FarmerId(Long id, Long coopId, Long farmerId);
}
