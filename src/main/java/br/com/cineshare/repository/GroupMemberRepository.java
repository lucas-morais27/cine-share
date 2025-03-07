package br.com.cineshare.repository;

import br.com.cineshare.entity.GroupMemberEntity;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;
import java.util.Optional;

public interface GroupMemberRepository extends JpaRepository<GroupMemberEntity, Long> {

    List<GroupMemberEntity> findByGroupId(Long groupId);

    List<GroupMemberEntity> findByUserId(Long userId);

    Optional<GroupMemberEntity> findByGroupIdAndUserId(Long groupId, Long userId);
}
