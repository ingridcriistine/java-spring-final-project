package com.example.demo;

public class Validator {
    
    public static boolean ValidatePassword(String password) {

        if (password == null || password.isEmpty()) {
            return false;
        }

        if (password.length() < 12)
            return false;
        
        return password.chars()
            .anyMatch(c -> c >= '0' && c <= '9');
    }
}