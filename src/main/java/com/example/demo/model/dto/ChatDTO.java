package com.example.demo.model.dto;

import com.example.demo.model.Message;
import lombok.Data;

import java.util.List;

@Data
public class ChatDTO {
    String id;
    List<MessageDTO> messages;
    String firstParticipantId;
    String secondParticipantId;
}
