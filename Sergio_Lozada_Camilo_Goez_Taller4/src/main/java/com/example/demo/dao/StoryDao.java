package com.example.demo.dao;

import java.util.List;

import com.example.demo.model.TsscStory;

public interface StoryDao {
	public TsscStory save(TsscStory entity);
	public TsscStory update(TsscStory entity);
	public TsscStory delete(TsscStory entity);
	public TsscStory findById(long codigo);
	public List<TsscStory> findAll();	
}
