package com.poultry.farmerservice.mapper.coopactivitymapper;

import com.poultry.farmerservice.dto.coopactivitydto.*;
import com.poultry.farmerservice.entity.coupactivity.*;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

@Mapper(componentModel = "spring")
public interface CoopActivityMapper {

    @Mapping(target = "id", ignore = true)
    EggsRecord toEggEntity(EggRequestDto eggRequestDto);

    EggResponseDto toEggDto(EggsRecord eggsRecord);

    @Mapping(target = "id", ignore = true)
    FinancialRecord toFinancialEntity(FinancialRequestDto financialRequestDto);

    FinancialResponseDto toFinancialDto(FinancialRecord financialRecord);

    @Mapping(target = "id", ignore = true)
    MortalityRecord toMortalityEntity(MortalityRequestDto mortalityRequestDto);

    MortalityResponseDto toMortalityDto(MortalityRecord mortalityRecord);

    @Mapping(target = "id", ignore = true)
    ScheduleRecord toScheduledEntity(ScheduleRequestDto scheduleRequestDto);

    ScheduleResponseDto toScheduleDto(ScheduleRecord scheduleRecord);

    @Mapping(target = "id", ignore = true)
    VaccineRecord toVaccineEntity(VaccineRequestDto vaccineRequestDto);

    VaccineResponseDto toVaccineDto(VaccineRecord vaccineRecord);

}
