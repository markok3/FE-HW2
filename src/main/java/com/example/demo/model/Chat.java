package com.example.demo.model;

import com.example.demo.model.dto.ChatDTO;
import com.example.demo.model.dto.MessageDTO;
import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.ManyToMany;
import jakarta.persistence.OneToMany;
import lombok.Data;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.List;

@Entity
@Data
public class Chat {


    @Id
    private String id;

    @ManyToMany(fetch = jakarta.persistence.FetchType.EAGER)
    private List<User> users;

    @OneToMany(fetch = jakarta.persistence.FetchType.EAGER)
    private List<Message> messagesList;

    private String firstParticipantId;

    private String secondParticipantId;

    public Chat() {
    }



    public Chat (User firstParticipant, User secondParticipant){
        this.id = combineIdsOfParticipants(firstParticipant.getUsername(), secondParticipant.getUsername());
        this.users = new ArrayList<>();
        this.messagesList = new ArrayList<>();

        this.users.add(firstParticipant);
        this.users.add(secondParticipant);

        this.firstParticipantId = firstParticipant.getUsername();
        this.secondParticipantId = secondParticipant.getUsername();

    }

    private String combineIdsOfParticipants(String firstParticipantId, String secondParticipantId){
        return firstParticipantId.compareTo(secondParticipantId) < 0 ?
                firstParticipantId + secondParticipantId
                : secondParticipantId + firstParticipantId;
    }


    public ChatDTO toChatDTO() {
        ChatDTO chatDTO = new ChatDTO();
        chatDTO.setId(this.id);
        chatDTO.setFirstParticipantId(this.firstParticipantId);
        chatDTO.setSecondParticipantId(this.secondParticipantId);

        // Convert messagesList to MessageDTO list
        List<MessageDTO> messageDTOList = new ArrayList<>();
        if (this.messagesList != null) {
            for (Message message : this.messagesList) {
                messageDTOList.add(message.toDTO());
            }
        }
        Collections.sort(messageDTOList, Comparator.comparing(MessageDTO::getDateTime));
        chatDTO.setMessages(messageDTOList);

        return chatDTO;
    }
}
