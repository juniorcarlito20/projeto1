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

        // Adicione outras regras de negócio aqui...
    }

    // metodo para salvar usuário
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
}



