package com.marcelo.futebol.services;

import com.marcelo.futebol.exceptions.ApiRequestException;
import com.marcelo.futebol.models.Group;
import com.marcelo.futebol.models.GroupPlayer;
import com.marcelo.futebol.models.Player;
import com.marcelo.futebol.repositories.GroupPlayerRepository;
import org.springframework.stereotype.Service;

@Service
public class GroupPlayerService {

    private GroupPlayerRepository groupPlayerRepository;
    private GroupService groupService;
    private PlayerService playerService;

    public GroupPlayerService(GroupPlayerRepository groupPlayerRepository, GroupService groupService, PlayerService playerService) {
        this.groupPlayerRepository = groupPlayerRepository;
        this.groupService = groupService;
        this.playerService = playerService;
    }

    public GroupPlayer create(Long groupId, Long playerId) throws Exception {
        Group group = groupService.findById(groupId);
        Player player = playerService.findById(playerId);

        if (groupPlayerRepository.existsByPlayerIdAndGroupId(playerId, groupId)) {
            throw new ApiRequestException("Jogador já participa deste grupo!");
        }

        return groupPlayerRepository.save(new GroupPlayer(0L, player, group));
    }
}
