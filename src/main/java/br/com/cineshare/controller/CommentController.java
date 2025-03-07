package br.com.cineshare.controller;

import br.com.cineshare.dto.CommentRequestDTO;
import br.com.cineshare.dto.CommentResponseDTO;
import br.com.cineshare.service.CommentService;
import jakarta.validation.Valid;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/api/comments")
public class CommentController {

    private final CommentService commentService;

    public CommentController(CommentService commentService) {
        this.commentService = commentService;
    }

    /**
     * Adiciona um comentário a uma avaliação.
     */
    @PostMapping
    public ResponseEntity<CommentResponseDTO> addComment(@Valid @RequestBody CommentRequestDTO request) {
        return commentService.addComment(request)
                .map(ResponseEntity::ok)
                .orElse(ResponseEntity.badRequest().build()); // Usuário ou avaliação não encontrado
    }

    /**
     * Retorna todos os comentários de uma avaliação.
     */
    @GetMapping("/review/{reviewId}")
    public ResponseEntity<List<CommentResponseDTO>> getCommentsByReview(@PathVariable Long reviewId) {
        return ResponseEntity.ok(commentService.getCommentsByReview(reviewId));
    }

    /**
     * Retorna todos os comentários feitos por um usuário.
     */
    @GetMapping("/user/{userId}")
    public ResponseEntity<List<CommentResponseDTO>> getCommentsByUser(@PathVariable Long userId) {
        return ResponseEntity.ok(commentService.getCommentsByUser(userId));
    }

    /**
     * Atualiza um comentário existente.
     */
    @PutMapping("/{id}")
    public ResponseEntity<CommentResponseDTO> updateComment(@PathVariable Long id, @Valid @RequestBody CommentRequestDTO request) {
        return commentService.updateComment(id, request)
                .map(ResponseEntity::ok)
                .orElse(ResponseEntity.notFound().build());
    }

    /**
     * Remove um comentário pelo ID.
     */
    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteComment(@PathVariable Long id) {
        if (commentService.deleteComment(id)) {
            return ResponseEntity.noContent().build();
        } else {
            return ResponseEntity.notFound().build();
        }
    }
}
