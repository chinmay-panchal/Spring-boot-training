package com.example.todoList.repository;

import com.example.todoList.entity.TodoList;
import org.springframework.data.jpa.repository.JpaRepository;

public interface TodoRepository extends JpaRepository<TodoList, Long> {
}
