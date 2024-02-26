package com.example.demo.repository;

import com.example.demo.model.Chat;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface ChatRepository extends JpaRepository<Chat, String> {
    Chat findChatById(String chatId);
    boolean existsChatById(String chatId);
    List<Chat> findChatByFirstParticipantIdOrSecondParticipantId(String firstParticipantId, String secondParticipantId);

}
