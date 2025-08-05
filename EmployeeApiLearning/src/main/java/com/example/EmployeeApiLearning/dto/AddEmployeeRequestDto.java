package com.example.EmployeeApiLearning.dto;

import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;
import lombok.Data;

@Data
public class AddEmployeeRequestDto {

    @Size(min=3, max=30, message = "Name should be of length 3 to 30 characters")
    @NotBlank(message = "Name is required")
    private String name;

    @Email
    @NotBlank(message = "Email is Required")
    private String email;

    @Size(min = 6, message = "Password must be at least 6 characters")
    @NotBlank(message = "Password is required")
    private String password;
}
