package br.com.cineshare.repository;

import br.com.cineshare.entity.MovieEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface MovieRepository extends JpaRepository<MovieEntity, Long> {
    // Aqui você pode adicionar métodos de consulta personalizados, se necessário
}
