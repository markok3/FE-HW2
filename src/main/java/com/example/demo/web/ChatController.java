package com.example.demo.web;

import com.example.demo.model.Chat;
import com.example.demo.model.dto.ChatDTO;
import com.example.demo.model.dto.MessageDTO;
import com.example.demo.service.ChatService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.messaging.handler.annotation.MessageMapping;
import org.springframework.messaging.handler.annotation.Payload;
import org.springframework.messaging.simp.SimpMessagingTemplate;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/chat")
public class ChatController {
    private SimpMessagingTemplate simpMessagingTemplate;

    private final ChatService chatService;


    public ChatController(ChatService chatService, SimpMessagingTemplate simpMessagingTemplate) {
        this.chatService = chatService;
        this.simpMessagingTemplate = simpMessagingTemplate;
    }


    @GetMapping("/create/{senderId}/{receiverId}")
    public ChatDTO createChat(@PathVariable String senderId, @PathVariable String receiverId) {
        return chatService.createChat(senderId, receiverId).toChatDTO();
    }

    @MessageMapping("/send-message")
    public MessageDTO sendMessage(@Payload MessageDTO messageDTO) {
        simpMessagingTemplate.convertAndSendToUser(messageDTO.getChatId(), "/private", messageDTO);
        chatService.sendMessage(messageDTO);

        return messageDTO;
    }



    @GetMapping("/{id}")
    public ChatDTO getChat(@PathVariable String id) {
        return chatService.getChat(id).toChatDTO();
    }
}
