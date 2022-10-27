package com.marcelo.futebol.services;

import java.lang.StackWalker.Option;
import java.sql.Timestamp;
import java.time.ZoneId;
import java.time.ZonedDateTime;
import java.util.Optional;

import org.springframework.data.crossstore.ChangeSetPersister.NotFoundException;
import org.springframework.stereotype.Service;

import com.marcelo.futebol.dto.PlayerDTO;
import com.marcelo.futebol.exceptions.ApiRequestException;
import com.marcelo.futebol.models.Player;
import com.marcelo.futebol.repositories.PlayerRepository;

@Service
public class PlayerService {
  private final PlayerRepository repository;

  public PlayerService(PlayerRepository repository) {
    this.repository = repository;
  }

  public Iterable<Player> listAll() {
    return repository.findAll();
  }

  public boolean playerExists(Long id) {
    return repository.existsById(id);
  }

  public Player findById(Long id) throws Exception {
    return repository.findById(id).orElseThrow(() -> new ApiRequestException("Jogador não encontrado!"));
  }

  public Player save(PlayerDTO playerDto) throws Exception {

    if (playerDto.getEmail() == null) {
      throw new ApiRequestException("E-mail é obrigatório!");
    }

    if (playerDto.getPhone() == null) {
      throw new ApiRequestException("Telefone é obrigatório!");
    }

    if (repository.existsByPhone(playerDto.getEmail())) {
      throw new ApiRequestException("E-mail já cadastrado!");
    }

    if (repository.existsByPhone(playerDto.getPhone())) {
      throw new ApiRequestException("Telefone já cadastrado!");
    }

    ZonedDateTime now = ZonedDateTime.now(ZoneId.of("Z"));

    Player player = new Player();
    player.setName(playerDto.getName());
    player.setPhone(playerDto.getPhone());
    player.setEmail(playerDto.getEmail());
    player.setCreatedAt(Timestamp.valueOf(now.toLocalDateTime()));
    player.setUpdatedAt(Timestamp.valueOf(now.toLocalDateTime()));

    return repository.save(player);
  }

  public Player save(PlayerDTO player, long id) throws Exception {
    Optional<Player> dbPlayer = repository.findById(id);
    if (dbPlayer.isEmpty()) {
      throw new ApiRequestException("Jogador não encontrado!");
    }

    ZonedDateTime now = ZonedDateTime.now(ZoneId.of("Z"));

    Player updatedPlayer = dbPlayer.get();
    updatedPlayer.setName(player.getName());
    updatedPlayer.setPhone(player.getPhone());
    updatedPlayer.setUpdatedAt(Timestamp.valueOf(now.toLocalDateTime()));

    return repository.save(updatedPlayer);
  }
}
