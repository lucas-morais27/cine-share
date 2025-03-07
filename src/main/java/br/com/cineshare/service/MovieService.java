package br.com.cineshare.service;

import br.com.cineshare.dto.MovieRequestDTO;
import br.com.cineshare.dto.MovieResponseDTO;
import br.com.cineshare.entity.MovieEntity;
import br.com.cineshare.repository.MovieRepository;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
public class MovieService {

    private final MovieRepository movieRepository;

    public MovieService(MovieRepository movieRepository) {
        this.movieRepository = movieRepository;
    }

    /**
     * Cria um novo filme no sistema.
     */
    public MovieResponseDTO createMovie(MovieRequestDTO movieRequest) {
        MovieEntity movie = new MovieEntity();
        movie.setTitle(movieRequest.getTitle());
        movie.setDescription(movieRequest.getDescription());
        movie.setReleaseYear(movieRequest.getReleaseYear());
        movie.setGenre(movieRequest.getGenre());
        movie.setDirector(movieRequest.getDirector());
        movie.setPosterUrl(movieRequest.getPosterUrl());

        MovieEntity savedMovie = movieRepository.save(movie);
        return toResponseDTO(savedMovie);
    }

    /**
     * Retorna todos os filmes cadastrados no sistema.
     */
    public List<MovieResponseDTO> getAllMovies() {
        return movieRepository.findAll()
                .stream()
                .map(this::toResponseDTO)
                .collect(Collectors.toList());
    }

    /**
     * Busca um filme pelo ID.
     */
    public Optional<MovieResponseDTO> getMovieById(Long id) {
        return movieRepository.findById(id).map(this::toResponseDTO);
    }

    /**
     * Atualiza os dados de um filme existente.
     */
    public Optional<MovieResponseDTO> updateMovie(Long id, MovieRequestDTO movieRequest) {
        return movieRepository.findById(id).map(existingMovie -> {
            existingMovie.setTitle(movieRequest.getTitle());
            existingMovie.setDescription(movieRequest.getDescription());
            existingMovie.setReleaseYear(movieRequest.getReleaseYear());
            existingMovie.setGenre(movieRequest.getGenre());
            existingMovie.setDirector(movieRequest.getDirector());
            existingMovie.setPosterUrl(movieRequest.getPosterUrl());

            MovieEntity updatedMovie = movieRepository.save(existingMovie);
            return toResponseDTO(updatedMovie);
        });
    }

    /**
     * Deleta um filme se ele existir.
     */
    public boolean deleteMovie(Long id) {
        if (movieRepository.existsById(id)) {
            movieRepository.deleteById(id);
            return true;
        }
        return false;
    }

    /**
     * Converte um MovieEntity para MovieResponseDTO.
     */
    private MovieResponseDTO toResponseDTO(MovieEntity movie) {
        MovieResponseDTO dto = new MovieResponseDTO();
        dto.setId(movie.getId());
        dto.setTitle(movie.getTitle());
        dto.setDescription(movie.getDescription());
        dto.setReleaseYear(movie.getReleaseYear());
        dto.setGenre(movie.getGenre());
        dto.setDirector(movie.getDirector());
        dto.setPosterUrl(movie.getPosterUrl());
        return dto;
    }
}
