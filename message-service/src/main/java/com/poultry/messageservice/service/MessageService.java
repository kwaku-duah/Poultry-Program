package com.poultry.messageservice.service;

import com.poultry.messageservice.dto.MessageRequest;
import com.poultry.messageservice.dto.MessageResponse;

import java.util.List;

public interface MessageService {
    public void saveMessage(MessageRequest messageRequest);

    public List<MessageResponse> getMessages(String senderId, String receiverId);
}
