package com.poultry.farmerservice.entity.coupactivity;

import jakarta.persistence.DiscriminatorValue;
import jakarta.persistence.Entity;

@Entity
@DiscriminatorValue("MORTALITY")
public class MortalityRecord extends CoupActivity {
    private Integer numberOfDeaths;
    private String reason;
    private String report;

}
