package com.poultry.userservice.mapper;

import com.poultry.userservice.dto.UserRequest;
import com.poultry.userservice.entity.User;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

@Mapper(componentModel = "spring")
public interface UserMapper {

    @Mapping(target = "id", ignore = true)
    @Mapping(target = "roles", ignore = true)
    User toEntity(UserRequest userRequest);
}
