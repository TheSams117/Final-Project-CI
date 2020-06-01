package com.example.demo.delegate;

import com.example.demo.model.TsscGame;
import com.example.demo.model.TsscTopic;

public interface GameDelegate {

	public TsscGame createGame(TsscGame game);
	public void updateGame(TsscGame game);
	public void updateGame(TsscTopic game);
	public void deleteGame(long id);
	public TsscGame getGame(long id);
	public Iterable<TsscGame> findAll();
}
