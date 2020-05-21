package com.example.demo.repository;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import com.example.demo.model.TsscGroup;

@Repository
public interface GroupRepository extends CrudRepository<TsscGroup, Long> {

}
