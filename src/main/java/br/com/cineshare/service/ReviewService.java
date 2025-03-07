package br.com.cineshare.service;

import br.com.cineshare.dto.ReviewRequestDTO;
import br.com.cineshare.dto.ReviewResponseDTO;
import br.com.cineshare.entity.MovieEntity;
import br.com.cineshare.entity.ReviewEntity;
import br.com.cineshare.entity.UserEntity;
import br.com.cineshare.repository.MovieRepository;
import br.com.cineshare.repository.ReviewRepository;
import br.com.cineshare.repository.UserRepository;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
public class ReviewService {

    private final ReviewRepository reviewRepository;
    private final UserRepository userRepository;
    private final MovieRepository movieRepository;

    public ReviewService(ReviewRepository reviewRepository, UserRepository userRepository, MovieRepository movieRepository) {
        this.reviewRepository = reviewRepository;
        this.userRepository = userRepository;
        this.movieRepository = movieRepository;
    }

    /**
     * Cria uma nova avaliação para um filme por um usuário.
     */
    public Optional<ReviewResponseDTO> createReview(ReviewRequestDTO reviewRequest) {
        Optional<UserEntity> user = userRepository.findById(reviewRequest.getUserId());
        Optional<MovieEntity> movie = movieRepository.findById(reviewRequest.getMovieId());

        if (user.isEmpty() || movie.isEmpty()) {
            return Optional.empty(); // Usuário ou filme não encontrado
        }

        ReviewEntity review = new ReviewEntity();
        review.setUser(user.get());
        review.setMovie(movie.get());
        review.setRating(reviewRequest.getRating());
        review.setReviewText(reviewRequest.getReviewText());

        ReviewEntity savedReview = reviewRepository.save(review);
        return Optional.of(toResponseDTO(savedReview));
    }

    /**
     * Retorna todas as avaliações do sistema.
     */
    public List<ReviewResponseDTO> getAllReviews() {
        return reviewRepository.findAll()
                .stream()
                .map(this::toResponseDTO)
                .collect(Collectors.toList());
    }

    /**
     * Retorna todas as avaliações de um filme específico.
     */
    public List<ReviewResponseDTO> getReviewsByMovie(Long movieId) {
        return reviewRepository.findByMovieId(movieId)
                .stream()
                .map(this::toResponseDTO)
                .collect(Collectors.toList());
    }

    /**
     * Retorna todas as avaliações feitas por um usuário específico.
     */
    public List<ReviewResponseDTO> getReviewsByUser(Long userId) {
        return reviewRepository.findByUserId(userId)
                .stream()
                .map(this::toResponseDTO)
                .collect(Collectors.toList());
    }

    /**
     * Busca uma avaliação pelo ID.
     */
    public Optional<ReviewResponseDTO> getReviewById(Long id) {
        return reviewRepository.findById(id).map(this::toResponseDTO);
    }

    /**
     * Atualiza uma avaliação existente.
     */
    public Optional<ReviewResponseDTO> updateReview(Long id, ReviewRequestDTO reviewRequest) {
        return reviewRepository.findById(id).map(existingReview -> {
            existingReview.setRating(reviewRequest.getRating());
            existingReview.setReviewText(reviewRequest.getReviewText());

            ReviewEntity updatedReview = reviewRepository.save(existingReview);
            return toResponseDTO(updatedReview);
        });
    }

    /**
     * Deleta uma avaliação pelo ID.
     */
    public boolean deleteReview(Long id) {
        if (reviewRepository.existsById(id)) {
            reviewRepository.deleteById(id);
            return true;
        }
        return false;
    }

    /**
     * Converte um ReviewEntity para ReviewResponseDTO.
     */
    private ReviewResponseDTO toResponseDTO(ReviewEntity review) {
        ReviewResponseDTO dto = new ReviewResponseDTO();
        dto.setId(review.getId());
        dto.setMovieId(review.getMovie().getId());
        dto.setUserId(review.getUser().getId());
        dto.setRating(review.getRating());
        dto.setReviewText(review.getReviewText());
        dto.setCreatedAt(review.getCreatedAt());
        return dto;
    }
}
