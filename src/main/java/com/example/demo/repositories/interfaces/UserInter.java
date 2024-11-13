package com.example.demo.repositories.interfaces;

import java.util.List;

import com.example.demo.model.User;

public interface UserInter {
    User createUser(User user);  /// Criar user
    User findById(Long id);
    List<User> findByEmailOrEdv(String login); //// tanto email quanto edv
    void deleteUser(User user);
    User updateUser(User user);
}
