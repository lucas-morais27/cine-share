package br.com.cineshare.controller;

import br.com.cineshare.dto.GroupMemberRequestDTO;
import br.com.cineshare.dto.GroupMemberResponseDTO;
import br.com.cineshare.service.GroupMemberService;
import jakarta.validation.Valid;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/api/group-members")
public class GroupMemberController {

    private final GroupMemberService groupMemberService;

    public GroupMemberController(GroupMemberService groupMemberService) {
        this.groupMemberService = groupMemberService;
    }

    /**
     * Adiciona um usuário a um grupo.
     */
    @PostMapping
    public ResponseEntity<GroupMemberResponseDTO> addUserToGroup(@Valid @RequestBody GroupMemberRequestDTO request) {
        return groupMemberService.addUserToGroup(request)
                .map(ResponseEntity::ok)
                .orElse(ResponseEntity.badRequest().build()); // Grupo ou usuário não encontrado
    }

    /**
     * Retorna todos os membros de um grupo.
     */
    @GetMapping("/group/{groupId}")
    public ResponseEntity<List<GroupMemberResponseDTO>> getMembersByGroup(@PathVariable Long groupId) {
        return ResponseEntity.ok(groupMemberService.getMembersByGroup(groupId));
    }

    /**
     * Retorna todos os grupos de um usuário.
     */
    @GetMapping("/user/{userId}")
    public ResponseEntity<List<GroupMemberResponseDTO>> getGroupsByUser(@PathVariable Long userId) {
        return ResponseEntity.ok(groupMemberService.getGroupsByUser(userId));
    }

    /**
     * Remove um usuário de um grupo.
     */
    @DeleteMapping("/{groupId}/{userId}")
    public ResponseEntity<Void> removeUserFromGroup(@PathVariable Long groupId, @PathVariable Long userId) {
        if (groupMemberService.removeUserFromGroup(groupId, userId)) {
            return ResponseEntity.noContent().build();
        } else {
            return ResponseEntity.notFound().build();
        }
    }
}
