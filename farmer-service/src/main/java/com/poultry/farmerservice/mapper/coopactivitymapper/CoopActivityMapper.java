package com.poultry.farmerservice.mapper.coopactivitymapper;

import com.poultry.farmerservice.dto.coopactivitydto.EggRequestDto;
import com.poultry.farmerservice.entity.coupactivity.EggsRecord;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

@Mapper(componentModel = "spring")
public interface CoopActivityMapper {

    @Mapping(target = "id", ignore = true)
    EggsRecord toEntity(EggRequestDto eggRequestDto);

    EggRequestDto toDto(EggsRecord eggsRecord);
}
