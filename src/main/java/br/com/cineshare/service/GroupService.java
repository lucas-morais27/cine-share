package br.com.cineshare.service;

import br.com.cineshare.entity.GroupEntity;
import br.com.cineshare.repository.GroupRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class GroupService {

    @Autowired
    private GroupRepository groupRepository;

    public GroupEntity getGroupById(Long id) {
        return groupRepository.findById(id).orElse(null);
    }

    // Outros métodos para listar grupos, criar grupos, atualizar, excluir, etc.
}
