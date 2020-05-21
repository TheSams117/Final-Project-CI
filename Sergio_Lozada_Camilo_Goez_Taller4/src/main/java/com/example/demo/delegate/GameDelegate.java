package com.example.demo.delegate;

import com.example.demo.model.TsscGame;

public interface GameDelegate {

	public TsscGame createGame(TsscGame game);
	public void updateGame(TsscGame game);
	public void deleteGame(long id);
	public TsscGame getGame(long id);
	public Iterable<TsscGame> findAll();
}
