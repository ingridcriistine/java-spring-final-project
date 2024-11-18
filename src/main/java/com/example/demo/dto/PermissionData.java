package com.example.demo.dto;

public record PermissionData(
    Long spaceId,
    Long userId,
    Boolean isAdmin
) {
    
}