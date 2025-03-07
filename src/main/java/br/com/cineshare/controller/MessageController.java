package br.com.cineshare.controller;

import br.com.cineshare.dto.MessageRequestDTO;
import br.com.cineshare.dto.MessageResponseDTO;
import br.com.cineshare.service.MessageService;
import jakarta.validation.Valid;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/api/messages")
public class MessageController {

    private final MessageService messageService;

    public MessageController(MessageService messageService) {
        this.messageService = messageService;
    }

    /**
     * Envia uma mensagem para um grupo.
     */
    @PostMapping
    public ResponseEntity<MessageResponseDTO> sendMessage(@Valid @RequestBody MessageRequestDTO request) {
        return messageService.sendMessage(request)
                .map(ResponseEntity::ok)
                .orElse(ResponseEntity.badRequest().build()); // Usuário ou grupo não encontrado
    }

    /**
     * Retorna todas as mensagens de um grupo.
     */
    @GetMapping("/group/{groupId}")
    public ResponseEntity<List<MessageResponseDTO>> getMessagesByGroup(@PathVariable Long groupId) {
        return ResponseEntity.ok(messageService.getMessagesByGroup(groupId));
    }

    /**
     * Retorna todas as mensagens enviadas por um usuário.
     */
    @GetMapping("/user/{userId}")
    public ResponseEntity<List<MessageResponseDTO>> getMessagesByUser(@PathVariable Long userId) {
        return ResponseEntity.ok(messageService.getMessagesByUser(userId));
    }

    /**
     * Atualiza uma mensagem existente.
     */
    @PutMapping("/{id}")
    public ResponseEntity<MessageResponseDTO> updateMessage(@PathVariable Long id, @Valid @RequestBody MessageRequestDTO request) {
        return messageService.updateMessage(id, request)
                .map(ResponseEntity::ok)
                .orElse(ResponseEntity.notFound().build());
    }

    /**
     * Remove uma mensagem pelo ID.
     */
    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteMessage(@PathVariable Long id) {
        if (messageService.deleteMessage(id)) {
            return ResponseEntity.noContent().build();
        } else {
            return ResponseEntity.notFound().build();
        }
    }
}
