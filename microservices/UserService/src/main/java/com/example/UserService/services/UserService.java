package com.example.UserService.services;

import com.example.UserService.entity.User;

import java.util.List;

public interface UserService {
    User saveUser(User user);
    List<User> getAllUsers();
    User getUserById(String userId);
    User updateUser(User user, String userId);
    void deleteUser(String userId);
}
