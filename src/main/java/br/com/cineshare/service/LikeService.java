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

    public LikeService(LikeRepository likeRepository, UserRepository userRepository, ReviewRepository reviewRepository) {
        this.likeRepository = likeRepository;
        this.userRepository = userRepository;
        this.reviewRepository = reviewRepository;
    }

    /**
     * Adiciona uma curtida a uma avaliação.
     */
    public Optional<LikeResponseDTO> addLike(LikeRequestDTO request) {
        Optional<UserEntity> user = userRepository.findById(request.getUserId());
        Optional<ReviewEntity> review = reviewRepository.findById(request.getReviewId());

        if (user.isEmpty() || review.isEmpty()) {
            return Optional.empty(); // Usuário ou avaliação não encontrado
        }

        // Verifica se o usuário já curtiu essa avaliação
        if (likeRepository.findByReviewIdAndUserId(request.getReviewId(), request.getUserId()).isPresent()) {
            return Optional.empty(); // Usuário já curtiu essa avaliação
        }

        LikeEntity like = new LikeEntity();
        like.setUser(user.get());
        like.setReview(review.get());

        LikeEntity savedLike = likeRepository.save(like);
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
