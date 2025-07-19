package com.poultry.farmerservice.mapper;

import com.poultry.farmerservice.dto.CoopRequestDto;
import com.poultry.farmerservice.dto.CoopResponseDto;
import com.poultry.farmerservice.entity.Coop;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

@Mapper(componentModel = "spring")
public interface CoopMapper {

    @Mapping(target = "id", ignore = true)
    @Mapping(target = "farmer", ignore = true)
    @Mapping(target = "coopName", ignore = true)
    Coop toEntity(CoopRequestDto coopRequestDto);

    @Mapping(target = "coopId", source = "id")
    CoopResponseDto toCoopResponseDto(Coop coop);

}
