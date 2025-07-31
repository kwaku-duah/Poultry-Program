package com.poultry.messageservice.service;


import com.poultry.messageservice.dto.MessageRequest;
import com.poultry.messageservice.dto.MessageResponse;

import java.util.List;

public interface ChatService {
    void chatUser(MessageRequest messageRequest);

    List<MessageResponse> getAllMessages(String senderId, String receiverId);
}
