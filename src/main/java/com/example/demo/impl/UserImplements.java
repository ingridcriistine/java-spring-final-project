package com.example.demo.impl;

import java.util.Collections;
import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;

import com.example.demo.model.User;
import com.example.demo.repositories.UserRepository;
import com.example.demo.services.UserService;

public class UserImplements implements UserService {

    @Autowired
    UserRepository repo;



    //! OS RETORNOS NULOS SÃO POR QUE NÃO CONSIGO MANDAR NADA QUE NÃO SEJA ELE PRO CONTROLLER, NÃO ME XINGUE
    //? Agradeço a compreensão 

    @Override
    public boolean checkPassword(String Password) {

        if (Password.length() < 12) {
            return false;
        }

        //? Regex que testa se tem Minuscula, Maiuscula e Numerico em qualquer parte da String, veja em Regex101.com se quiser testar
        if (!Password.matches("^(?=.*[a-z])(?=.*[A-Z])(?=.*[1-9]).+$")) {
            return false;
        }

        return true;
    }

    @Override
    public User createUser(String edv, String email,String name, String Password ) {
        
        if (!repo.findByEmail(email).isEmpty()) {
            return null;
        }

        if (!repo.findByEdv(edv).isEmpty()) {
            return null;
        }

        User newUser = new User();
        var encoder = new BCryptPasswordEncoder();

        newUser.setEdv(edv);
        newUser.setEmail(email);
        newUser.setName(name);
        //? Salvado com BCrypt
        newUser.setPassword(encoder.encode(Password));
        repo.save(newUser);
        return newUser;

    }

    @Override
    public List<User> getUsers(String query, Integer page, Integer size) {
        List<User> Users = repo.findUsersWithPagination(query,(page-1)* size, size);
        
        if (Users.size() == 0) {
            return Collections.emptyList();
        }
        
        return Users;
    }

    @Override
    public User authUsers(String login, String password) {
        
        if (!existUserByEdv(login)) {
            return null;
        }

        User auth = repo.findByEdv(login).get(0);

        return auth;
    }

    private boolean existUserByEdv(String Edv) {
        return !repo.findByEdv(Edv).isEmpty();
    }

    @Override
    public User getById(long id) {

        Optional<User> user = repo.findById(id);
    
        if (user.isEmpty()) {
            return null;
        }

        return user.get();

    }
}
