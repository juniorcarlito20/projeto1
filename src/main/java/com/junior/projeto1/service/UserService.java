package com.junior.projeto1.service;

import com.junior.projeto1.entity.User;
import com.junior.projeto1.repository.UserRepository;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class UserService {
    private final UserRepository userRepository;

    public UserService(UserRepository userRepository) {
        this.userRepository = userRepository;
    }

    // Metodo de validação
    private void validarUsuario(User user) {
        // 1. Usuário não pode ser nulo
        if (user == null) {
            throw new IllegalArgumentException("Usuário não pode ser nulo");
        }

        // 2. Campos obrigatórios não podem ser vazios/nulos
        if (user.getName() == null || user.getName().isBlank()) {
            throw new IllegalArgumentException("Nome do usuário é obrigatório");
        }

        if (user.getEmail() == null || user.getEmail().isBlank()) {
            throw new IllegalArgumentException("E-mail é obrigatório");
        }

        // 3. Validação de formato de e-mail (simples)
        String email = user.getEmail();
        if (email == null || email.isBlank() || !email.contains("@")) {
            throw new IllegalArgumentException("E-mail inválido");
        }


    }

    // Metodo para validar e salvar um usuário
    public User salvarUsuario(User user) {
        validarUsuario(user); // Valida antes de salvar
        return userRepository.save(user); // Salva no banco e retorna o usuário salvo
    }

    public List<User> listarUsuarios() {
        return userRepository.findAll();
    }

    // Metodo para validar e salvar varios usuários de uma so vez
    public List<User> salvarLista(List<User> users) {
        for (User user : users) {
            validarUsuario(user);  // Valida cada um
        }
        return userRepository.saveAll(users);  // Salva todos os usuários e retorna a lista salva
    }

    //metodo para buscar todos os usuários
    public List<User> listarTodos() {
    return userRepository.findAll();
    }
    //metodo para buscar usuário por ID
    public User buscarPorId(Long id) {
        return userRepository.findById(id).orElse(null) ;
        // findById busca no banco de dados e orElse(null) retorna usuario encontrado ou inexistente.
    }
    // metodo para atualizar usuario
    public User atualizarUsuario (long id , User usuarioAtualizado){
        User user = userRepository.findById(id).orElseThrow(()-> new RuntimeException("Usuário não encontrado."));
        user.setName(usuarioAtualizado.getName());
        user.setEmail(usuarioAtualizado.getEmail());
        return userRepository.save(user);
    }

    // metodo para deletar usuario
    public void deletarUsuario(Long id) {
        User user = userRepository.findById(id).orElseThrow(() -> new RuntimeException("Usuário não encontrado."));
        userRepository.delete(user);
    }
}



