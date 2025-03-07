package br.com.cineshare.service;

import br.com.cineshare.dto.NotificationResponseDTO;
import br.com.cineshare.entity.NotificationEntity;
import br.com.cineshare.entity.UserEntity;
import br.com.cineshare.repository.NotificationRepository;
import br.com.cineshare.repository.UserRepository;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
public class NotificationService {

    private final NotificationRepository notificationRepository;
    private final UserRepository userRepository;

    public NotificationService(NotificationRepository notificationRepository, UserRepository userRepository) {
        this.notificationRepository = notificationRepository;
        this.userRepository = userRepository;
    }

    /**
     * Envia uma nova notificação para um usuário.
     */
    public void sendNotification(Long userId, String message) {
        Optional<UserEntity> user = userRepository.findById(userId);

        if (user.isPresent()) {
            NotificationEntity notification = new NotificationEntity();
            notification.setUser(user.get());
            notification.setMessage(message);
            notificationRepository.save(notification);
        }
    }

    /**
     * Retorna todas as notificações de um usuário.
     */
    public List<NotificationResponseDTO> getNotificationsByUser(Long userId) {
        return notificationRepository.findByUserIdOrderByCreatedAtDesc(userId)
                .stream()
                .map(this::toResponseDTO)
                .collect(Collectors.toList());
    }

    /**
     * Marca uma notificação como lida.
     */
    public boolean markAsRead(Long notificationId) {
        Optional<NotificationEntity> notification = notificationRepository.findById(notificationId);

        if (notification.isPresent()) {
            notification.get().setIsRead(true);
            notificationRepository.save(notification.get());
            return true;
        }

        return false;
    }

    /**
     * Converte NotificationEntity para NotificationResponseDTO.
     */
    private NotificationResponseDTO toResponseDTO(NotificationEntity notification) {
        NotificationResponseDTO dto = new NotificationResponseDTO();
        dto.setId(notification.getId());
        dto.setUserId(notification.getUser().getId());
        dto.setMessage(notification.getMessage());
        dto.setIsRead(notification.getIsRead());
        dto.setCreatedAt(notification.getCreatedAt());
        return dto;
    }
}
