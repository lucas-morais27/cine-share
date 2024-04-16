package br.com.cineshare.service;

import br.com.cineshare.entity.MovieEntity;
import br.com.cineshare.repository.MovieRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class MovieService {

    @Autowired
    private MovieRepository movieRepository;

    public MovieEntity getMovieById(Long id) {
        return movieRepository.findById(id).orElse(null);
    }

    // Outros métodos para listar filmes, adicionar filmes, atualizar, excluir, etc.
}
