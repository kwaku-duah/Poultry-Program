package com.poultry.messageservice.service.kafka;

import com.fasterxml.jackson.core.JsonProcessingException;

public interface KafkaConsumerService {
    public  void listen(String messageJson) throws JsonProcessingException;
}
