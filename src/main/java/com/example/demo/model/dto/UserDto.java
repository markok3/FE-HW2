package com.example.demo.model.dto;

import lombok.Data;

@Data
public class UserDto {
    private String username;
    private String password;
    private String name;
    private String surname;


    public UserDto() {
    }

    public UserDto(String username, String password, String name, String surname) {
        this.username = username;
        this.password = password;
        this.name = name;
        this.surname = surname;
    }


}
