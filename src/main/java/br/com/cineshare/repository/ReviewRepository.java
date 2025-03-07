package br.com.cineshare.repository;

import br.com.cineshare.entity.ReviewEntity;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface ReviewRepository extends JpaRepository<ReviewEntity, Long> {

    List<ReviewEntity> findByUserId(Long userId);

    List<ReviewEntity> findByMovieId(Long movieId);
}
