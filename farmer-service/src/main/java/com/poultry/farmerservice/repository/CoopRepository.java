package com.poultry.farmerservice.repository;

import com.poultry.farmerservice.entity.Coop;
import org.springframework.data.jpa.repository.JpaRepository;

public interface CoopRepository extends JpaRepository<Coop, Long> {
}
