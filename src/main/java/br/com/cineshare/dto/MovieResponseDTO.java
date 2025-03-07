package br.com.cineshare.dto;

import lombok.Data;

@Data
public class MovieResponseDTO {
    private Long id;
    private String title;
    private String description;
    private Integer releaseYear;
    private String genre;
    private String director;
    private String posterUrl;
}
