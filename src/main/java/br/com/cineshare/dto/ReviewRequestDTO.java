package br.com.cineshare.dto;

import jakarta.validation.constraints.Max;
import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import lombok.Data;

@Data
public class ReviewRequestDTO {

    @NotNull(message = "O ID do usuário é obrigatório")
    private Long userId;

    @NotNull(message = "O ID do filme é obrigatório")
    private Long movieId;

    @Min(1) @Max(5)
    private int rating;

    @NotBlank(message = "O comentário não pode estar vazio")
    private String reviewText;
}
