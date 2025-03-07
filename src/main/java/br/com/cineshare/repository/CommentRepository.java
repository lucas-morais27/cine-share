package br.com.cineshare.repository;

import br.com.cineshare.entity.CommentEntity;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface CommentRepository extends JpaRepository<CommentEntity, Long> {

    List<CommentEntity> findByReviewId(Long reviewId);

    List<CommentEntity> findByUserId(Long userId);
}
