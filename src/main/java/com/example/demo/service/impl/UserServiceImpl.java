package com.example.demo.service.impl;

import com.example.demo.model.Chat;
import com.example.demo.model.User;
import com.example.demo.repository.UserRepository;
import com.example.demo.service.UserService;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class UserServiceImpl implements UserService, UserDetailsService {
    private final UserRepository userRepository;

    public UserServiceImpl(UserRepository userRepository) {
        this.userRepository = userRepository;
    }

    @Override
    public User getUserById(String id) {
        User user = userRepository.getById(id);
        return user;
    }

    @Override
    public User addChatToUser(String userId, Chat chat){
        User user = userRepository.getById(userId);
        user.getChats().add(chat);
        userRepository.save(user);
        return user;
    }

    @Override
    public User registerUser(String username, String password, String name, String surname) {
        if(userRepository.existsUserByUsername(username)){
            throw new RuntimeException("User Exists");
        }
        User newUser = userRepository.save(new User(username,password,name,surname));

        return newUser;
    }

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        User user = userRepository.findByUsername(username);
        List<String> roles = new ArrayList<>();
        roles.add("USER");
        UserDetails userDetails =
                org.springframework.security.core.userdetails.User.builder()
                        .username(user.getUsername())
                        .password(user.getPassword())
                        .roles(roles.toArray(new String[0]))
                        .build();
        return userDetails;
    }
}
