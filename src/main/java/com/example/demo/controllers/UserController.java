package com.example.demo.controllers;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.example.demo.dto.LoginData;
import com.example.demo.dto.UserData;
import com.example.demo.dto.UsersList;
import com.example.demo.model.User;
import com.example.demo.services.UserService;

@RestController
public class UserController {

    @Autowired
    UserService userService;

    @PostMapping("/user")
    public ResponseEntity<String> RegisterUser(UserData data){


        //? retorna caso qualquer campo seja falso
        if (data.edv().isEmpty() || data.email().isEmpty() || data.name().isEmpty() || data.password().isEmpty() ){ 
            return new ResponseEntity<>(HttpStatus.BAD_REQUEST); 
        }
        
 
        User newUser = userService.createUser(data.edv(), data.email(), data.name(), data.password());

            if (newUser == null) {
                return new ResponseEntity<>(HttpStatus.CONFLICT);
            }

            return new ResponseEntity<>(HttpStatus.OK);
        
        }

        @PostMapping("/auth")
        public ResponseEntity<String> Login(LoginData data){
    
    
            //? retorna caso qualquer campo seja falso
            if (data.edv().isEmpty() || data.password().isEmpty() ){ 
                return new ResponseEntity<>(HttpStatus.BAD_REQUEST); 
            }
     
            User Login = userService.authUsers(data.edv(),data.password());
    
            if (Login == null) {
                return new ResponseEntity<>(HttpStatus.CONFLICT);
            }
    
            return new ResponseEntity<>(HttpStatus.OK);
            
        }

            @GetMapping("/user")
            public ResponseEntity<UsersList> getUserLimited(@RequestParam(value = "page", defaultValue = "1") Integer page,
            @RequestParam(value = "size", defaultValue = "1") Integer size){
        
                List<User> Users = userService.getUsers(page,size);
        
                if (Users == null) {
                    return new ResponseEntity<UsersList>(new UsersList(null, "Not founded any Users"),HttpStatus.NO_CONTENT);
                }
        
                return new ResponseEntity<UsersList>(new UsersList(Users, "Queru sucessfull completed"),HttpStatus.OK);
                
            }
}
