package com.example.demo.controllers;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.example.demo.dto.LoginData;
import com.example.demo.dto.SecurityToken;
import com.example.demo.dto.Token;
import com.example.demo.dto.UserData;
import com.example.demo.dto.UsersList;
import com.example.demo.model.User;
import com.example.demo.services.JWTService;
import com.example.demo.services.UserService;

@RestController
public class UserController {

    @Autowired
    UserService userService;

    @Autowired
    JWTService<Token> jwtService;

    @Autowired
    PasswordEncoder encoder;

    @PostMapping("/user")
    public ResponseEntity<String> RegisterUser(@RequestBody UserData data) {

        // ? retorna caso qualquer campo seja vazio
        if (data.edv().isEmpty() || data.email().isEmpty() || data.name().isEmpty() || data.password().isEmpty()) {
            return new ResponseEntity<>("Fill all inputs", HttpStatus.BAD_REQUEST);
        }

        if (!userService.checkPassword(data.password())) {
            return new ResponseEntity<>("Password don't have certain criteria", HttpStatus.BAD_REQUEST);
        }

        User newUser = userService.createUser(data.edv(), data.email(), data.name(), data.password());

        // ! VEJA A IMPLEMENTAÇÃO ANTES DE ME XINGAR
        if (newUser == null) {
            return new ResponseEntity<>("Already exist this user", HttpStatus.CONFLICT);
        }

        return new ResponseEntity<>("Sign up with sucess", HttpStatus.OK);

    }

    @PostMapping("/auth")
    public ResponseEntity<SecurityToken> Login(@RequestBody LoginData data) {

        // ? retorna caso qualquer campo seja falso
        if (data.edv().isEmpty() || data.password().isEmpty()) {
            return new ResponseEntity<>(new SecurityToken(null, "Fill all inputs"), HttpStatus.BAD_REQUEST);
        }

        User Login = userService.authUsers(data.edv(),data.password() );

        if (Login == null) {
            return new ResponseEntity<>(new SecurityToken(null, "User don't exist"),
                    HttpStatus.CONFLICT);
        }

        if (!encoder.matches(data.password(), Login.getPassword())) {
            return new ResponseEntity<>(new SecurityToken(null, "Invalid password"),
                    HttpStatus.CONFLICT);
        }

        Token token = new Token();
        token.setId(Login.getId());

        var jwt = jwtService.get(token);

        return new ResponseEntity<>(new SecurityToken(jwt, "Sign In With Sucess"), HttpStatus.OK);

    }

    @GetMapping("/user")
    public ResponseEntity<UsersList> getUserLimited(
        @RequestParam(value = "query", defaultValue = "") String query, 
        @RequestParam(value = "page", defaultValue = "1") Integer page, 
        @RequestParam(value = "size", defaultValue = "1") Integer size ) {

        List<User> Users = userService.getUsers(query, page, size);
        System.out.println(Users.size());
        if (Users.size() == 0) {
            return new ResponseEntity<UsersList>(new UsersList(null, "Not founded any Users"), HttpStatus.CONFLICT);
        }

        return new ResponseEntity<UsersList>(new UsersList(Users, "Query sucessfull completed"), HttpStatus.OK);

    }
}
