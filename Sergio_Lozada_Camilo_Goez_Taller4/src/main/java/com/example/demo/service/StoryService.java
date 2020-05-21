package com.example.demo.service;

import com.example.demo.exception.StoryServiceException;
import com.example.demo.model.TsscStory;

public interface StoryService {
	public TsscStory createStoryService(TsscStory story, long id) throws StoryServiceException;
	public TsscStory updateStoryService(TsscStory story) throws StoryServiceException;
	public void deleteStoryService(TsscStory story) throws StoryServiceException;
	public TsscStory getStoryService(long id) throws StoryServiceException;
	public Iterable<TsscStory> findAll();
}
