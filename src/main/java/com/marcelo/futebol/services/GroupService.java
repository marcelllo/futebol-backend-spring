package com.marcelo.futebol.services;

import com.marcelo.futebol.dto.GroupDTO;
import com.marcelo.futebol.exceptions.ApiRequestException;
import com.marcelo.futebol.models.Group;
import com.marcelo.futebol.models.Player;
import com.marcelo.futebol.repositories.GroupRepository;
import org.springframework.beans.BeanUtils;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.UUID;

@Service
public class GroupService {

    private GroupRepository groupRepository;
    private PlayerService playerService;

    public GroupService(GroupRepository groupRepository) {
        this.groupRepository = groupRepository;
    }

    public Iterable<Group> findAll() {
        return groupRepository.findAll();
    }

    public Group findById(Long id) throws Exception {
        return groupRepository.findById(id)
                .orElseThrow(() -> new ApiRequestException("Grupo não encontrado!"));
    }

    public Group create(GroupDTO groupDTO) throws Exception {
        Player player = null;

        if (groupDTO.getPlayer().getId() == 0) {
            throw new ApiRequestException("Jogador é obrigatório");
        } else {
            player = playerService.findById(groupDTO.getPlayer().getId());
        }

        if (groupDTO.getName() == null || groupDTO.getName().trim().isEmpty()) {
            throw new ApiRequestException("Nome do grupo é obrigatório");
        }

        UUID hash = UUID.randomUUID();

        Group group = new Group();
        BeanUtils.copyProperties(groupDTO, group);

        group.setHash(hash.toString());
        group.setOwner(player);

        return groupRepository.save(group);
    }

    public List<Group> findAllByPlayerId(Long playerId) {
        return groupRepository.findAllByOwner(playerId);
    }

}
