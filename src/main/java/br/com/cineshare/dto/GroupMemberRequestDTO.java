package br.com.cineshare.dto;

import br.com.cineshare.enums.Role;
import jakarta.validation.constraints.NotNull;
import lombok.Data;

@Data
public class GroupMemberRequestDTO {

    @NotNull(message = "O ID do grupo é obrigatório")
    private Long groupId;

    @NotNull(message = "O ID do usuário é obrigatório")
    private Long userId;

    private Role role; // Padrão: MEMBER
}
