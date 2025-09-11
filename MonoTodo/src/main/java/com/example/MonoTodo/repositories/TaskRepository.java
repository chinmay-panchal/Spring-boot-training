package com.example.MonoTodo.repositories;

import com.example.MonoTodo.entities.Task;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface TaskRepository extends JpaRepository<Task, String> {

    List<Task> findByUserId(Long userId);
}
