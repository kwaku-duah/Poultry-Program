package com.poultry.userservice.mapper;


import com.poultry.userservice.dto.Oauth2Access;
import com.poultry.userservice.dto.Oauth2RresponseDto;
import com.poultry.userservice.dto.UserRequest;
import com.poultry.userservice.dto.UserResponseDto;
import com.poultry.userservice.entity.User;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

@Mapper(componentModel = "spring")
public interface UserMapper {

    @Mapping(target = "id", ignore = true)
    @Mapping(target = "roles", ignore = true)
    User toEntity(UserRequest userRequest);

    @Mapping(target = "id", ignore = true)
    @Mapping(target = "roles", ignore = true)
    @Mapping(target = "password", ignore = true)
    User oauthToEntity(Oauth2Access oauth2Access);


    UserResponseDto toResponse(User user);

    Oauth2RresponseDto toOauth2Response(User user);
}
