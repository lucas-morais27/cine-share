package br.com.cineshare.dto;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import lombok.Data;

@Data
public class GroupRequestDTO {

    @NotBlank(message = "O nome do grupo é obrigatório")
    private String name;

    private String description;

    @NotNull(message = "O ID do dono do grupo é obrigatório")
    private Long ownerId; // ID do usuário que cria o grupo
}
