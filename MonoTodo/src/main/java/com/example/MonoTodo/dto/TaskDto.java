package com.example.MonoTodo.dto;

import lombok.Data;

import java.util.UUID;

@Data
    public class TaskDto {
        private UUID id;
        private String title;
        private String status;
        private String priority;
        // no User field here (to avoid recursion), @JsonIgnore
    }

