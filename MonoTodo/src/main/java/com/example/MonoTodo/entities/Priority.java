package com.example.MonoTodo.entities;

import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Entity
@NoArgsConstructor
@AllArgsConstructor
@Setter
@Getter
public class Priority {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;
    private String priority;

    @OneToOne(mappedBy = "priority",cascade = CascadeType.PERSIST)
    @JsonIgnore
    Task task;
}