package com.example.demo.services;

import java.util.List;

import com.example.demo.model.User;

public interface UserService {
    boolean checkPassword(String Password);
<<<<<<< HEAD
    User createUser(String edv, String email, String Password);
    User getUsers(Integer page, Integer size);
    User authUsers(String login, String password);
=======
    User createUser(String edv,String email,String Password);
    List<User> getUsers(String email,String edv,Integer page,Integer size); /// Email e edv são da query e podem ser opcionais(null)
    User authUsers(String login,String password);
    User getById(long id); /// QUando tiver o jwt retorna o user só pe id
>>>>>>> 27fa51104656d7939724e951d5ed61f1a0b3d361
}