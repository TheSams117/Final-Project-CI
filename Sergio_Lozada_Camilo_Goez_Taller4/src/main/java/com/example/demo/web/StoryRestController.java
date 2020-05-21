package com.example.demo.web;

import com.example.demo.exception.StoryServiceException;
import com.example.demo.model.TsscStory;

public interface StoryRestController {
	public TsscStory createStory(TsscStory Story, long id) throws StoryServiceException ;
	public TsscStory updateStory(TsscStory Story) throws StoryServiceException;
	public void deleteStory(long id) throws StoryServiceException;
	public TsscStory getStory(long id) throws StoryServiceException;
	public Iterable<TsscStory> findAll();

}
