package br.com.cineshare.service;

import br.com.cineshare.dto.GroupRequestDTO;
import br.com.cineshare.dto.GroupResponseDTO;
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
public class GroupService {

    private final GroupRepository groupRepository;
    private final UserRepository userRepository;
    private final GroupMemberRepository groupMemberRepository;

    public GroupService(GroupRepository groupRepository, UserRepository userRepository, GroupMemberRepository groupMemberRepository) {
        this.groupRepository = groupRepository;
        this.userRepository = userRepository;
        this.groupMemberRepository = groupMemberRepository;
    }

    /**
     * Cria um novo grupo e adiciona o criador como ADMIN no GroupMemberEntity.
     */
    public Optional<GroupResponseDTO> createGroup(GroupRequestDTO groupRequest) {
        Optional<UserEntity> owner = userRepository.findById(groupRequest.getOwnerId());
        if (owner.isEmpty()) {
            return Optional.empty(); // Retorna vazio se o usuário dono não existir
        }

        // Criar o grupo
        GroupEntity group = new GroupEntity();
        group.setName(groupRequest.getName());
        group.setDescription(groupRequest.getDescription());
        group.setOwner(owner.get());

        GroupEntity savedGroup = groupRepository.save(group);

        // Criar automaticamente um registro no GroupMemberEntity como ADMIN
        GroupMemberEntity groupMember = new GroupMemberEntity();
        groupMember.setGroup(savedGroup);
        groupMember.setUser(owner.get());
        groupMember.setRole(Role.ADMIN);

        groupMemberRepository.save(groupMember); // Salva o relacionamento

        return Optional.of(toResponseDTO(savedGroup));
    }

    /**
     * Retorna todos os grupos cadastrados no sistema.
     */
    public List<GroupResponseDTO> getAllGroups() {
        return groupRepository.findAll()
                .stream()
                .map(this::toResponseDTO)
                .collect(Collectors.toList());
    }

    /**
     * Busca um grupo pelo ID e retorna um Optional<GroupResponseDTO>.
     */
    public Optional<GroupResponseDTO> getGroupById(Long id) {
        return groupRepository.findById(id).map(this::toResponseDTO);
    }

    /**
     * Atualiza os dados de um grupo existente.
     */
    public Optional<GroupResponseDTO> updateGroup(Long id, GroupRequestDTO groupRequest) {
        return groupRepository.findById(id).map(existingGroup -> {
            existingGroup.setName(groupRequest.getName());
            existingGroup.setDescription(groupRequest.getDescription());
            GroupEntity updatedGroup = groupRepository.save(existingGroup);
            return toResponseDTO(updatedGroup);
        });
    }

    /**
     * Deleta um grupo se ele existir.
     */
    public boolean deleteGroup(Long id) {
        if (groupRepository.existsById(id)) {
            groupRepository.deleteById(id);
            return true;
        }
        return false;
    }

    /**
     * Converte um GroupEntity para GroupResponseDTO.
     */
    private GroupResponseDTO toResponseDTO(GroupEntity group) {
        GroupResponseDTO dto = new GroupResponseDTO();
        dto.setId(group.getId());
        dto.setName(group.getName());
        dto.setDescription(group.getDescription());
        dto.setOwnerId(group.getOwner().getId());
        return dto;
    }
}
