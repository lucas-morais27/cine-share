package br.com.cineshare.controller;

import br.com.cineshare.dto.LikeRequestDTO;
import br.com.cineshare.dto.LikeResponseDTO;
import br.com.cineshare.service.LikeService;
import jakarta.validation.Valid;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/api/likes")
public class LikeController {

    private final LikeService likeService;

    public LikeController(LikeService likeService) {
        this.likeService = likeService;
    }

    /**
     * Adiciona uma curtida a uma avaliação.
     */
    @PostMapping
    public ResponseEntity<LikeResponseDTO> addLike(@Valid @RequestBody LikeRequestDTO request) {
        return likeService.addLike(request)
                .map(ResponseEntity::ok)
                .orElse(ResponseEntity.badRequest().build()); // Usuário ou avaliação não encontrado
    }

    /**
     * Retorna todas as curtidas de uma avaliação.
     */
    @GetMapping("/review/{reviewId}")
    public ResponseEntity<List<LikeResponseDTO>> getLikesByReview(@PathVariable Long reviewId) {
        return ResponseEntity.ok(likeService.getLikesByReview(reviewId));
    }

    /**
     * Retorna todas as curtidas feitas por um usuário.
     */
    @GetMapping("/user/{userId}")
    public ResponseEntity<List<LikeResponseDTO>> getLikesByUser(@PathVariable Long userId) {
        return ResponseEntity.ok(likeService.getLikesByUser(userId));
    }

    /**
     * Remove uma curtida de uma avaliação.
     */
    @DeleteMapping("/{reviewId}/{userId}")
    public ResponseEntity<Void> removeLike(@PathVariable Long reviewId, @PathVariable Long userId) {
        if (likeService.removeLike(reviewId, userId)) {
            return ResponseEntity.noContent().build();
        } else {
            return ResponseEntity.notFound().build();
        }
    }
}
