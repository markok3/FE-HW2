package com.example.demo.service;

import com.example.demo.model.Chat;
import com.example.demo.model.User;
import org.springframework.security.core.userdetails.UserDetailsService;

public interface UserService extends UserDetailsService {
    User getUserById(String id);
    User registerUser(String username, String password, String name, String surname);
    User addChatToUser(String userId, Chat chat);
}
