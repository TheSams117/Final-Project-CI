package com.example.demo.web;

import com.example.demo.exception.GameServiceException;
import com.example.demo.model.TsscGame;
import com.example.demo.model.TsscTopic;

public interface GameRestController {
	public Boolean createGame(TsscGame game) throws GameServiceException;
	public boolean updateGame(TsscGame game) throws GameServiceException;
	public boolean updateTopic(TsscTopic topic) throws GameServiceException;
	public void deleteGame(long id) throws GameServiceException;
	public TsscGame getGame(long id) throws GameServiceException;
	public Iterable<TsscGame> findAll();

}
