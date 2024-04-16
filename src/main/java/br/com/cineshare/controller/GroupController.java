package br.com.cineshare.controller;

import br.com.cineshare.entity.GroupEntity;
import br.com.cineshare.service.GroupService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/groups")
public class GroupController {

    @Autowired
    private GroupService groupService;

    @GetMapping("/{id}")
    public GroupEntity getGroupById(@PathVariable Long id) {
        return groupService.getGroupById(id);
    }

    // Outros métodos para listar grupos, criar grupos, atualizar, excluir, etc.
}
