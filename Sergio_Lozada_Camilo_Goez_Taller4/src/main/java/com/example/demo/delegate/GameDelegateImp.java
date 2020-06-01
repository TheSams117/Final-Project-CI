package com.example.demo.delegate;

import org.springframework.stereotype.Component;
import org.springframework.web.client.RestTemplate;

import com.example.demo.model.TsscGame;
import com.example.demo.model.TsscTopic;

@Component
public class GameDelegateImp implements GameDelegate{

	private  RestTemplate restTemplate;
	
	public GameDelegateImp() {
		restTemplate = new RestTemplate();
	}
	
	@Override
	public TsscGame createGame(TsscGame game) {
		restTemplate.postForObject("http://localhost:8080/api/Games", game,Boolean.class);
		return game;
	}

	@Override
	public void updateGame(TsscGame game) {
		restTemplate.put("http://localhost:8080/api/Games", game);
	}
	
	@Override
	public void updateGame(TsscTopic topic) {
		restTemplate.put("http://localhost:8080/api/Topic", topic);
	}

	@Override
	public void deleteGame(long id) {
		restTemplate.delete("http://localhost:8080/api/Games/"+id, id);
	}

	@Override
	public TsscGame getGame(long id) {
		return restTemplate.getForObject("http://localhost:8080/api/Games/"+id, TsscGame.class);
	}

	@SuppressWarnings("unchecked")
	@Override
	public Iterable<TsscGame> findAll() {
		return restTemplate.getForObject("http://localhost:8080/api/Games", Iterable.class);
	}
	
}
