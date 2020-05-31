package com.example.demo.DelegateUnitTesting;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.when;

import java.util.ArrayList;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.MockitoJUnitRunner;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit.jupiter.SpringExtension;
import org.springframework.web.client.RestTemplate;

import com.example.demo.delegate.GameDelegate;
import com.example.demo.model.TsscGame;

@ExtendWith(SpringExtension.class)
@RunWith(MockitoJUnitRunner.class)
@SpringBootTest
class GameDelegateTest {
	
	@Mock 
	private RestTemplate restTemplate;
	
	@InjectMocks
	private GameDelegate GameDelegate;
	
	private TsscGame GameTest;
	
	@Autowired
	public GameDelegateTest(GameDelegate GameDelegate) {
		this.GameDelegate = GameDelegate;
	}
	
	public void setUpOne() {
		GameTest = new TsscGame();
	}

	@Test
	void testCreateGame() {
		assertNotNull(GameDelegate);
		setUpOne();
		
		when(restTemplate.postForObject("http://localhost:8080/Games", GameTest, TsscGame.class)).thenReturn(GameTest);
		
		assertTrue(GameDelegate.createGame(GameTest)!=null);
	}
	
	@Test
	void testUpdateGame() {
		assertNotNull(GameDelegate);
		setUpOne();
		
		when(restTemplate.postForObject("http://localhost:8080/Games", GameTest, TsscGame.class)).thenReturn(GameTest);
		
		assertTrue(GameDelegate.createGame(GameTest)!=null);
		
		GameTest.setName("Game1");
		
		GameDelegate.updateGame(GameTest);
	}
	
	@Test
	void testDeleteGame() {
		assertNotNull(GameDelegate);
		setUpOne();
		
		when(restTemplate.postForObject("http://localhost:8080/Games", GameTest, TsscGame.class)).thenReturn(GameTest);
		
		assertTrue(GameDelegate.createGame(GameTest)!=null);
		
		GameDelegate.deleteGame(GameTest.getId());
	}
	
	@Test
	void testGetGame() {
		assertNotNull(GameDelegate);
		setUpOne();
		
		when(restTemplate.postForObject("http://localhost:8080/Games", GameTest, TsscGame.class)).thenReturn(GameTest);
		
		assertTrue(GameDelegate.createGame(GameTest)!=null);
		
		when(restTemplate.getForObject("http://localhost:8080/Games/"+GameTest.getId(), TsscGame.class)).thenReturn(GameTest);
		
		assertTrue(GameDelegate.getGame(GameTest.getId())!=null);
	}
	
	@Test
	void testFindAllGame() {
		assertNotNull(GameDelegate);
		setUpOne();
		
		ArrayList<TsscGame> list  = new ArrayList<TsscGame>();
		
		list.add(GameTest);
		
		when(restTemplate.postForObject("http://localhost:8080/Games", GameTest, TsscGame.class)).thenReturn(GameTest);
		
		assertTrue(GameDelegate.createGame(GameTest)!=null);
		
		when(restTemplate.getForObject("http://localhost:8080/Games", Iterable.class)).thenReturn(list);
		
		assertTrue(((ArrayList<TsscGame>)GameDelegate.findAll()).get(0)!=null);
	}

}
