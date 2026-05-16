package com.junior.projeto1.controller;

import com.junior.projeto1.dto.UserRequestDTO;
import com.junior.projeto1.dto.UserResponseDTO;
import com.junior.projeto1.entity.User;
import com.junior.projeto1.service.UserService;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/usuarios")
public class UserController {
    private final UserService userService;

    public UserController(UserService userService) {
        this.userService = userService;
    }

    @PostMapping
    public UserResponseDTO criar(@RequestBody UserRequestDTO usuarioDTO) {
        return userService.salvarUsuario(usuarioDTO);
    }

    @PostMapping("/lote")
    public List<User> criarLote(@RequestBody List<User> usuarios) {
        return userService.salvarLista(usuarios);
    }

    @GetMapping
    public List<User> listar() {
        return userService.listarUsuarios();
    }

    @GetMapping("/{id}")
    public User buscarPorId(@PathVariable Long id){
        return userService.buscarPorId(id);
    }

    @PutMapping("/{id}")
    public User atualizarUsuario(@PathVariable Long id, @RequestBody User usuarioAtualizado) {
        return userService.atualizarUsuario(id, usuarioAtualizado);
    }
    @DeleteMapping("/{id}")
    public void deletarUsuario(@PathVariable Long id) {
        userService.deletarUsuario(id);
    }
}
