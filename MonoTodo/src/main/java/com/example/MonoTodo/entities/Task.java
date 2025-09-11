package com.example.MonoTodo.entities;

import com.example.MonoTodo.enums.TaskStatus;
import com.fasterxml.jackson.annotation.JsonBackReference;
import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.persistence.*;
import jdk.jshell.Snippet;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.UUID;

@Entity
@Setter
@Getter
@AllArgsConstructor
@NoArgsConstructor
public class Task {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private UUID id;

    private String name;

    @Enumerated(EnumType.STRING)
    private TaskStatus status = TaskStatus.PENDING;

    @ManyToOne
    @JoinColumn(name = "user_id")   // many tasks can belong to one user
    private User user;

    @ManyToOne
    @JoinColumn(name = "priority_id")  // many tasks can have the same priority
    private Priority priority;
}
