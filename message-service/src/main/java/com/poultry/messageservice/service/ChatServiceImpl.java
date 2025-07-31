package com.poultry.messageservice.service;


import com.poultry.messageservice.dto.MessageRequest;
import com.poultry.messageservice.dto.MessageResponse;
import com.poultry.messageservice.entity.Message;
import com.poultry.messageservice.repository.MessageRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class ChatServiceImpl implements ChatService {
    private MessageService messageService;
    private final MessageRepository messageRepository;


    @Override
    public void chatUser(MessageRequest messageRequest) {
        messageService.saveMessage(messageRequest);
    }

    @Override
    public List<MessageResponse> getAllMessages(String senderId, String receiverId) {
        List<Message> entities = messageRepository.findChatHistoryBetweenUsers(senderId, receiverId);

        return entities.stream()
                .map(m -> new MessageResponse(
                        m.getSenderId(),
                        m.getReceiverId(),
                        m.getContent(),
                        m.getTimeStamp()))
                .collect(Collectors.toList());
    }
}
