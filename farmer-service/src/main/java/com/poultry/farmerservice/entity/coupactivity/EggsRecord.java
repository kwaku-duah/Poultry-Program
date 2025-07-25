package com.poultry.farmerservice.entity.coupactivity;


import jakarta.persistence.DiscriminatorValue;
import jakarta.persistence.Entity;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Entity
@DiscriminatorValue("EGGS")
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class EggsRecord extends CoupActivity {
    private Integer numberOfEggsTrays;
    private Integer numberOfExtraEggs;
    private String reason;
}
