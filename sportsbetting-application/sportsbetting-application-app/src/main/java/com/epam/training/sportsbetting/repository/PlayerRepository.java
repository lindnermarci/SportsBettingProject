package com.epam.training.sportsbetting.repository;

import java.util.List;

import org.springframework.data.repository.CrudRepository;

import com.epam.training.sportsbetting.domain.Player;


public interface PlayerRepository extends CrudRepository<Player, Integer> {
    
    List<Player> findByName(String name);
    
    List<Player> findByEmail(String name);
}
