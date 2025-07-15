package com.poultry.farmerservice.entity.coupactivity;

import jakarta.persistence.DiscriminatorValue;
import jakarta.persistence.Entity;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.math.BigDecimal;

@Entity
@DiscriminatorValue("FINANCIALS")
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class FinancialRecord extends CoupActivity {
    private String itemName;
    private BigDecimal amount;
    private String description;

}
