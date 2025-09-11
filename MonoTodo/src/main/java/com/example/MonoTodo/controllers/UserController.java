package com.example.MonoTodo.controllers;

import com.example.MonoTodo.dto.UserDto;
import com.example.MonoTodo.entities.Task;
import com.example.MonoTodo.entities.User;
import com.example.MonoTodo.services.TaskService;
import com.example.MonoTodo.services.UserService;
import lombok.Getter;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/users")
@RequiredArgsConstructor
public class UserController {

    private final UserService userService;
    private final TaskService taskService;

    @GetMapping()
    public List<UserDto> getAllUsers(){
        return userService.getAllUsers();
    }

    // Create new user
    @PostMapping
    public UserDto createUser(@RequestBody User user) {
        return userService.createUser(user);
    }

    // Get user by ID
    @GetMapping("/{id}")
    public UserDto getUserById(@PathVariable Long id) {
        return userService.getUserById(id);
    }

    // Create task for a user
    @PostMapping("/{id}/tasks")
    public UserDto createTaskForUser(@PathVariable Long id, @RequestBody Task task) {
        return userService.createTaskById(id, task);
    }
}
