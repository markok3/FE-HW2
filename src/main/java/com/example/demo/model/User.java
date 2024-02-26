package com.example.demo.model;

import com.example.demo.model.dto.UserDto;
import jakarta.persistence.*;
import lombok.Data;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

@Data
@Entity
public class User {
    @Id
    private String username;

    private String password;

    private String name;

    private String surname;

    @ManyToMany(cascade = CascadeType.ALL, fetch = FetchType.EAGER)
    private List<Chat> chats;

    @OneToMany( cascade = CascadeType.ALL, fetch = FetchType.EAGER)
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

    public UserDto toDto() {
        UserDto dto = new UserDto();
        dto.setUsername(this.username);
        dto.setName(this.name);
        dto.setSurname(this.surname);
        dto.setChats(this.chats.isEmpty() ? new ArrayList<>() : this.chats.stream().map(Chat::getId).collect(Collectors.toList()));
        dto.setDogReports(dogReports.isEmpty() ? new ArrayList<>() : this.dogReports.stream().map(DogReport::getId).collect(Collectors.toList()));
        return dto;
    }

    @Override
    public String toString() {
        return "User{" +
                "username='" + username + '\'' +
                ", name='" + name + '\'' +
                ", surname='" + surname;

    }


}
