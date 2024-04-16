package br.com.cineshare.repository;

import br.com.cineshare.entity.GroupMemberEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface GroupMemberRepository extends JpaRepository<GroupMemberEntity, Long> {
    // Aqui você pode adicionar métodos de consulta personalizados, se necessário
}
