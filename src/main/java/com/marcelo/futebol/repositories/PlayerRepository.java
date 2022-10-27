package com.marcelo.futebol.repositories;

import org.springframework.data.repository.CrudRepository;

import com.marcelo.futebol.models.Player;

public interface PlayerRepository extends CrudRepository<Player, Long> {

  public boolean existsByPhone(String phone);

  public boolean existsByEmail(String email);

}