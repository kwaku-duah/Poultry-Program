package com.poultry.farmerservice.entity.coupactivity;

import jakarta.persistence.DiscriminatorValue;
import jakarta.persistence.Entity;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.time.LocalDate;

@Entity
@DiscriminatorValue("SCHEDULE")
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class ScheduleRecord extends CoupActivity {
    String title;
    String description;
    LocalDate startDate;
}
