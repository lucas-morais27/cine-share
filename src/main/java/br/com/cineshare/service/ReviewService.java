package br.com.cineshare.service;

import br.com.cineshare.entity.ReviewEntity;
import br.com.cineshare.repository.ReviewRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class ReviewService {

    @Autowired
    private ReviewRepository reviewRepository;

    public ReviewEntity getReviewById(Long id) {
        return reviewRepository.findById(id).orElse(null);
    }

    // Outros métodos para listar avaliações, adicionar avaliações, atualizar, excluir, etc.
}
