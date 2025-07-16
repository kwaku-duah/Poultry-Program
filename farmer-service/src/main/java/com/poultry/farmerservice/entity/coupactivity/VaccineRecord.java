package com.poultry.farmerservice.entity.coupactivity;


import jakarta.persistence.DiscriminatorValue;
import jakarta.persistence.Entity;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;


@Entity
@DiscriminatorValue("VACCINE")
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class VaccineRecord extends CoupActivity {
    private String vaccineName;
    private String vaccineType;
    private String dosage;
    private String durationDosage;
    private String reason;
    private String description;
}
