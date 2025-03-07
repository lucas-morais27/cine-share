package br.com.cineshare.dto;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import lombok.Data;

@Data
public class MessageRequestDTO {

    @NotNull(message = "O ID do usuário é obrigatório")
    private Long userId;

    @NotNull(message = "O ID do grupo é obrigatório")
    private Long groupId;

    @NotBlank(message = "A mensagem não pode estar vazia")
    private String messageContent;
}
