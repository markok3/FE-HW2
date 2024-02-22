package com.example.demo.model;

import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.ManyToMany;
import jakarta.persistence.OneToMany;
import lombok.Data;

import java.util.ArrayList;
import java.util.List;

@Data
@Entity
public class User {
    @Id
    private String username;

    private String password;

    private String name;

    private String surname;

    @ManyToMany
    private List<Chat> chats;

    @OneToMany
    private List<DogReport> dogReports;

    public User() {
    }


    public User(String username, String password, String name, String surname) {
        this.username = username;
        this.password = password;
        this.name = name;
        this.surname = surname;
        this.chats = new ArrayList<>();
        this.dogReports = new ArrayList<>();
    }
}
