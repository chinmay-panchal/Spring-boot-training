package com.example.MonoTodo.dto;

import com.example.MonoTodo.entities.Task;
import lombok.Data;

import java.util.List;

@Data
public class UserDto {
    Long id;
    String name;
    String email;
    private List<TaskDto> tasks;
}
