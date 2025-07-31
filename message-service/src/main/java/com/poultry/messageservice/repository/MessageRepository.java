package com.poultry.messageservice.repository;

import com.poultry.messageservice.entity.Message;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;
import java.util.UUID;

public interface MessageRepository extends JpaRepository<Message, UUID> {
    List<Message> findBySenderIdAndReceiverIdOrReceiverIdAndSenderId ( String senderId, String receiverId,String receiverId2, String senderId2
    );
    // Fetch messages where sender & receiver match in either direction
    @Query("SELECT m FROM Message m WHERE " +
            "(m.senderId = :sender AND m.receiverId = :receiver) OR " +
            "(m.senderId = :receiver AND m.receiverId = :sender) " +
            "ORDER BY m.timeStamp ASC")
    List<Message> findChatHistoryBetweenUsers(
            @Param("sender") String senderId,
            @Param("receiver") String receiverId
    );
}
