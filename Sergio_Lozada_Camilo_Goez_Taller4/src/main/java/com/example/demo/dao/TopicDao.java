package com.example.demo.dao;

import java.util.List;

import com.example.demo.model.TsscTopic;

public interface TopicDao {
	public TsscTopic save(TsscTopic entity);
	public TsscTopic update(TsscTopic entity);
	public TsscTopic delete(TsscTopic entity);
	public TsscTopic findById(long codigo);
	public TsscTopic findByName(String name);
	public TsscTopic findByDescription(String Description);
	public List<TsscTopic> findAll();


}
