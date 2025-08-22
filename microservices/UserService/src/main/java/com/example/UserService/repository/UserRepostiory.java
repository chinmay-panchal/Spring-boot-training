package com.example.UserService.repository;

import com.example.UserService.entity.User;
import org.springframework.data.jpa.repository.JpaRepository;

public interface UserRepostiory extends JpaRepository<User, String> {
}
