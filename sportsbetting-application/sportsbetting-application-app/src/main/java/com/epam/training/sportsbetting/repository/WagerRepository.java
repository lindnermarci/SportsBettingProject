package com.epam.training.sportsbetting.repository;

import org.springframework.data.repository.CrudRepository;

import com.epam.training.sportsbetting.domain.Wager;

public interface WagerRepository extends CrudRepository<Wager, Integer> {
}
