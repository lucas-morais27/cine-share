package br.com.cineshare.repository;

import br.com.cineshare.entity.GroupEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface GroupRepository extends JpaRepository<GroupEntity, Long> {
    // Aqui você pode adicionar métodos de consulta personalizados, se necessário
}
