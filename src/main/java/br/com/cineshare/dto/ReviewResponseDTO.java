package br.com.cineshare.dto;

import lombok.Data;
import java.time.LocalDateTime;

@Data
public class ReviewResponseDTO {
    private Long id;
    private Long movieId;
    private Long userId;
    private int rating;
    private String reviewText;
    private LocalDateTime createdAt;
}
