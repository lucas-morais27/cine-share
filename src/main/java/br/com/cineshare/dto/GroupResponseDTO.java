package br.com.cineshare.dto;

import lombok.Data;

@Data
public class GroupResponseDTO {
    private Long id;
    private String name;
    private String description;
    private Long ownerId; // Apenas ID para evitar expor todo o UserEntity
}
