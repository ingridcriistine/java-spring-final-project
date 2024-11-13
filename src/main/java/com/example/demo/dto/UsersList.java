package com.example.demo.dto;

import java.util.List;

import com.example.demo.model.User;

public record UsersList(
    List<User> Users,
    String message
) {
    
}
