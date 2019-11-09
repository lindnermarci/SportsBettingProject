package com.epam.training.sportsbetting.repository;

import org.springframework.data.repository.CrudRepository;

import com.epam.training.sportsbetting.domain.Bet;

public interface BetRepository extends CrudRepository<Bet, Integer> {

}
