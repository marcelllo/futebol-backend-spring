package com.marcelo.futebol.controllers;

import com.marcelo.futebol.dto.GroupDTO;
import com.marcelo.futebol.dto.PlayerDTO;
import com.marcelo.futebol.exceptions.ApiRequestException;
import com.marcelo.futebol.models.Group;
import com.marcelo.futebol.services.GroupService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/group")
public class GroupController {

    private GroupService groupService;

    public GroupController(GroupService groupService) {
        this.groupService = groupService;
    }

    @GetMapping
    public @ResponseBody Iterable<Group> index() {
        return groupService.findAll();
    }

    @PostMapping
    public @ResponseBody Group create(@RequestBody GroupDTO groupDTO) throws Exception {
        return groupService.create(groupDTO);
    }

    @PostMapping("/{groupId}/player/{playerId}")
    public void addPlayer(@PathVariable() Long groupId, @PathVariable Long playerId) throws Exception {
        System.out.println(groupId);
        System.out.println(playerId);
    }
}
