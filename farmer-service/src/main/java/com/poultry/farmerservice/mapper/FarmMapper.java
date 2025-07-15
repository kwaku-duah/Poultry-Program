package com.poultry.farmerservice.mapper;


import com.poultry.farmerservice.dto.CoopResponseDto;
import com.poultry.farmerservice.dto.FarmRequestDto;
import com.poultry.farmerservice.dto.FarmResponseDto;
import com.poultry.farmerservice.entity.Coop;
import com.poultry.farmerservice.entity.Farmer;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

import java.util.List;

@Mapper(componentModel = "spring")
public interface FarmMapper {

    @Mapping(target = "id", ignore = true)
    @Mapping(target = "farmerId", ignore = true)
    @Mapping(target = "coops", expression = "java(new ArrayList<>())")
    Farmer toEntity(FarmRequestDto farmRequestDto);

    @Mapping(target = "coops", source = "coops")
    FarmResponseDto farmResponseDto(Farmer farmer);

    List<CoopResponseDto> toCoopResponseDto(List<Coop> coops);


}
