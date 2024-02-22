package com.example.demo.service;

import com.example.demo.model.User;

public interface UserService {
    User getUserById(String id);
    User registerUser(String username, String password, String name, String surname);
}
