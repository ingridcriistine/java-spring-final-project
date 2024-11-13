package com.example.demo.repositories.Default;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;

import com.example.demo.model.User;
import com.example.demo.repositories.UserRepository;
import com.example.demo.repositories.interfaces.UserInter;

public class UserRepo implements UserInter{

    @Autowired
    UserRepository repo;

    @Override
    public User createUser(User user) {
        return repo.save(user);
    }

    @Override
    public User findById(Long id) {
        var res = repo.findById(id);
        return res.isEmpty()?null: res.get();
    }

    @Override
    public List<User> findByEmailOrEdv(String login) {
        return repo.login(login);
    }

    @Override
    public void deleteUser(User user) {
        repo.delete(user);
    }

    @Override
    public User updateUser(User user) {
        return repo.save(user);
    }
    
}
