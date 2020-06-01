package com.example.demo.dao;

import java.util.List;

import com.example.demo.model.TsscAdmin;

public interface AdminDao {
	public TsscAdmin save(TsscAdmin entity);
	public TsscAdmin update(TsscAdmin entity);
	public TsscAdmin delete(TsscAdmin entity);
	public TsscAdmin findById(long codigo);
	public List<TsscAdmin> findAll();
	public TsscAdmin findByUser(String user);

}
