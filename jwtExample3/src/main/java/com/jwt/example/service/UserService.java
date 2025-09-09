package com.jwt.example.service;

import com.jwt.example.entities.User;
import com.jwt.example.repositories.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

@Service
public class UserService {
//    private List<User> store = new ArrayList<>();

//    public UserService(){
//        store.add(new User(UUID.randomUUID().toString(), "Chinmay Panchal1", "Chinmay1@dev.in"));
//        store.add(new User(UUID.randomUUID().toString(), "Chinmay Panchal2", "Chinmay2@dev.in"));
//        store.add(new User(UUID.randomUUID().toString(), "Chinmay Panchal4", "Chinmay3@dev.in"));
//        store.add(new User(UUID.randomUUID().toString(), "Chinmay Panchal4", "Chinmay4@dev.in"));
//    }

//    public List<User> getUser(){
//        return this.store;
//    }

    // now for the databse users
    @Autowired
    private UserRepository userRepository;

    @Autowired
    private PasswordEncoder passwordEncoder;

    public List<User> getUsers(){
        return userRepository.findAll();
    }

    public User createUser(User user){
        user.setUserId(UUID.randomUUID().toString());
        user.setPassword(passwordEncoder.encode(user.getPassword()));
        return userRepository.save(user);
    }
}
