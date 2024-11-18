package com.example.demo.services;

import java.util.List;

import com.example.demo.model.User;

public interface UserService {
    boolean checkPassword(String Password);
    User createUser(String edv, String email,String name, String Password);
    List<User> getUsers(String query, Integer page, Integer size); /// Email e edv são da query e podem ser opcionais(null)
    User authUsers(String login, String password);
    public User getById(long id);
}