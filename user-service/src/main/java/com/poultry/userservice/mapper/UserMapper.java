package com.poultry.userservice.mapper;


import com.poultry.userservice.dto.Oauth2Access;
import com.poultry.userservice.dto.Oauth2RresponseDto;
import com.poultry.userservice.dto.UserRequest;
import com.poultry.userservice.dto.UserResponseDto;
import com.poultry.userservice.entity.User;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.springframework.security.oauth2.core.user.OAuth2User;

@Mapper(componentModel = "spring")
public interface UserMapper {

    @Mapping(target = "id", ignore = true)
    @Mapping(target = "roles", ignore = true)
    User toEntity(UserRequest userRequest);

    @Mapping(target = "id", ignore = true)
    @Mapping(target = "roles", ignore = true)
    @Mapping(target = "password", ignore = true)
    User oauthToEntity(OAuth2User oAuth2User);


    UserResponseDto toResponse(User user);

    Oauth2RresponseDto toOauth2Response(User user);
}
