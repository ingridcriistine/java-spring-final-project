package com.example.demo;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotEquals;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import com.example.demo.model.User;
import com.example.demo.services.SpaceService;
import com.example.demo.services.UserService;

@SpringBootTest
class DemoApplicationTests {

	@Autowired
	UserService userService;

	@Autowired
	SpaceService spaceService;

	@Test
    void validatePassword() {
        assertEquals(userService.checkPassword(""), false); //senha vazia
        assertEquals(userService.checkPassword("abcd"), false); //não possui 12 caracteres
        assertEquals(userService.checkPassword("123456789123"), false); //só possui números
        assertEquals(userService.checkPassword(".,~´.;,.~´%%"), false); //possui caracteres especiais
        assertEquals(userService.checkPassword("minhasenhaaaa"), false); //só possui letras
        assertEquals(userService.checkPassword("minhasenha..."), false); //possui caracteres especiais
        assertEquals(userService.checkPassword("minhasenha123"), false); //não possui letra maiúscula
        assertEquals(userService.checkPassword("12345678910%%"), false); //não possui letras
        assertEquals(userService.checkPassword("12345678AAAAA"), false); //não possui letras minúsculas
        assertEquals(userService.checkPassword("12345678aaaaa"), false); //não possui letras maiúsculas
        assertEquals(userService.checkPassword("MinhaSenha12"), true); //tem 12 caracteres, letra maiúscula e minúscula
    }

	@Test
	void validateUser() {
		String edv = "123456";
		String email = "user@email.com";
		String senha = "MinhaSenha12";

		assertNotEquals(userService.createUser(edv, email, senha), null); //o user será criado se o retorno não for igual a null
		assertNotEquals(userService.authUsers(edv, senha), null); //será feita a autenticação do usuário se o retorno não for igual a null
		assertNotEquals(userService.getUsers(email, edv, 1, 5), null); //irá retornar o user se não for igual a null
		assertNotEquals(userService.getById(0), null); //irá retornar o user se não for igual a null
	}

	@Test
	void validateSpace() {
		String name = "Teste";
		var user = new User();
		var space = spaceService.createSpace(name, user);

		assertEquals(spaceService.deleteSpace(space.getId()), true); //o space será deletado se o retorno for igual a true
		assertNotEquals(spaceService.getSpaces(name, 1, 5), null); //irá retornar o space se não for igual a null
	}

	@Test 
	void validatePermission() {
		
	}

	@Test 
	void validateQuestion() {

	}

	@Test 
	void validateAnswer() {
		
	}


}
