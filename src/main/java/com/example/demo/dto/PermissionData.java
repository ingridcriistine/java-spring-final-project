package com.example.demo.dto;

public record PermissionData(
    Long spaceID,
    Long userId,
    Boolean isAdmin
) {
    
}
