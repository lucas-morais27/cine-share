package br.com.cineshare.repository;

import br.com.cineshare.entity.ReviewEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ReviewRepository extends JpaRepository<ReviewEntity, Long> {
    // Aqui você pode adicionar métodos de consulta personalizados, se necessário
}
