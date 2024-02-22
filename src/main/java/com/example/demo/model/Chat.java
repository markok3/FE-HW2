package com.example.demo.model;

import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.ManyToMany;
import jakarta.persistence.OneToMany;
import lombok.Data;

import java.util.List;

@Entity
@Data
public class Chat {


    @Id
    private String id;

    @ManyToMany
    private List<User> users;

    @OneToMany
    private List<Message> messagesList;

    private String firstParticipantId;

    private String secondParticipantId;

    public Chat() {
    }
}
