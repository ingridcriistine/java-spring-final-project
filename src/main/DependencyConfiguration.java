package com.example.demo;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Scope;

import com.example.demo.dto.Token;
import com.example.demo.impl.BcryptPasswordEncoderImpl;
import com.example.demo.impl.ExampleImaginaryExponential;
import com.example.demo.impl.ExampleLoginService;
import com.example.demo.impl.ExampleReverseService;
import com.example.demo.impl.JwtTokenImpl;
import com.example.demo.impl.ProductImpl;
import com.example.demo.impl.UserSecurityImpl;
import com.example.demo.services.ImaExpService;
import com.example.demo.services.JwtTokenService;
import com.example.demo.services.LoginService;
import com.example.demo.services.PasswordEncoderService;
import com.example.demo.services.ProductService;
import com.example.demo.services.ReverseService;
import com.example.demo.services.UserService;

@Configuration
public class DependencyConfiguration {
    
}
