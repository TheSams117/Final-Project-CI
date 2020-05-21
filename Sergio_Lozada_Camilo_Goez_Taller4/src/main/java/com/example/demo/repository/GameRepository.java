package com.example.demo.repository;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import com.example.demo.model.TsscGame;

@Repository
public interface GameRepository extends CrudRepository<TsscGame, Long> {

}
