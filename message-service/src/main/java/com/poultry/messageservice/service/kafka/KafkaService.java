package com.poultry.messageservice.service.kafka;

import com.poultry.messageservice.dto.MessageRequest;
import com.poultry.messageservice.entity.Message;

public interface KafkaService {
    public void sendMessage(MessageRequest messageRequest);
}
