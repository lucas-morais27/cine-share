package br.com.cineshare.service;

import br.com.cineshare.dto.GroupMemberRequestDTO;
import br.com.cineshare.dto.GroupMemberResponseDTO;
import br.com.cineshare.entity.GroupEntity;
import br.com.cineshare.entity.GroupMemberEntity;
import br.com.cineshare.entity.UserEntity;
import br.com.cineshare.enums.Role;
import br.com.cineshare.repository.GroupMemberRepository;
import br.com.cineshare.repository.GroupRepository;
import br.com.cineshare.repository.UserRepository;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
public class GroupMemberService {

    private final GroupMemberRepository groupMemberRepository;
    private final GroupRepository groupRepository;
    private final UserRepository userRepository;

    public GroupMemberService(GroupMemberRepository groupMemberRepository, GroupRepository groupRepository, UserRepository userRepository) {
        this.groupMemberRepository = groupMemberRepository;
        this.groupRepository = groupRepository;
        this.userRepository = userRepository;
    }

    /**
     * Adiciona um usuário a um grupo.
     */
    public Optional<GroupMemberResponseDTO> addUserToGroup(GroupMemberRequestDTO request) {
        Optional<GroupEntity> group = groupRepository.findById(request.getGroupId());
        Optional<UserEntity> user = userRepository.findById(request.getUserId());

        if (group.isEmpty() || user.isEmpty()) {
            return Optional.empty(); // Grupo ou usuário não encontrado
        }

        // Verifica se o usuário já faz parte do grupo
        if (groupMemberRepository.findByGroupIdAndUserId(request.getGroupId(), request.getUserId()).isPresent()) {
            return Optional.empty(); // Usuário já está no grupo
        }

        GroupMemberEntity groupMember = new GroupMemberEntity();
        groupMember.setGroup(group.get());
        groupMember.setUser(user.get());
        groupMember.setRole(request.getRole() != null ? request.getRole() : Role.MEMBER); // Padrão: MEMBER

        GroupMemberEntity savedMember = groupMemberRepository.save(groupMember);
        return Optional.of(toResponseDTO(savedMember));
    }

    /**
     * Retorna todos os membros de um grupo.
     */
    public List<GroupMemberResponseDTO> getMembersByGroup(Long groupId) {
        return groupMemberRepository.findByGroupId(groupId)
                .stream()
                .map(this::toResponseDTO)
                .collect(Collectors.toList());
    }

    /**
     * Retorna todos os grupos de um usuário.
     */
    public List<GroupMemberResponseDTO> getGroupsByUser(Long userId) {
        return groupMemberRepository.findByUserId(userId)
                .stream()
                .map(this::toResponseDTO)
                .collect(Collectors.toList());
    }

    /**
     * Remove um usuário de um grupo.
     */
    public boolean removeUserFromGroup(Long groupId, Long userId) {
        Optional<GroupMemberEntity> groupMember = groupMemberRepository.findByGroupIdAndUserId(groupId, userId);

        if (groupMember.isPresent()) {
            groupMemberRepository.delete(groupMember.get());
            return true;
        }
        return false;
    }

    /**
     * Converte GroupMemberEntity para GroupMemberResponseDTO.
     */
    private GroupMemberResponseDTO toResponseDTO(GroupMemberEntity groupMember) {
        GroupMemberResponseDTO dto = new GroupMemberResponseDTO();
        dto.setId(groupMember.getId());
        dto.setGroupId(groupMember.getGroup().getId());
        dto.setUserId(groupMember.getUser().getId());
        dto.setRole(groupMember.getRole());
        return dto;
    }
}
