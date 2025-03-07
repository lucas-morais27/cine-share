package br.com.cineshare.service;

import br.com.cineshare.dto.MessageRequestDTO;
import br.com.cineshare.dto.MessageResponseDTO;
import br.com.cineshare.entity.GroupEntity;
import br.com.cineshare.entity.MessageEntity;
import br.com.cineshare.entity.UserEntity;
import br.com.cineshare.repository.GroupRepository;
import br.com.cineshare.repository.MessageRepository;
import br.com.cineshare.repository.UserRepository;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
public class MessageService {

    private final MessageRepository messageRepository;
    private final UserRepository userRepository;
    private final GroupRepository groupRepository;

    public MessageService(MessageRepository messageRepository, UserRepository userRepository, GroupRepository groupRepository) {
        this.messageRepository = messageRepository;
        this.userRepository = userRepository;
        this.groupRepository = groupRepository;
    }

    /**
     * Envia uma nova mensagem em um grupo.
     */
    public Optional<MessageResponseDTO> sendMessage(MessageRequestDTO request) {
        Optional<UserEntity> user = userRepository.findById(request.getUserId());
        Optional<GroupEntity> group = groupRepository.findById(request.getGroupId());

        if (user.isEmpty() || group.isEmpty()) {
            return Optional.empty(); // Usuário ou grupo não encontrado
        }

        MessageEntity message = new MessageEntity();
        message.setUser(user.get());
        message.setGroup(group.get());
        message.setMessageContent(request.getMessageContent());

        MessageEntity savedMessage = messageRepository.save(message);
        return Optional.of(toResponseDTO(savedMessage));
    }

    /**
     * Retorna todas as mensagens de um grupo.
     */
    public List<MessageResponseDTO> getMessagesByGroup(Long groupId) {
        return messageRepository.findByGroupId(groupId)
                .stream()
                .map(this::toResponseDTO)
                .collect(Collectors.toList());
    }

    /**
     * Retorna todas as mensagens enviadas por um usuário.
     */
    public List<MessageResponseDTO> getMessagesByUser(Long userId) {
        return messageRepository.findByUserId(userId)
                .stream()
                .map(this::toResponseDTO)
                .collect(Collectors.toList());
    }

    /**
     * Atualiza uma mensagem existente.
     */
    public Optional<MessageResponseDTO> updateMessage(Long id, MessageRequestDTO request) {
        return messageRepository.findById(id).map(existingMessage -> {
            existingMessage.setMessageContent(request.getMessageContent());

            MessageEntity updatedMessage = messageRepository.save(existingMessage);
            return toResponseDTO(updatedMessage);
        });
    }

    /**
     * Remove uma mensagem pelo ID.
     */
    public boolean deleteMessage(Long id) {
        if (messageRepository.existsById(id)) {
            messageRepository.deleteById(id);
            return true;
        }
        return false;
    }

    /**
     * Converte MessageEntity para MessageResponseDTO.
     */
    private MessageResponseDTO toResponseDTO(MessageEntity message) {
        MessageResponseDTO dto = new MessageResponseDTO();
        dto.setId(message.getId());
        dto.setGroupId(message.getGroup().getId());
        dto.setUserId(message.getUser().getId());
        dto.setMessageContent(message.getMessageContent());
        dto.setSentAt(message.getSentAt());
        return dto;
    }
}
