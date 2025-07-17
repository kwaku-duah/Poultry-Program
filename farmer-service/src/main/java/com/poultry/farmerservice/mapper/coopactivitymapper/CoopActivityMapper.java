package com.poultry.farmerservice.mapper.coopactivitymapper;

import com.poultry.farmerservice.dto.coopactivitydto.*;
import com.poultry.farmerservice.entity.coupactivity.*;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

@Mapper(componentModel = "spring")
public interface CoopActivityMapper {

    @Mapping(target = "id", ignore = true)
    EggsRecord toEggEntity(EggRequestDto eggRequestDto);

    @Mapping(target = "coopId", expression = "java(eggsRecord.getCoop().getId())")
    EggResponseDto toEggDto(EggsRecord eggsRecord);

    @Mapping(target = "id", ignore = true)
    FinancialRecord toFinancialEntity(FinancialRequestDto financialRequestDto);

    @Mapping(target = "coopId", expression = "java(financialRecord.getCoop().getId())")
    FinancialResponseDto toFinancialDto(FinancialRecord financialRecord);

    @Mapping(target = "id", ignore = true)
    MortalityRecord toMortalityEntity(MortalityRequestDto mortalityRequestDto);

    @Mapping(target = "coopId", expression = "java(mortalityRecord.getCoop().getId())")
    MortalityResponseDto toMortalityDto(MortalityRecord mortalityRecord);

    @Mapping(target = "id", ignore = true)
    ScheduleRecord toScheduledEntity(ScheduleRequestDto scheduleRequestDto);

    @Mapping(target = "coopId", expression = "java(scheduleRecord.getCoop().getId())")
    ScheduleResponseDto toScheduleDto(ScheduleRecord scheduleRecord);

    @Mapping(target = "id", ignore = true)
    VaccineRecord toVaccineEntity(VaccineRequestDto vaccineRequestDto);

    @Mapping(target = "coopId", expression = "java(vaccineRecord.getCoop().getId())")
    VaccineResponseDto toVaccineDto(VaccineRecord vaccineRecord);
}
