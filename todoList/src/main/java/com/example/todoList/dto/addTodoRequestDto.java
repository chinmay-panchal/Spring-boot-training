package com.example.todoList.dto;

import com.example.todoList.entity.Priority;
import com.example.todoList.entity.Status;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;
import lombok.Data;

@Data
public class addTodoRequestDto {
    @NotBlank(message = "Title is Required")
    @Size(max=10, message = "Title must not have more length than 10 characters")
    String title;
    String description;

    @NotNull(message = "Priority is required")
    Priority priority;
}
