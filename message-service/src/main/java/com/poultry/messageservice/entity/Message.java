package com.poultry.messageservice.entity;


import jakarta.persistence.Entity;
import jakarta.persistence.Table;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;


@Entity
@Table(name= "messages")
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class Message {


    private String senderId;
    private String receiverId;
    private String content;
    private String timeStamp;
}
