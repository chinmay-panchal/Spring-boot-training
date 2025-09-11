package com.example.MonoTodo.controllers;

import com.example.MonoTodo.entities.Task;
import com.example.MonoTodo.services.TaskService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/tasks")
@RequiredArgsConstructor
public class TaskController {

    private final TaskService taskService;

    // Create task with userId
    @PostMapping("/user/{userId}")
    public Task createTask(@PathVariable Long userId, @RequestBody Task task) {
        return taskService.createTask(userId, task);
    }

    // Get all tasks
    @GetMapping
    public List<Task> getAllTasks() {
        return taskService.getAllTasks();
    }
}
