package com.example.demo.repository;

import org.springframework.data.repository.CrudRepository;

import com.example.demo.model.TsscAdmin;

public interface AdminRepository extends CrudRepository<TsscAdmin, Long> {
	
	TsscAdmin findByUser(String user);

}
