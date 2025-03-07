package br.com.cineshare.dto;

import lombok.Data;

@Data
public class LikeResponseDTO {
    private Long id;
    private Long reviewId;
    private Long userId;
}
