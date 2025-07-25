package com.poultry.farmerservice.entity.coupactivity;

import jakarta.persistence.DiscriminatorValue;
import jakarta.persistence.Entity;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Entity
@DiscriminatorValue("MORTALITY")
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class MortalityRecord extends CoupActivity {
    private Integer numberOfDeaths;
    private String reason;
    private String report;

}
