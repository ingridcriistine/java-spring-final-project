package com.example.demo;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotEquals;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import com.example.demo.services.UserService;

@SpringBootTest
class DemoApplicationTests {

	@Autowired
	UserService service;

	@Test
    void validateInvalidPasswords() {
        assertEquals(service.checkPassword(""), false); //senha vazia
        assertEquals(service.checkPassword("abcd"), false); //não possui 12 caracteres
        assertEquals(service.checkPassword("123456789123"), false); //só possui números
        assertEquals(service.checkPassword(".,~´.;,.~´%%"), false); //possui caracteres especiais
        assertEquals(service.checkPassword("minhasenhaaaa"), false); //só possui letras
        assertEquals(service.checkPassword("minhasenha..."), false); //possui caracteres especiais
        assertEquals(service.checkPassword("minhasenha123"), false); //não possui letra maiúscula
        assertEquals(service.checkPassword("12345678910%%"), false); //não possui letras
        assertEquals(service.checkPassword("12345678AAAAA"), false); //não possui letras minúsculas
        assertEquals(service.checkPassword("12345678aaaaa"), false); //não possui letras maiúsculas
    }

    @Test
    void validateValidPasswords() {
        assertEquals(service.checkPassword("MinhaSenha12"), true); //tem 12 caracteres, letra maiúscula e minúscula
    }

	@Test
	void validateUser() {
		String edv = "123456";
		String email = "user@email.com";
		String senha = "MinhaSenha12";

		assertNotEquals(service.createUser(edv, email, senha), null); //o user será criado se o retorno não for igual a null
		assertNotEquals(service.authUsers(edv, senha), null); //será feita a autenticação do usuário se o retorno não for igual a null
		assertNotEquals(service.getUsers(1, 5), null); 
	}


}
