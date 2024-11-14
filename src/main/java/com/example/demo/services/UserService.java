package com.example.demo.services;

import java.util.List;

import com.example.demo.model.User;

public interface UserService {
    User createUser(String edv, String email, String Password);
    boolean checkPassword(String Password);
<<<<<<< HEAD
    List<User> getUsers(String email, String edv, Integer page, Integer size); /// Email e edv são da query e podem ser opcionais(null)
    User authUsers(String login, String password);
=======
    User createUser(String edv,String email,String name, String Password);
    List<User> getUsers(Integer page,Integer size); /// Email e edv são da query e podem ser opcionais(null)
    User authUsers(String login,String password);
>>>>>>> 11c0021cced064ad4fc947998408637261f36580
    User getById(long id); /// QUando tiver o jwt retorna o user só pe id
}