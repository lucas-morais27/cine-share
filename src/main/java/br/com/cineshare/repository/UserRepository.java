package br.com.cineshare.repository;

import br.com.cineshare.entity.UserEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface UserRepository extends JpaRepository<UserEntity, Long> {
    // Aqui você pode adicionar métodos de consulta personalizados, se necessário
}
