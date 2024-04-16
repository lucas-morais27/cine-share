package br.com.cineshare.controller;

import br.com.cineshare.entity.UserEntity;
import br.com.cineshare.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/users")
public class UserController {

    @Autowired
    private UserService userService;

    @GetMapping("/{id}")
    public UserEntity getUserById(@PathVariable Long id) {
        return userService.getUserById(id);
    }

    @PostMapping("/register")
    public UserEntity registerUser(@RequestBody UserEntity userEntity) {
        return userService.registerUser(userEntity);
    }

    // Outros métodos para atualizar, excluir, listar usuários, etc.
}
