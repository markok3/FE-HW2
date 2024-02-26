package com.example.demo.service;

import com.example.demo.model.Chat;
import com.example.demo.model.Message;
import org.springframework.stereotype.Service;

public interface MessageService {
    Message saveMessage(Chat chat, Message message);
}
