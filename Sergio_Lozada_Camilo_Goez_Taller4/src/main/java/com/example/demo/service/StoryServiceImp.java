package com.example.demo.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.example.demo.exception.StoryServiceException;
import com.example.demo.model.TsscGame;
import com.example.demo.model.TsscStory;
import com.example.demo.dao.GameDao;
import com.example.demo.dao.StoryDao;

@Service
public class StoryServiceImp implements StoryService {
	
	@Autowired
	private StoryDao StoryDao;
	@Autowired
	private GameDao GameDao;
	
	public TsscStory createStoryService(TsscStory story, long id) throws StoryServiceException {
		if(story == null) {
			
			throw new StoryServiceException("CreateStory: The new story can be null");
		}
		
		TsscGame game = GameDao.findById(id);
		
		if(GameDao.findById(id) == null) {
			throw new StoryServiceException("CreateStory: The game of new Story haven't been created ");
		}
		
		story.setTsscGame(game);
		
		
		if(story.getTsscGame() == null) {
			throw new StoryServiceException("CreateStory: The new story need have a game");
		}else if(story.getInitialSprint().intValue() <= 0 || story.getBusinessValue().intValue() <= 0 || story.getPriority().intValue() <= 0) {
			throw new StoryServiceException("CreateStory: The business value, initial sprint or the priority are equal or less to 0");
		}
		
		//game.addTsscStory(story);
		GameDao.update(game);
		
		return StoryDao.save(story);
	}
	
	public TsscStory updateStoryService(TsscStory story) throws StoryServiceException {
		
		if(story == null) {
			throw new StoryServiceException("UpdateStory: The story to update can be null");
		}else if(StoryDao.findById(story.getId()) == null) {
			throw new StoryServiceException("UpdateStory: The story to update doesn't exist");
		}
		
		story.setTsscGame(StoryDao.findById(story.getId()).getTsscGame());
		if(GameDao.findById(story.getTsscGame().getId()) == null) {
			throw new StoryServiceException("UpdateStory: The game of Story to update haven't been created ");
		}else if(story.getTsscGame() == null) {
			throw new StoryServiceException("UpdateStory: The story to update need have a game");
		}else if(story.getInitialSprint().intValue() <= 0 || story.getBusinessValue().intValue() <= 0 || story.getPriority().intValue() <= 0) {
			throw new StoryServiceException("UpdateStory: The business value, initial sprint or the priority are equal or less to 0");
		}
		
		return StoryDao.update(story);
	}
	
	public void deleteStoryService(TsscStory story) throws StoryServiceException {
		story.setTsscGame(null);
		StoryDao.delete(story);
	}

	public TsscStory getStoryService(long id) throws StoryServiceException {
		
		return StoryDao.findById(id);
	}

	@Override
	public Iterable<TsscStory> findAll() {
		return StoryDao.findAll();
	}



}
