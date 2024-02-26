package com.example.demo.service.impl;

import com.example.demo.model.Chat;
import com.example.demo.model.Message;
import com.example.demo.model.User;
import com.example.demo.model.dto.MessageDTO;
import com.example.demo.repository.ChatRepository;
import com.example.demo.repository.MessageRepository;
import com.example.demo.repository.UserRepository;
import com.example.demo.service.ChatService;
import com.example.demo.service.MessageService;
import com.example.demo.service.UserService;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ChatServiceImpl implements ChatService {

    private final ChatRepository chatRepository;
    private final UserService userService;
    private final MessageService messageService;

    public ChatServiceImpl(ChatRepository chatRepository, UserService userService, MessageService messageService) {
        this.chatRepository = chatRepository;
        this.userService = userService;
        this.messageService = messageService;
    }

    @Override
    public MessageDTO sendMessage(MessageDTO messageDTO) {
        User sender = userService.getUserById(messageDTO.getSenderId());
        User receiver = userService.getUserById(messageDTO.getReceiverId());

        if(sender == null || receiver == null){
            throw new RuntimeException("User not found");
        }

        if(existsChatById(messageDTO.combineIdsOfParticipants())){
            Chat chat = chatRepository.findChatById(messageDTO.combineIdsOfParticipants());

            Message message = messageService.saveMessage(chat, Message.fromDTO(messageDTO));

            chat.getMessagesList().add(message);

            chatRepository.save(chat);

            return message.toDTO();
        }

            Chat chat = createChat(messageDTO.getSenderId(), messageDTO.getReceiverId());

//            userService.addChatToUser(messageDTO.getSenderId(), chat);
//            userService.addChatToUser(messageDTO.getReceiverId(), chat);

            Message message = messageService.saveMessage(chat, Message.fromDTO(messageDTO));

            addMessageToChat(chat.getId(), message);

            return message.toDTO();

    }

    public void addMessageToChat(String chatId, Message message){
        Chat chat = chatRepository.findChatById(chatId);
        chat.getMessagesList().add(message);
        chatRepository.save(chat);
    }

    @Override
    public Chat createChat(String senderId, String receiverId){
        String chatId  = combineIdsOfParticipants(senderId, receiverId);

        if(existsChatById(chatId)){
            return chatRepository.findChatById(chatId);
        }


        User sender = userService.getUserById(senderId);
        User receiver = userService.getUserById(receiverId);

        if(sender == null || receiver == null){
            throw new RuntimeException("User not found");
        }
        Chat chat = new Chat(sender, receiver);

        userService.addChatToUser(senderId, chat);
        userService.addChatToUser(receiverId, chat);

        return chatRepository.save(chat);
    }

    public String combineIdsOfParticipants(String firstParticipantId, String secondParticipantId){
        return firstParticipantId.compareTo(secondParticipantId) < 0 ?
                firstParticipantId + secondParticipantId
                : secondParticipantId + firstParticipantId;
    }

    @Override
    public Chat getChat(String chatId) {
        return chatRepository.findChatById(chatId);
    }


    @Override
    public boolean existsChatById(String chatId){
        return chatRepository.existsChatById(chatId);
    }

    @Override
    public List<Chat> getChatsByUserId(String userId) {
        return chatRepository.findChatByFirstParticipantIdOrSecondParticipantId(userId, userId);
    }
}
