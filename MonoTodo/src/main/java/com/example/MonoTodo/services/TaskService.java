package com.example.MonoTodo.services;

import com.example.MonoTodo.entities.Priority;
import com.example.MonoTodo.entities.Task;
import com.example.MonoTodo.entities.User;
import com.example.MonoTodo.repositories.TaskRepository;
import com.example.MonoTodo.repositories.UserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class TaskService {

    private final TaskRepository taskRepository;
    private final UserRepository userRepository;

    // Create task for a user
    public Task createTask(Long userId, Task task) {
        // Fetch existing user from DB
        User user = userRepository.findById(userId)
                .orElseThrow(() -> new IllegalArgumentException("User not found"));

//         Set user on task
        task.setUser(user);

        user.getTask().add(task);

        // Save task
        return taskRepository.save(task);
    }

//     Get all tasks for a user
    public List<Task> getTasksByUser(Long userId) {
        return taskRepository.findByUserId(userId);
    }

    // Get all tasks
    public List<Task> getAllTasks() {
        return taskRepository.findAll();
    }
}
