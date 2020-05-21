package com.example.demo.DelegateUnitTesting;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.when;

import org.junit.jupiter.api.BeforeEach;
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
	private GameDelegate gameDelegate;
	
	private TsscGame gameTest;
	
	@Autowired
	public GameDelegateTest(GameDelegate gameDelegate) {
		// TODO Auto-generated constructor stub
		this.gameDelegate=gameDelegate;
	}
	
	@BeforeEach
	public void setUpOne() {
		gameTest = new TsscGame();
	}
	
	@Test
	void testCreateGame() {
		assertNotNull(gameDelegate);
		
		when(restTemplate.postForObject("http://localhost:8080/Games", gameTest, TsscGame.class)).thenReturn(gameTest);
		
		assertTrue(gameDelegate.createGame(gameTest)!=null);
	}
	
	@Test
	void testUpdateGame() {
		assertNotNull(gameDelegate);
		gameDelegate.createGame(gameTest);
		
		when(restTemplate.postForObject("http://localhost:8080/Games", gameTest, TsscGame.class)).thenReturn(gameTest);
		
		assertTrue(gameDelegate.createGame(gameTest)!=null);
	}

}
