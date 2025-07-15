package com.poultry.farmerservice.repository;

import com.poultry.farmerservice.entity.Farmer;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface FarmRepository extends JpaRepository<Farmer,Long> {
    boolean existsByFarmerId(String farmerId);
    Optional<Farmer> findByFarmerId(String farmerId);
    void deleteByFarmerId(String farmerId);
}
