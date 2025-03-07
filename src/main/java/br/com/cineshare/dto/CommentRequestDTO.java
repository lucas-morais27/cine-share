package br.com.cineshare.dto;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import lombok.Data;

@Data
public class CommentRequestDTO {

    @NotNull(message = "O ID do usuário é obrigatório")
    private Long userId;

    @NotNull(message = "O ID da avaliação é obrigatório")
    private Long reviewId;

    @NotBlank(message = "O comentário não pode estar vazio")
    private String content;
}
