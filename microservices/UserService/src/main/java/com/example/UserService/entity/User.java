package com.example.UserService.entity;

import jakarta.persistence.*;
import lombok.*;

import java.util.ArrayList;
import java.util.List;

@Entity
@Table(name = "micro_users")
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class User {
    
    @Id
    @Column(name = "user_id")
    private String userId;
    
    @Column(name = "name", length = 50)
    private String name;
    
    @Column(name = "email", unique = true)
    private String email;
    
    @Column(name = "about", length = 255)
    private String about;

    @Transient
    private List<Rating> ratings = new ArrayList<>();
}