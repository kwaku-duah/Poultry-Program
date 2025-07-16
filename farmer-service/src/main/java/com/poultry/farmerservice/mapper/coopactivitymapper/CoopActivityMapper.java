package com.poultry.farmerservice.mapper.coopactivitymapper;

import com.poultry.farmerservice.dto.coopactivitydto.*;
import com.poultry.farmerservice.entity.coupactivity.*;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

@Mapper(componentModel = "spring")
public interface CoopActivityMapper {

    @Mapping(target = "id", ignore = true)
    EggsRecord toEntity(EggRequestDto eggRequestDto);

    EggRequestDto toDto(EggsRecord eggsRecord);

    @Mapping(target = "id", ignore = true)
    FinancialRecord toEntity(FinancialRequestDto financialRequestDto);

    FinancialRequestDto toDto(FinancialRecord financialRecord);

    @Mapping(target = "id", ignore = true)
    MortalityRecord toEntity(MortalityRequestDto mortalityRequestDto);

    MortalityRequestDto toDto(MortalityRecord mortalityRecord);

    @Mapping(target = "id", ignore = true)
    ScheduleRecord toEntity(ScheduleRequestDto scheduleRequestDto);

    ScheduleRequestDto toDto(ScheduleRecord scheduleRecord);

    @Mapping(target = "id", ignore = true)
    VaccineRecord toEntity(VaccineRequestDto vaccineRequestDto);

    VaccineRequestDto toDto(VaccineRecord vaccineRecord);

}
