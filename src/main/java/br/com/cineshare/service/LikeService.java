package br.com.cineshare.service;

import br.com.cineshare.dto.LikeRequestDTO;
import br.com.cineshare.dto.LikeResponseDTO;
import br.com.cineshare.entity.LikeEntity;
import br.com.cineshare.entity.ReviewEntity;
import br.com.cineshare.entity.UserEntity;
import br.com.cineshare.repository.LikeRepository;
import br.com.cineshare.repository.ReviewRepository;
import br.com.cineshare.repository.UserRepository;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
public class LikeService {

    private final LikeRepository likeRepository;
    private final UserRepository userRepository;
    private final ReviewRepository reviewRepository;
    private final NotificationService notificationService;

    public LikeService(LikeRepository likeRepository, UserRepository userRepository, ReviewRepository reviewRepository, NotificationService notificationService) {
        this.likeRepository = likeRepository;
        this.userRepository = userRepository;
        this.reviewRepository = reviewRepository;
        this.notificationService = notificationService;
    }

    /**
     * Adiciona uma curtida a uma avaliação.
     */
    public Optional<LikeResponseDTO> addLike(LikeRequestDTO request) {
        Optional<UserEntity> user = userRepository.findById(request.getUserId());
        Optional<ReviewEntity> review = reviewRepository.findById(request.getReviewId());

        if (user.isEmpty() || review.isEmpty()) {
            return Optional.empty();
        }

        if (likeRepository.findByReviewIdAndUserId(request.getReviewId(), request.getUserId()).isPresent()) {
            return Optional.empty();
        }

        LikeEntity like = new LikeEntity();
        like.setUser(user.get());
        like.setReview(review.get());

        LikeEntity savedLike = likeRepository.save(like);

        // 🔔 Enviar notificação para o autor da review
        notificationService.sendNotification(
                review.get().getUser().getId(),
                "Seu review recebeu um novo like de " + user.get().getName()
        );

        return Optional.of(toResponseDTO(savedLike));
    }

    /**
     * Retorna todas as curtidas de uma avaliação.
     */
    public List<LikeResponseDTO> getLikesByReview(Long reviewId) {
        return likeRepository.findByReviewId(reviewId)
                .stream()
                .map(this::toResponseDTO)
                .collect(Collectors.toList());
    }

    /**
     * Retorna todas as curtidas feitas por um usuário.
     */
    public List<LikeResponseDTO> getLikesByUser(Long userId) {
        return likeRepository.findByUserId(userId)
                .stream()
                .map(this::toResponseDTO)
                .collect(Collectors.toList());
    }

    /**
     * Remove uma curtida de uma avaliação.
     */
    public boolean removeLike(Long reviewId, Long userId) {
        Optional<LikeEntity> like = likeRepository.findByReviewIdAndUserId(reviewId, userId);

        if (like.isPresent()) {
            likeRepository.delete(like.get());
            return true;
        }
        return false;
    }

    /**
     * Converte LikeEntity para LikeResponseDTO.
     */
    private LikeResponseDTO toResponseDTO(LikeEntity like) {
        LikeResponseDTO dto = new LikeResponseDTO();
        dto.setId(like.getId());
        dto.setReviewId(like.getReview().getId());
        dto.setUserId(like.getUser().getId());
        return dto;
    }
}
