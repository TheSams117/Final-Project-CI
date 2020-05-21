package com.example.demo.web;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import com.example.demo.exception.GameServiceException;
import com.example.demo.model.TsscGame;
import com.example.demo.service.GameService;

@RestController
public class GameRestControllerImp implements GameRestController {
	@Autowired
	private GameService GameService;
	
	@PostMapping("/Games")
	@Override
	public boolean createGame(@RequestBody Object Game) throws GameServiceException {
		return GameService.createGame(Game);
	}
	
	@PutMapping("/Games")
	@Override
	public boolean updateGame(@RequestBody Object Game) throws GameServiceException {
		return GameService.updateGame(Game);
	}
	
	@DeleteMapping("/Games/{id}")
	@Override
	public void deleteGame(@PathVariable("id") long id) throws GameServiceException {
		TsscGame Game = GameService.getGame(id);
		GameService.deleteGame(Game);
		
	}
	
	@GetMapping("/Games/{id}")
	@Override
	public TsscGame getGame(@PathVariable("id") long id) throws GameServiceException {
		return GameService.getGame(id);
	}
	
	@GetMapping("/Games")
	@Override
	public Iterable<TsscGame> findAll() {
		return GameService.findAll();
	}

}
