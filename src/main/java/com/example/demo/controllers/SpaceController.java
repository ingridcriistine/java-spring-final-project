package com.example.demo.controllers;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestAttribute;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.example.demo.dto.QuestionData;
import com.example.demo.dto.SpaceData;
import com.example.demo.dto.SpaceList;
import com.example.demo.dto.SpaceReturn;
import com.example.demo.dto.Token;
import com.example.demo.model.Question;
import com.example.demo.model.Space;
import com.example.demo.repositories.PermissionRepository;
import com.example.demo.repositories.UserRepository;
import com.example.demo.services.SpaceService;

@RestController
@RequestMapping("/spaces")
public class SpaceController {
    
    @Autowired
    SpaceService service;

    @Autowired
    UserRepository user;

    @Autowired
    PermissionRepository PermissionRepo;

    @PostMapping
    public ResponseEntity<String> create(@RequestAttribute("token") Token token, @RequestBody SpaceData data){
        if(service.createSpace(data.name(), user.findById(token.getId()).get()) == null){
            return new ResponseEntity<>("Esse nome de espaço já existe!", HttpStatus.OK);
        }

        service.createSpace(data.name(), user.findById(token.getId()).get());

        return new ResponseEntity<>("Espaço criado com sucesso!", HttpStatus.OK);
    }

    @GetMapping
    public ResponseEntity<SpaceList> getSpaces(@RequestParam(value = "name", defaultValue = "null")  String name, @RequestParam(value = "page", defaultValue = "1") Integer page, @RequestParam(value = "size", defaultValue = "1") Integer size) {
        
        List<Space> Spaces = service.getSpaces(name, page, size);

        if(Spaces == null){
            return new ResponseEntity<>(new SpaceList(null, "Espaço não encontrado"), HttpStatus.OK);
        }
        List<SpaceReturn> data = new ArrayList<>();
        for (Space space : Spaces) {
                data.add(new SpaceReturn(space.getId(), space.getName()));
            }

        return new ResponseEntity<>(new SpaceList(data, "Pesquisa finalizada!"), HttpStatus.OK);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<String> delete(@RequestAttribute("token") Token token, @PathVariable Long id){
        
        var permission = PermissionRepo.findByUserId(token.getId());

        if(permission.isEmpty() || permission.get(0).getIsAdmin() == false){
            return new ResponseEntity<>("Voce não possui permissão para deletar o espaço", HttpStatus.OK);
        }

        if(service.deleteSpace(id)){
            return new ResponseEntity<>("Espaço deletado!", HttpStatus.ACCEPTED);
        }
            
        return new ResponseEntity<>("Não foi possível deletar esse Espaço!", HttpStatus.BAD_REQUEST);
    }
}
