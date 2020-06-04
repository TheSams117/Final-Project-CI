package com.example.demo.service;

import java.time.LocalDate;

import com.example.demo.exception.GameServiceException;
import com.example.demo.model.TsscGame;
import com.example.demo.model.TsscTopic;

public interface GameService {
	public boolean createGame(Object game) throws GameServiceException;
	public boolean updateGame(Object game) throws GameServiceException;
	public void deleteGame(TsscGame game) throws GameServiceException;
	public TsscGame getGame(long id) throws GameServiceException;
	public Iterable<TsscGame> findAll();
	public Iterable<TsscTopic> getTopics();
	public void editTopic(TsscTopic topic);
	public Iterable<TsscTopic> queryTopics(LocalDate date);
	public Iterable<TsscGame> queryGames(LocalDate date);
	
}
