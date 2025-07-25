package com.poultry.messageservice.mapper;

import com.poultry.messageservice.dto.MessageRequest;
import com.poultry.messageservice.dto.MessageResponse;
import com.poultry.messageservice.entity.Message;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

@Mapper(componentModel = "spring")
public interface MessageMapper {

    @Mapping(target = "id", ignore = true)
    @Mapping(target = "timeStamp", ignore = true)
    Message toEntity (MessageRequest messageRequest);

    MessageResponse toDto(Message message);
}
