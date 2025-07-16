package com.poultry.farmerservice.repository;

import com.poultry.farmerservice.entity.coupactivity.VaccineRecord;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;
import java.util.Optional;

public interface VaccineRecordRepository extends JpaRepository<VaccineRecord, Long> {
    List<VaccineRecord> findByCoop_IdAndCoop_Farmer_FarmerId(Long coopId, String farmerId);
    Optional<VaccineRecord> findByIdAndCoop_IdAndCoop_Farmer_FarmerId(Long id, Long coopId, String farmerId);
}
