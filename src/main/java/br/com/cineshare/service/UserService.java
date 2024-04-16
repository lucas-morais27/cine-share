package br.com.cineshare.service;

import br.com.cineshare.entity.UserEntity;
import br.com.cineshare.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class UserService {

    @Autowired
    private UserRepository userRepository;

    public UserEntity getUserById(Long id) {
        return userRepository.findById(id).orElse(null);
    }

    public UserEntity registerUser(UserEntity userEntity) {
        // Lógica para registrar um novo usuário
        return userRepository.save(userEntity);
    }

    // Outros métodos para atualizar, excluir, listar usuários, etc.
}
