package com.epam.training.sportsbetting.repository;

import org.springframework.data.repository.CrudRepository;

import com.epam.training.sportsbetting.domain.Wager;

public interface WagerRepository  extends CrudRepository<Wager, Integer> {
@Override
default <S extends Wager> S save(S entity) {
System.out.println(entity.getId() + entity.toString());
return entity;
}
}
