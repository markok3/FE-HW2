package com.example.demo.model.dto;

import lombok.Data;

import java.time.LocalDateTime;

@Data
public class MessageDTO {
    private String chatId;
    private String senderId;
    private String receiverId;
    private String messageText;
    private LocalDateTime dateTime;


    public String combineIdsOfParticipants(){
        return senderId.compareTo(receiverId) < 0 ?
                senderId + receiverId
                : receiverId + senderId;
    }


}
