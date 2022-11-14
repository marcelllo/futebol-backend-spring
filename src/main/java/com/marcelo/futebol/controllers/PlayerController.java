package com.marcelo.futebol.controllers;

import com.marcelo.futebol.models.Group;
import com.marcelo.futebol.services.GroupService;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import com.marcelo.futebol.dto.PlayerDTO;
import com.marcelo.futebol.models.Player;
import com.marcelo.futebol.services.PlayerService;

import java.util.List;

@RestController
@RequestMapping("/api/player")
public class PlayerController {

  private final PlayerService playerService;
  private final GroupService groupService;

  public PlayerController(PlayerService playerService, GroupService groupService) {
    this.playerService = playerService;
    this.groupService = groupService;
  }

  @GetMapping()
  public @ResponseBody Iterable<Player> index() {
    return playerService.listAll();
  }

  @PostMapping()
  public @ResponseBody Player create(@RequestBody PlayerDTO player) throws Exception {
    return playerService.save(player);
  }

  @PutMapping("/{id}")
  public @ResponseBody Player update(@RequestBody PlayerDTO player, @PathVariable long id) throws Exception {
    return playerService.save(player, id);
  }

  @GetMapping("/{id}")
  public @ResponseBody Player findOne(@PathVariable long id) throws Exception {
    return playerService.findById(id);
  }

  @GetMapping("/{id}/groups")
  public @ResponseBody List<Group> listGroups(@PathVariable long id) throws Exception {
    return groupService.findAllByPlayerId(id);
  }
}
