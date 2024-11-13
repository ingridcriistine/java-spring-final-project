package com.example.demo.services;

import java.util.List;

import com.example.demo.model.Space;
import com.example.demo.model.User;

public interface SpaceService {
    Space createSpace(String name, User user); /// criar o space vazio e ja adicionar a permissao do cara que criou!
    List<Space> getSpaces(String name, Integer page, Integer size);///nome faz parte da query e pode ser opcional(null)
    boolean isOwner(Long idSpace, User user); /// retorna se user é dono do Space!!
    boolean deleteSpace(Long id); // id do que vai ser excluido e user para verificar o se é real dono do Space!!
    Space getSpace(Long id);/// retorna o space pelo id, usa pra fazer as coisas!!!
}
