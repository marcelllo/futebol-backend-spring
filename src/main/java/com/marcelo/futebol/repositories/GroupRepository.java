package com.marcelo.futebol.repositories;

import com.marcelo.futebol.models.Group;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.repository.CrudRepository;

import java.util.List;

public interface GroupRepository extends JpaRepository<Group, Long> {
    public List<Group> findAllByOwner(Long playerId);
}
