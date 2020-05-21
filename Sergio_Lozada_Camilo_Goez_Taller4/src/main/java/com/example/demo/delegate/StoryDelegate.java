package com.example.demo.delegate;

import com.example.demo.model.TsscStory;

public interface StoryDelegate {

	public TsscStory createStory(TsscStory Story);
	public void updateStory(TsscStory Story);
	public void deleteStory(long id);
	public TsscStory getStory(long id);
	public Iterable<TsscStory> findAll();
}
