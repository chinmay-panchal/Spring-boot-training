package com.example.todoList.service;


import com.example.todoList.dto.addTodoRequestDto;
import com.example.todoList.entity.TodoList;

import java.util.List;
import java.util.Map;

public interface TodoService {
    List<TodoList> getAllTasks();
    TodoList getTaskById(Long id);

    TodoList createNewTask(addTodoRequestDto task);

    void deleteTaskById(Long id);

    TodoList editTaskById(Long id, addTodoRequestDto update);

    TodoList editPartialTask(Long id, Map<String, Object> updates);

    void deleteAllTodos();
}