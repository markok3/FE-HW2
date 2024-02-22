package com.example.demo.model;

import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.ManyToOne;
import lombok.Data;

import java.time.LocalDateTime;

@Entity
@Data
public class Message {
    @Id
    private String id;

    @ManyToOne
    private Chat chat;

    private LocalDateTime dateTime;
    private String senderId;
    private String receiverId;
    private String messageText;

    public Message() {
    }

//    public Message(String id, Chat chat, LocalDateTime dateTime, String senderId, String receiverId, String messageText) {
//        this.id = id;
//        this.chat = chat;
//        this.dateTime = dateTime;
//        this.senderId = senderId;
//        this.receiverId = receiverId;
//        this.messageText = messageText;
//    }
}
