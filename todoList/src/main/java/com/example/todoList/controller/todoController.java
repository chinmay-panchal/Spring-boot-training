package com.example.todoList.controller;


import com.example.todoList.dto.addTodoRequestDto;
import com.example.todoList.entity.TodoList;
import com.example.todoList.service.TodoService;
import jakarta.persistence.Id;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDate;
import java.util.List;
import java.util.Map;
import java.util.Objects;

@RestController
@RequestMapping("/todo")
@RequiredArgsConstructor
public class todoController {

    private final TodoService todoService;

    @GetMapping
    public ResponseEntity<List<TodoList>> getAllTasks(){
        return ResponseEntity.ok(todoService.getAllTasks());
    }

    @PostMapping
    public TodoList addNewTask(@RequestBody addTodoRequestDto task){
        return todoService.createNewTask(task);
    }

    @DeleteMapping
    public ResponseEntity<String> deleteAllTodos() {
        todoService.deleteAllTodos();
        return ResponseEntity.ok("All todos deleted.");
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<String> deleteTaskById(@PathVariable Long id){
        todoService.deleteTaskById(id);
        return ResponseEntity.ok("deleted the task of Id: "+id);
    }

    @GetMapping("/task/{id}")
    public ResponseEntity<TodoList> getTaskById(@PathVariable Long id){
        return ResponseEntity.ok(todoService.getTaskById(id));
    }

    @PutMapping("/{id}")
    public ResponseEntity<TodoList> editTaskById(@PathVariable Long id, @RequestBody addTodoRequestDto update){
        return ResponseEntity.ok(todoService.editTaskById(id, update));
    }

    @PatchMapping("/update/{id}")
    public ResponseEntity<TodoList> editPartialTask(@PathVariable Long id, @RequestBody Map<String, Object> update){
        return ResponseEntity.ok(todoService.editPartialTask(id, update));
    }
}