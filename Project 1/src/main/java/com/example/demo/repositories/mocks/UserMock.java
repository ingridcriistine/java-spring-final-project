package com.example.demo.repositories.mocks;

import java.util.ArrayList;
import java.util.List;

import com.example.demo.model.User;
import com.example.demo.repositories.interfaces.UserInter;


public class UserMock implements UserInter{
    private ArrayList<User> list;

    public UserMock(){
        list = new ArrayList<>();
    }

    @Override
    public User findById(Long id){
        for (User user : list) {
            if (user.getId() == id)
                return user;
        }
        return null;
    }


    @Override
    public User createUser(User user) {
        list.add(user);
        return list.getLast();
    }

    @Override
    public List<User> findByEmailOrEdv(String login) {
        ArrayList<User> newList = new ArrayList<>();

        for (User user : list) {
            if(user.getEdv().equals(login) || user.getEmail().equals(login))
                newList.add(user);
        }

        return newList.isEmpty()?null:newList;
    }

    @Override
    public void deleteUser(User User) {
        list.remove(User);
    }

    @Override
    public User updateUser(User user) {
        for (User userList : list) {
            if(user.getId() == user.getId()){
                userList.setEdv(user.getEdv());
                userList.setEmail(user.getEmail());
                userList.setName(user.getName());
                userList.setPassword(user.getPassword());
                userList.setPermissions(user.getPermissions());
                return userList;
            }
        }
        return null;
    }

}
