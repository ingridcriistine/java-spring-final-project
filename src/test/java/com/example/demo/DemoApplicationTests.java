package com.example.demo;

import static org.junit.jupiter.api.Assertions.assertEquals;

import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;

@SpringBootTest
class DemoApplicationTests {

	@Test
    void validateInvalidPasswords() {
        assertEquals(Validator.ValidatePassword(""), false); //senha vazia
        assertEquals(Validator.ValidatePassword("abcd"), false); //não possui 8 caracteres
        assertEquals(Validator.ValidatePassword("12345678"), false); //só possui números
        assertEquals(Validator.ValidatePassword(".,~´.;,.~´%%"), false); //só possui caracteres especiais
        assertEquals(Validator.ValidatePassword("minhasenha"), false); //só possui letras
        assertEquals(Validator.ValidatePassword("minhasenha..."), false); //não possui números
        assertEquals(Validator.ValidatePassword("minhasenha123"), false); //não possui caracteres especiais
        assertEquals(Validator.ValidatePassword("123456%%"), false); //não possui letras
    }

    @Test
    void validateValidPasswords() {
        assertEquals(Validator.ValidatePassword("minha.senha123"), true);
        assertEquals(Validator.ValidatePassword("senhaemoji.👻123"), true);
        assertEquals(Validator.ValidatePassword("oi2005.senha"), true);
        assertEquals(Validator.ValidatePassword(".oi.oi.oi123456"), true);
        
    }

}
