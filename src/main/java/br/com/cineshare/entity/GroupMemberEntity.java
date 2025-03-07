package br.com.cineshare.entity;

import br.com.cineshare.enums.Role;
import jakarta.persistence.*;
import lombok.*;

@Data
@Entity
@Table(name = "group_members")
@AllArgsConstructor
@NoArgsConstructor
public class GroupMemberEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToOne
    @JoinColumn(name = "group_id", nullable = false)
    private GroupEntity group;

    @ManyToOne
    @JoinColumn(name = "user_id", nullable = false)
    private UserEntity user;

    @Enumerated(EnumType.STRING)
    private Role role; // Definição do papel no grupo (ADMIN, MEMBER)
}
