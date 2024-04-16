package br.com.cineshare.controller;

import br.com.cineshare.entity.GroupMemberEntity;
import br.com.cineshare.service.GroupMemberService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/group-members")
public class GroupMemberController {

    @Autowired
    private GroupMemberService groupMemberService;

    @GetMapping("/{id}")
    public GroupMemberEntity getGroupMemberById(@PathVariable Long id) {
        return groupMemberService.getGroupMemberById(id);
    }

    // Outros métodos para listar membros do grupo, adicionar membros, remover membros, etc.
}
