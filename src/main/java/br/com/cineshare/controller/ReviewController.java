package br.com.cineshare.controller;

import br.com.cineshare.entity.ReviewEntity;
import br.com.cineshare.service.ReviewService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/reviews")
public class ReviewController {

    @Autowired
    private ReviewService reviewService;

    @GetMapping("/{id}")
    public ReviewEntity getReviewById(@PathVariable Long id) {
        return reviewService.getReviewById(id);
    }

    // Outros métodos para listar avaliações, adicionar avaliações, atualizar, excluir, etc.
}
