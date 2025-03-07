package br.com.cineshare.dto;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;
import lombok.Data;

@Data
public class MovieRequestDTO {

    @NotBlank(message = "O título do filme é obrigatório")
    @Size(max = 255, message = "O título pode ter no máximo 255 caracteres")
    private String title;

    @Size(max = 500, message = "A descrição pode ter no máximo 500 caracteres")
    private String description;

    @NotNull(message = "O ano de lançamento é obrigatório")
    private Integer releaseYear;

    @NotBlank(message = "O gênero do filme é obrigatório")
    private String genre;

    private String director;

    private String posterUrl;
}
