package com.example.demo.service;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import com.example.demo.exception.GameServiceException;
import com.example.demo.model.TsscGame;
import com.example.demo.model.TsscStory;
import com.example.demo.model.TsscTimecontrol;
import com.example.demo.model.TsscTopic;
import com.example.demo.dao.GameDao;
import com.example.demo.dao.TopicDao;

@Service
public class GameServiceImp implements GameService {
	@Autowired
	private GameDao GameDao;
	@Autowired
	private TopicDao TopicDao;
	
	public boolean createGame(Object game) throws GameServiceException {
		if (game == null) {
			throw new GameServiceException("CreateGame: The new game or topic can be null");
		} if (game instanceof TsscGame ) {
			if(((TsscGame) game).getNGroups() <= 0) {
				throw new GameServiceException("CreateGame: Number of groups equal or less to 0");
			}else if(((TsscGame) game).getNSprints() <= 0) {
				throw new GameServiceException("CreateGame: Number of sptrints equal or less to 0");
			}
			GameDao.save((TsscGame) game);
			
			return true ;
		}else if(game instanceof TsscTopic) {
			if(TopicDao.findById(((TsscTopic) game).getId()) == null) {
				throw new GameServiceException("CreateGame: The topic to add dosen't exist.");
			}
			TsscGame newGame = new TsscGame();
			
			ArrayList<TsscStory> listStories = new ArrayList<>();
			listStories.addAll(((TsscTopic) game).getTsscStories());
			
			ArrayList<TsscTimecontrol> listTimecontrol = new ArrayList<>();
			listTimecontrol.addAll(((TsscTopic) game).getTsscTimeControl());
			
			newGame.setTsscStories(listStories);
			newGame.setTsscTimecontrol(listTimecontrol);
			
			GameDao.save(newGame);
			
			((TsscTopic) game).getTsscGames().add(newGame);
			
		
			TopicDao.update((TsscTopic) game);
				
			return true;
		}
		
		return false;
	}
	
	public boolean updateGame(Object game) throws GameServiceException {
		if (game == null) {
			throw new GameServiceException("UpdateGame: The new game or topic can be null");
		} if (game instanceof TsscGame ) {
			if(GameDao.findById(((TsscGame) game).getId()) == null) {
				throw new GameServiceException("UpdateGame: The game to update dosen't exist");
			}else if(((TsscGame) game).getNGroups() <= 0) {
				throw new GameServiceException("UpdateGame: Number of groups equal or less to 0");
			}else if(((TsscGame) game).getNSprints() <= 0) {
				throw new GameServiceException("UpdateGame: Number of sptrints equal or less to 0");
			}				
			
			
			GameDao.update((TsscGame) game);
			
			
			return true;
			
		}else if(game instanceof TsscTopic) {
			
			if(TopicDao.findById(((TsscTopic) game).getId())==null) {
				throw new GameServiceException("UpdateGame: The topic to update dosen't exist.");
			}
			
		
				
			
			TopicDao.update((TsscTopic) game);
				
			return true;
		}
		
		return false;
	}
	
	public void deleteGame(TsscGame game) throws GameServiceException {
		List<TsscStory> stories = game.getTsscStories();
		List<TsscTimecontrol> timecontrols = game.getTsscTimecontrols();
		
		for (int i = 0; i < stories.size(); i++) {
			if(stories.size()!=0) {
				
				stories.get(i).setTsscGame(null);
			}
	
		}
		
		for (int i = 0; i < timecontrols.size(); i++) {

			if(timecontrols.size()!=0) {
				
				timecontrols.get(i).setTsscGame(null);
			}
		}
		GameDao.delete(game);
	}
	
	public TsscGame getGame(long id) throws GameServiceException {
		return GameDao.findById(id);
	}
	
	public TopicDao getTopicDao() {
		return TopicDao;
	}

	public void setTopicDao(TopicDao TopicDao) {
		this.TopicDao = TopicDao;
	}

	@Override
	public Iterable<TsscGame> findAll() {
		return GameDao.findAll();
	}

	@Override
	public Iterable<TsscTopic>  getTopics() {		
		return TopicDao.findAll();
	}

	@Override
	public void editTopic(TsscTopic topic) {
		TopicDao.save(topic);
		
		
	}
	
	@Override
	public Iterable<TsscTopic> queryTopics(LocalDate date){
		return GameDao.Query2A(date);
	}
	
	@Override
	public Iterable<TsscGame> queryGames(LocalDate date){
		return GameDao.Query2B(date);
	}

	

}
