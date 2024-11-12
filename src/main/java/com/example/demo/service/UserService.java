package com.example.demo.service;

import com.example.demo.model.User;

public interface UserService {
    boolean checkPassword(String Password);
    User createUser(String edv,String email,String Password);
    User getUsers(Integer page,Integer size);
    User authUsers(String login,String password);
}