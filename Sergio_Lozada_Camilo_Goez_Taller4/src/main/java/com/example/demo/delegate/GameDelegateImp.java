package com.example.demo.delegate;

import org.springframework.stereotype.Component;
import org.springframework.web.client.RestTemplate;

import com.example.demo.model.TsscGame;

@Component
public class GameDelegateImp implements GameDelegate{

	private  RestTemplate restTemplate;
	
	public GameDelegateImp() {
		restTemplate = new RestTemplate();
	}
	
	@Override
	public TsscGame createGame(TsscGame game) {
		return restTemplate.postForObject("http://localhost:8080/Games", game, TsscGame.class);
	}

	@Override
	public void updateGame(TsscGame game) {
		restTemplate.put("http://localhost:8080/Games", game);
	}

	@Override
	public void deleteGame(long id) {
		restTemplate.delete("http://localhost:8080/Games/"+id, id);
	}

	@Override
	public TsscGame getGame(long id) {
		return restTemplate.getForObject("http://localhost:8080/Games/"+id, TsscGame.class);
	}

	@SuppressWarnings("unchecked")
	@Override
	public Iterable<TsscGame> findAll() {
		return restTemplate.getForObject("http://localhost:8080/Games", Iterable.class);
	}
	
}
