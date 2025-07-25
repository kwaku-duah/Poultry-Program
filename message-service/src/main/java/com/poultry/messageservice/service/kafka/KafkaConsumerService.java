package com.poultry.messageservice.service.kafka;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.poultry.messageservice.dto.MessageRequest;

public interface KafkaConsumerService {
    public  void listen(String messageJson) throws JsonProcessingException;
}
