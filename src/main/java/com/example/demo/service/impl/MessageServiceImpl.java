package com.example.demo.service.impl;

import com.example.demo.model.Chat;
import com.example.demo.model.Message;
import com.example.demo.repository.MessageRepository;
import com.example.demo.service.ChatService;
import com.example.demo.service.MessageService;
import org.springframework.stereotype.Service;

@Service
public class MessageServiceImpl implements MessageService {
    private final MessageRepository messageRepository;

    public MessageServiceImpl(MessageRepository messageRepository) {
        this.messageRepository = messageRepository;
    }

    @Override
    public Message saveMessage(Chat chat, Message message) {
        message.setChat(chat);
        return messageRepository.save(message);
    }
}
