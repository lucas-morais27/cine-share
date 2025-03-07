package br.com.cineshare.service;

import br.com.cineshare.dto.CommentRequestDTO;
import br.com.cineshare.dto.CommentResponseDTO;
import br.com.cineshare.entity.CommentEntity;
import br.com.cineshare.entity.ReviewEntity;
import br.com.cineshare.entity.UserEntity;
import br.com.cineshare.repository.CommentRepository;
import br.com.cineshare.repository.ReviewRepository;
import br.com.cineshare.repository.UserRepository;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
public class CommentService {

    private final CommentRepository commentRepository;
    private final UserRepository userRepository;
    private final ReviewRepository reviewRepository;

    public CommentService(CommentRepository commentRepository, UserRepository userRepository, ReviewRepository reviewRepository) {
        this.commentRepository = commentRepository;
        this.userRepository = userRepository;
        this.reviewRepository = reviewRepository;
    }

    /**
     * Adiciona um comentário a uma avaliação.
     */
    public Optional<CommentResponseDTO> addComment(CommentRequestDTO request) {
        Optional<UserEntity> user = userRepository.findById(request.getUserId());
        Optional<ReviewEntity> review = reviewRepository.findById(request.getReviewId());

        if (user.isEmpty() || review.isEmpty()) {
            return Optional.empty(); // Usuário ou avaliação não encontrado
        }

        CommentEntity comment = new CommentEntity();
        comment.setUser(user.get());
        comment.setReview(review.get());
        comment.setContent(request.getContent());

        CommentEntity savedComment = commentRepository.save(comment);
        return Optional.of(toResponseDTO(savedComment));
    }

    /**
     * Retorna todos os comentários de uma avaliação.
     */
    public List<CommentResponseDTO> getCommentsByReview(Long reviewId) {
        return commentRepository.findByReviewId(reviewId)
                .stream()
                .map(this::toResponseDTO)
                .collect(Collectors.toList());
    }

    /**
     * Retorna todos os comentários feitos por um usuário.
     */
    public List<CommentResponseDTO> getCommentsByUser(Long userId) {
        return commentRepository.findByUserId(userId)
                .stream()
                .map(this::toResponseDTO)
                .collect(Collectors.toList());
    }

    /**
     * Atualiza um comentário existente.
     */
    public Optional<CommentResponseDTO> updateComment(Long id, CommentRequestDTO request) {
        return commentRepository.findById(id).map(existingComment -> {
            existingComment.setContent(request.getContent());

            CommentEntity updatedComment = commentRepository.save(existingComment);
            return toResponseDTO(updatedComment);
        });
    }

    /**
     * Remove um comentário pelo ID.
     */
    public boolean deleteComment(Long id) {
        if (commentRepository.existsById(id)) {
            commentRepository.deleteById(id);
            return true;
        }
        return false;
    }

    /**
     * Converte CommentEntity para CommentResponseDTO.
     */
    private CommentResponseDTO toResponseDTO(CommentEntity comment) {
        CommentResponseDTO dto = new CommentResponseDTO();
        dto.setId(comment.getId());
        dto.setReviewId(comment.getReview().getId());
        dto.setUserId(comment.getUser().getId());
        dto.setContent(comment.getContent());
        dto.setCreatedAt(comment.getCreatedAt());
        return dto;
    }
}
