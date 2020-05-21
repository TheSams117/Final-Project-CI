package com.example.demo.web;

import com.example.demo.exception.GameServiceException;
import com.example.demo.model.TsscGame;

public interface GameRestController {
	public boolean createGame(Object game) throws GameServiceException;
	public boolean updateGame(Object game) throws GameServiceException;
	public void deleteGame(long id) throws GameServiceException;
	public TsscGame getGame(long id) throws GameServiceException;
	public Iterable<TsscGame> findAll();

}
