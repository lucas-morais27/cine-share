package br.com.cineshare.entity;

import jakarta.persistence.*;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;
import lombok.*;

import java.util.List;

@Data
@Entity
@Table(name = "movies")
@AllArgsConstructor
@NoArgsConstructor
public class MovieEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @NotBlank(message = "O título do filme é obrigatório")
    @Column(nullable = false, unique = true, length = 255)
    private String title;

    @Size(max = 500, message = "A descrição pode ter no máximo 500 caracteres")
    @Column(length = 500)
    private String description;

    @Column(nullable = false)
    private int releaseYear;

    @NotBlank(message = "O gênero é obrigatório")
    @Column(nullable = false, length = 100)
    private String genre;

    @Size(max = 100, message = "O nome do diretor pode ter no máximo 100 caracteres")
    private String director;

    @Column(length = 500)
    private String posterUrl;
}
