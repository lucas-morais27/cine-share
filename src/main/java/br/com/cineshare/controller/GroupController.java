package br.com.cineshare.controller;

import br.com.cineshare.dto.GroupRequestDTO;
import br.com.cineshare.dto.GroupResponseDTO;
import br.com.cineshare.service.GroupService;
import jakarta.validation.Valid;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/api/groups")
public class GroupController {

    private final GroupService groupService;

    public GroupController(GroupService groupService) {
        this.groupService = groupService;
    }

    /**
     * Cria um novo grupo.
     */
    @PostMapping
    public ResponseEntity<GroupResponseDTO> createGroup(@Valid @RequestBody GroupRequestDTO groupRequest) {
        return groupService.createGroup(groupRequest)
                .map(ResponseEntity::ok)
                .orElse(ResponseEntity.badRequest().build()); // Se o dono do grupo não existir
    }

    /**
     * Retorna a lista de todos os grupos.
     */
    @GetMapping
    public ResponseEntity<List<GroupResponseDTO>> getAllGroups() {
        return ResponseEntity.ok(groupService.getAllGroups());
    }

    /**
     * Retorna um grupo pelo ID.
     */
    @GetMapping("/{id}")
    public ResponseEntity<GroupResponseDTO> getGroupById(@PathVariable Long id) {
        return groupService.getGroupById(id)
                .map(ResponseEntity::ok)
                .orElse(ResponseEntity.notFound().build());
    }

    /**
     * Atualiza um grupo existente.
     */
    @PutMapping("/{id}")
    public ResponseEntity<GroupResponseDTO> updateGroup(@PathVariable Long id, @Valid @RequestBody GroupRequestDTO groupRequest) {
        return groupService.updateGroup(id, groupRequest)
                .map(ResponseEntity::ok)
                .orElse(ResponseEntity.notFound().build());
    }

    /**
     * Deleta um grupo pelo ID.
     */
    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteGroup(@PathVariable Long id) {
        if (groupService.deleteGroup(id)) {
            return ResponseEntity.noContent().build();
        } else {
            return ResponseEntity.notFound().build();
        }
    }
}
