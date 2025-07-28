package com.poultry.messageservice.service.kafka;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.poultry.messageservice.dto.MessageRequest;
import lombok.RequiredArgsConstructor;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class KafkaServiceImpl implements KafkaService {

    private final KafkaTemplate<String, String> kafkaTemplate;
    private final ObjectMapper objectMapper;
    private static final String DEFAULT_TOPIC = "message-topic";


    @Override
    public void sendMessage(MessageRequest messageRequest) {
        try {
            String jsonMessage = objectMapper.writeValueAsString(messageRequest);
            kafkaTemplate.send(DEFAULT_TOPIC, jsonMessage);
        } catch (JsonProcessingException e) {
            throw new RuntimeException("Failed to send kafka message", e);
        }
    }
}
