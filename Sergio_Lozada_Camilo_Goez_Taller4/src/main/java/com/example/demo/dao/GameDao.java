package com.example.demo.dao;

import java.time.LocalDate;
import java.time.LocalTime;
import java.util.List;

import com.example.demo.model.TsscGame;
import com.example.demo.model.TsscTopic;

public interface GameDao {
	public TsscGame save(TsscGame entity);
	public TsscGame update(TsscGame entity);
	public TsscGame delete(TsscGame entity);
	public TsscGame findById(long codigo);
	public List<TsscGame> findByTopicName(String name);
	public List<TsscGame> findByTopicDescription(String description);
	public List<TsscGame> findByTopicId(long id);
	public List<TsscGame> findByDateTime(LocalDate date, LocalTime time1, LocalTime time2);
	public List<TsscGame> findByDate(LocalDate date1, LocalDate date2);
	public List<TsscGame> findAll();
	public List<TsscTopic> Query2A(LocalDate date);
	public List<TsscGame> Query2B(LocalDate date);
}
