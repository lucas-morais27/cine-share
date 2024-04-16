package br.com.cineshare.service;

import br.com.cineshare.entity.GroupMemberEntity;
import br.com.cineshare.repository.GroupMemberRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class GroupMemberService {

    @Autowired
    private GroupMemberRepository groupMemberRepository;

    public GroupMemberEntity getGroupMemberById(Long id) {
        return groupMemberRepository.findById(id).orElse(null);
    }

    // Outros métodos para listar membros do grupo, adicionar membros, remover membros, etc.
}
