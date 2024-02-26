package com.example.demo.model.dto;

import lombok.Data;

import java.util.List;

@Data
public class UserDto {
    private String username;
    private String password;
    private String name;
    private String surname;
    private List<String> chats;
    private List<String> dogReports;
    private String token;


    public UserDto() {
    }

    public UserDto(String username, String password, String name, String surname) {
        this.username = username;
        this.password = password;
        this.name = name;
        this.surname = surname;
    }


}
