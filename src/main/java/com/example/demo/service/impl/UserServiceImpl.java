package com.example.demo.service.impl;

import com.example.demo.model.User;
import com.example.demo.repository.UserRepository;
import com.example.demo.service.UserService;
import org.springframework.stereotype.Service;

@Service
public class UserServiceImpl implements UserService {
    private final UserRepository userRepository;

    public UserServiceImpl(UserRepository userRepository) {
        this.userRepository = userRepository;
    }

    @Override
    public User getUserById(String id) {
        return userRepository.getById(id);
    }

    @Override
    public User registerUser(String username, String password, String name, String surname) {
        if(userRepository.existsUserByUsername(username)){
            throw new RuntimeException("User Exists");
        }
        User newUser = userRepository.save(new User(username,password,name,surname));

        return newUser;
    }
}
