package com.example.demo;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.HttpMethod; // Importe HttpMethod
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;
import org.springframework.web.cors.CorsConfiguration;

import com.example.demo.dto.Token;
import com.example.demo.filters.JWTAuthenticationFilter;
import com.example.demo.services.JWTService;

@Configuration
@EnableWebSecurity
public class SecurityConfiguration {

    @Autowired
    JWTService<Token> jwtService;

    @Bean
    public SecurityFilterChain securityFilterChain(HttpSecurity http) throws Exception {
        return http
            .csrf(csrf -> csrf.disable())
            .cors(cors -> cors.configurationSource(request -> {
                CorsConfiguration config = new CorsConfiguration();
                config.setAllowedOrigins(List.of("http://localhost:3000"));
                config.setAllowedMethods(List.of("*"));  // ou List.of("GET", "POST", "PUT", "DELETE", "OPTIONS")
                config.setAllowedHeaders(List.of("*"));
                config.setAllowCredentials(true); // Se necessário
                return config;
            }))
            .authorizeHttpRequests(authorize -> authorize
                .requestMatchers("/user").permitAll() //Especifica o método POST aqui
                .requestMatchers("/auth").permitAll() //Especifica o método POST aqui
                .anyRequest().authenticated() // Outras requisições precisam de autenticação
            )
            .addFilterBefore(new JWTAuthenticationFilter(jwtService), UsernamePasswordAuthenticationFilter.class)
            .build();
    }
}