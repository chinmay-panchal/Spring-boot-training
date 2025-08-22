package com.example.UserService.exceptions;

public class ResourceNotFoundException extends RuntimeException {
    
    public ResourceNotFoundException() {
        super("Resource not found");
    }

    public ResourceNotFoundException(String message) {
        super("Resource not found: " + (message != null ? message : "Unknown resource"));
    }  
}