package br.com.cineshare.dto;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import lombok.Data;

@Data
public class NotificationRequestDTO {

    @NotNull(message = "O ID do usuário é obrigatório")
    private Long userId;

    @NotBlank(message = "A mensagem da notificação não pode estar vazia")
    private String message;
}
