package com.marcelo.futebol.repositories;

import com.marcelo.futebol.models.GroupPlayer;
import org.springframework.data.repository.CrudRepository;

public interface GroupPlayerRepository extends CrudRepository<GroupPlayer, Long> {

    boolean existsByPlayerIdAndGroupId(Long playerId, Long groupId);

}
