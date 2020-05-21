package com.example.demo.delegate;

import org.springframework.stereotype.Component;
import org.springframework.web.client.RestTemplate;

import com.example.demo.model.TsscGame;

@Component
public class GameDelegateImp implements GameDelegate{

	private  RestTemplate restTemplate;
	
	public GameDelegateImp() {
		// TODO Auto-generated constructor stub
		restTemplate = new RestTemplate();
	}
	
	@Override
	public TsscGame createGame(TsscGame game) {
		// TODO Auto-generated method stub
		return restTemplate.postForObject("http://localhost:8080/Games", game, TsscGame.class);
	}

	@Override
	public void updateGame(TsscGame game) {
		// TODO Auto-generated method stub
		restTemplate.put("http://localhost:8080/Games", game);
	}

	@Override
	public void deleteGame(long id) {
		// TODO Auto-generated method stub
		restTemplate.delete("http://localhost:8080/Games", id);
	}

	@Override
	public TsscGame getGame(long id) {
		// TODO Auto-generated method stub
		return restTemplate.getForObject("http://localhost:8080/Games"+id, TsscGame.class);
	}

	@SuppressWarnings("unchecked")
	@Override
	public Iterable<TsscGame> findAll() {
		// TODO Auto-generated method stub
		return restTemplate.getForObject("http://localhost:8080/Games", Iterable.class);
	}
	
}
