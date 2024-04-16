package br.com.cineshare.controller;

import br.com.cineshare.entity.MovieEntity;
import br.com.cineshare.service.MovieService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/movies")
public class MovieController {
    @Autowired
    private MovieService movieService;

    @GetMapping("/{id}")
    public MovieEntity getMovieById(@PathVariable Long id) {
        return movieService.getMovieById(id);
    }

    // Outros métodos para listar filmes, adicionar filmes, atualizar, excluir, etc.
}
