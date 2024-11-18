package com.example.demo;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;

import com.example.demo.dto.Token;
import com.example.demo.impl.AnswerImpl;
import com.example.demo.impl.DefaultJWTService;
import com.example.demo.impl.PermissionImplements;
import com.example.demo.impl.QuestionImpl;
import com.example.demo.impl.SpaceImple;
import com.example.demo.impl.UserImplements;
import com.example.demo.repositories.interfaces.UserInter;
import com.example.demo.repositories.mocks.UserMock;
import com.example.demo.services.AnswerService;
import com.example.demo.services.JWTService;
import com.example.demo.services.PermissionService;
import com.example.demo.services.QuestionService;
import com.example.demo.services.SpaceService;
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

    @Bean
    public SpaceService spaceService(){
        return new SpaceImple();
    }
    
    @Bean
    public PermissionService permissionService(){
        return new PermissionImplements();
    }

    @Bean
    public AnswerService answerService(){
        return new AnswerImpl();
    }
    
        @Bean
    public PasswordEncoder passwordEncoder() {
        return new BCryptPasswordEncoder(8);
    }

    @Bean
    public JWTService<Token> jwtService() {
        return new DefaultJWTService();
    }
}
