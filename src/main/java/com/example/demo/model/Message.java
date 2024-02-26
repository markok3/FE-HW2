package com.example.demo.model;

import com.example.demo.model.dto.MessageDTO;
import jakarta.persistence.*;
import lombok.Data;

import java.time.LocalDateTime;

@Entity
@Data
public class Message {
    @Id
    @GeneratedValue(strategy = GenerationType.UUID)
    private String id;

    @ManyToOne
    private Chat chat;

    private LocalDateTime dateTime;
    private String senderId;
    private String receiverId;
    private String messageText;

    public Message() {
        this.dateTime = LocalDateTime.now();
    }

    public static Message fromDTO(MessageDTO messageDTO) {
        Message message = new Message();
        message.setSenderId(messageDTO.getSenderId());
        message.setReceiverId(messageDTO.getReceiverId());
        message.setMessageText(messageDTO.getMessageText());
        return message;
    }

    public MessageDTO toDTO() {
        MessageDTO messageDTO = new MessageDTO();
        messageDTO.setSenderId(this.senderId);
        messageDTO.setReceiverId(this.receiverId);
        messageDTO.setMessageText(this.messageText);
        messageDTO.setDateTime(this.dateTime);
        return messageDTO;
    }


}
