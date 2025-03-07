package br.com.cineshare.dto;

import lombok.Data;
import java.time.LocalDateTime;

@Data
public class CommentResponseDTO {
    private Long id;
    private Long reviewId;
    private Long userId;
    private String content;
    private LocalDateTime createdAt;
}
