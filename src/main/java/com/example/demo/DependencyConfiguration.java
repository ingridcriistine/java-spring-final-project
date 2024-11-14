package com.example.demo;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import com.example.demo.impl.QuestionImpl;
import com.example.demo.impl.UserImplements;
import com.example.demo.repositories.interfaces.UserInter;
import com.example.demo.repositories.mocks.UserMock;
import com.example.demo.services.QuestionService;
import com.example.demo.services.UserService;

@Configuration
public class DependencyConfiguration {
    @Bean
    public UserInter userInter(){
        return new UserMock();
    }

    @Bean
    public QuestionService questionService(){
        return new QuestionImpl();
    }

    @Bean
    public UserService userService(){
        return new UserImplements();
    }
}
