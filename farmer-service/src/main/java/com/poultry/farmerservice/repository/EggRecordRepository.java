package com.poultry.farmerservice.repository;

import com.poultry.farmerservice.entity.coupactivity.EggsRecord;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;
import java.util.Optional;

public interface EggRecordRepository extends JpaRepository<EggsRecord, Long> {

    List<EggsRecord> findByCoop_IdAndCoop_Farmer_FarmerId(Long coopId, Long farmerId);
    Optional<EggsRecord> findByIdAndCoop_IdAndCoop_Farmer_FarmerId(Long id, Long coopId, Long farmerId);
}
