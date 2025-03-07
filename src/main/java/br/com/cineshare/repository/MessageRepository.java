package br.com.cineshare.repository;

import br.com.cineshare.entity.MessageEntity;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface MessageRepository extends JpaRepository<MessageEntity, Long> {

    List<MessageEntity> findByGroupId(Long groupId);

    List<MessageEntity> findByUserId(Long userId);
}
