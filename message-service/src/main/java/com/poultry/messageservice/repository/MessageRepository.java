package com.poultry.messageservice.repository;

import com.poultry.messageservice.entity.Message;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;
import java.util.UUID;

public interface MessageRepository extends JpaRepository<Message, UUID> {
    List<Message> findBySenderIdAndReceiverIdOrReceiverIdAndSenderId ( String senderId, String receiverId,
                                                                       String receiverId2, String senderId2
    );
}
