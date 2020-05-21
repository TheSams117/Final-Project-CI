package com.example.demo.dao;

import java.util.List;

import com.example.demo.model.TsscTimecontrol;

public interface TimecontrolDao {
	public TsscTimecontrol save(TsscTimecontrol entity);
	public TsscTimecontrol update(TsscTimecontrol entity);
	public TsscTimecontrol delete(TsscTimecontrol entity);
	public TsscTimecontrol findById(long codigo);
	public List<TsscTimecontrol> findAll();	

}
