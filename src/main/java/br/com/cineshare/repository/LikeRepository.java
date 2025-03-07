package br.com.cineshare.repository;

import br.com.cineshare.entity.LikeEntity;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;
import java.util.Optional;

public interface LikeRepository extends JpaRepository<LikeEntity, Long> {

    List<LikeEntity> findByReviewId(Long reviewId);

    List<LikeEntity> findByUserId(Long userId);

    Optional<LikeEntity> findByReviewIdAndUserId(Long reviewId, Long userId);
}
