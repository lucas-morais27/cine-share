package br.com.cineshare.dto;

import lombok.Data;
import java.time.LocalDateTime;

@Data
public class MessageResponseDTO {
    private Long id;
    private Long groupId;
    private Long userId;
    private String messageContent;
    private LocalDateTime sentAt;
}
