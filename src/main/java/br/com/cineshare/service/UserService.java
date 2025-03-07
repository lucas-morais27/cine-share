package br.com.cineshare.service;

import br.com.cineshare.dto.UserRequestDTO;
import br.com.cineshare.dto.UserResponseDTO;
import br.com.cineshare.entity.UserEntity;
import br.com.cineshare.repository.UserRepository;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
public class UserService {

    private final UserRepository userRepository;

    public UserService(UserRepository userRepository) {
        this.userRepository = userRepository;
    }

    /**
     * Cria um novo usuário no sistema.
     */
    public UserResponseDTO createUser(UserRequestDTO userRequest) {
        UserEntity user = new UserEntity();
        user.setName(userRequest.getName());
        user.setEmail(userRequest.getEmail());
        user.setPassword(userRequest.getPassword()); // 🔹 A senha deveria ser criptografada antes de salvar
        user.setProfilePicture(userRequest.getProfilePicture());
        user.setBio(userRequest.getBio());

        UserEntity savedUser = userRepository.save(user);
        return toResponseDTO(savedUser);
    }

    /**
     * Retorna todos os usuários no formato de DTO.
     */
    public List<UserResponseDTO> getAllUsers() {
        return userRepository.findAll()
                .stream()
                .map(this::toResponseDTO)
                .collect(Collectors.toList());
    }

    /**
     * Busca um usuário pelo ID e retorna um Optional.
     */
    public Optional<UserResponseDTO> getUserById(Long id) {
        return userRepository.findById(id).map(this::toResponseDTO);
    }

    /**
     * Busca um usuário pelo email.
     */
    public Optional<UserResponseDTO> getUserByEmail(String email) {
        return userRepository.findByEmail(email).map(this::toResponseDTO);
    }

    /**
     * Atualiza os dados de um usuário.
     */
    public Optional<UserResponseDTO> updateUser(Long id, UserRequestDTO userDetails) {
        return userRepository.findById(id).map(existingUser -> {
            existingUser.setName(userDetails.getName());
            existingUser.setEmail(userDetails.getEmail());
            existingUser.setPassword(userDetails.getPassword()); // 🔹 Certifique-se de criptografar a senha antes de salvar
            existingUser.setProfilePicture(userDetails.getProfilePicture());
            existingUser.setBio(userDetails.getBio());

            UserEntity updatedUser = userRepository.save(existingUser);
            return toResponseDTO(updatedUser);
        });
    }

    /**
     * Deleta um usuário pelo ID, se ele existir.
     */
    public boolean deleteUser(Long id) {
        if (userRepository.existsById(id)) {
            userRepository.deleteById(id);
            return true;
        }
        return false;
    }

    /**
     * Converte um UserEntity para UserResponseDTO.
     */
    private UserResponseDTO toResponseDTO(UserEntity user) {
        UserResponseDTO dto = new UserResponseDTO();
        dto.setId(user.getId());
        dto.setName(user.getName());
        dto.setEmail(user.getEmail());
        dto.setProfilePicture(user.getProfilePicture());
        dto.setBio(user.getBio());
        dto.setCreatedAt(user.getCreatedAt());
        dto.setUpdatedAt(user.getUpdatedAt());
        return dto;
    }
}
