package br.com.cineshare.entity;

import jakarta.persistence.*;
import lombok.*;

import java.time.LocalDateTime;

@Data
@Entity
@Table(name = "notifications")
@AllArgsConstructor
@NoArgsConstructor
public class NotificationEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToOne
    @JoinColumn(name = "user_id", nullable = false)
    private UserEntity user; // Usuário que recebe a notificação

    @Column(nullable = false)
    private String message; // Texto da notificação

    @Column(nullable = false)
    private Boolean isRead = false; // Indica se a notificação foi lida

    @Column(nullable = false)
    private LocalDateTime createdAt = LocalDateTime.now();
}
