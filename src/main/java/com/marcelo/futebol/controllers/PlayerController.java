package com.marcelo.futebol.controllers;

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

@RestController
@RequestMapping("/api/player")
public class PlayerController {

  private PlayerService playerService;

  public PlayerController(PlayerService playerService) {
    this.playerService = playerService;
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

}
