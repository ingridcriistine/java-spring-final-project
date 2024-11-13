package com.example.demo;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import com.example.demo.repositories.interfaces.UserInter;
import com.example.demo.repositories.mocks.UserMock;

@Configuration
public class DependencyConfiguration {
    @Bean
    public UserInter userInter(){
        return new UserMock();
    }
}
