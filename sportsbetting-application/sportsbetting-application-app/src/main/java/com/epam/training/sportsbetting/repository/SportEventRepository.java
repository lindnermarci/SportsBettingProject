package com.epam.training.sportsbetting.repository;

import org.springframework.data.repository.CrudRepository;

import com.epam.training.sportsbetting.domain.SportEvent;

public interface SportEventRepository extends CrudRepository<SportEvent, Integer> {

}
