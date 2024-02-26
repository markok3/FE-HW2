package com.example.demo.service;

import com.example.demo.model.Chat;
import com.example.demo.model.Message;
import com.example.demo.model.dto.MessageDTO;
import org.springframework.stereotype.Service;

import java.util.List;

public interface ChatService {
    MessageDTO sendMessage(MessageDTO messageDTO);
    Chat getChat(String chatId);
    Chat createChat(String senderId, String receiverId);
    boolean existsChatById(String chatId);
    List<Chat> getChatsByUserId(String userId);
}
