package com.example.demo.repository;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import com.example.demo.model.TsscTimecontrol;
@Repository
public interface TimecontrolRepository extends CrudRepository<TsscTimecontrol, Long> {

}
