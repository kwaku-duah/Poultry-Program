package com.poultry.messageservice.entity;


import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.hibernate.annotations.GenericGenerator;

import java.util.UUID;


@Entity
@Table(name= "messages")
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class Message {

    @Id
    @GeneratedValue(generator = "uuid")
    @Column(columnDefinition = "uuid", updatable = false, nullable = false)
    private UUID id;
    private String senderId;
    private String receiverId;
    private String content;
    private String timeStamp;
}
