package com.example.demo.services;

import java.util.List;

import com.example.demo.model.Space;
import com.example.demo.model.User;

public interface SpaceService {
    List<Space> getSpaces(String name,Integer page,Integer size);///nome faz parte da query e pode ser opcional(null)
    Space createSpace(String name,User user); /// criar o space vazio e ja adiconar a permissao do cara que criou!
    boolean deleteSpace(Long id); // id do que vai ser excluido e user para verificar o se é real dono do Space!!
    boolean isOwner(long idSpace,User user); /// retorna se user é dono do Space!!
}
