package br.com.cineshare.dto;

import jakarta.validation.constraints.NotNull;
import lombok.Data;

@Data
public class LikeRequestDTO {

    @NotNull(message = "O ID do usuário é obrigatório")
    private Long userId;

    @NotNull(message = "O ID da avaliação é obrigatório")
    private Long reviewId;
}
