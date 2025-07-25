package com.poultry.messageservice.service;

import com.poultry.messageservice.dto.MessageRequest;
import com.poultry.messageservice.dto.MessageResponse;
import com.poultry.messageservice.entity.Message;
import com.poultry.messageservice.mapper.MessageMapper;
import com.poultry.messageservice.repository.MessageRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.time.Instant;
import java.util.List;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class MessageServiceImpl implements MessageService {
    private final MessageRepository messageRepository;
    private final MessageMapper messageMapper;

    @Transactional
    @Override
    public void saveMessage(MessageRequest messageRequest) {
        Message message = messageMapper.toEntity(messageRequest);
        message.setTimeStamp(Instant.now().toString());
        messageRepository.save(message);

    }

    @Transactional(readOnly = true)
    @Override
    public List<MessageResponse> getMessages(String senderId, String receiverId) {
        List<Message> messages = messageRepository.findBySenderIdAndReceiverIdOrReceiverIdAndSenderId(
                senderId,receiverId,receiverId,senderId
        );
                return messages.stream()
                        .map(messageMapper::toDto)
                        .collect(Collectors.toList());
    }
}
