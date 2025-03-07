package br.com.cineshare.dto;

import br.com.cineshare.enums.Role;
import lombok.Data;

@Data
public class GroupMemberResponseDTO {
    private Long id;
    private Long groupId;
    private Long userId;
    private Role role;
}
