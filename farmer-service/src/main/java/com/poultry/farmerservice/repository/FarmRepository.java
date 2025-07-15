package com.poultry.farmerservice.repository;

import com.poultry.farmerservice.entity.Farmer;
import org.springframework.data.jpa.repository.JpaRepository;

public interface FarmRepository extends JpaRepository<Farmer,Long> {
}
