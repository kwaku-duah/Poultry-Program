package com.poultry.farmerservice.kafka;

import com.google.protobuf.util.JsonFormat;
import com.poultry.farmerservice.grpc.MessageRequest;
import com.poultry.farmerservice.websocketconfiguration.WebSocketMessageHandler;
import lombok.extern.slf4j.Slf4j;
import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.stereotype.Service;
import org.springframework.web.socket.TextMessage;
import org.springframework.web.socket.WebSocketSession;

import java.io.IOException;

@Slf4j
@Service
public class KafkaMessageListener {

    @KafkaListener(topics = "message-topic", groupId = "farmer-group")
    public void listen(String messageJson) {
        try {
            // Log raw message for debugging
            log.info("Raw Kafka message: {}", messageJson);

            // Parse JSON to Protobuf
            MessageRequest.Builder builder = MessageRequest.newBuilder();
            JsonFormat.parser().ignoringUnknownFields().merge(messageJson, builder);
            MessageRequest message = builder.build();

            log.info("💬 New message received: From: {}, To: {}, Content: {}",
                    message.getSenderId(), message.getReceiverId(), message.getContent());

            // Forward to WebSocket sessions
            for (WebSocketSession session : WebSocketMessageHandler.getWebSocketSessions()) {
                try {
                    session.sendMessage(new TextMessage(messageJson));
                } catch (IOException e) {
                    log.error("Error sending message to WebSocket session: {}", e.getMessage());
                }
            }

        } catch (IOException e) {
            log.error("Failed to deserialize Kafka message", e);
        }
    }
}