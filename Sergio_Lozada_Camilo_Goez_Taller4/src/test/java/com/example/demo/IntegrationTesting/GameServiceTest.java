package com.example.demo.IntegrationTesting;

import static org.junit.jupiter.api.Assertions.*;

import java.util.ArrayList;

import org.junit.jupiter.api.AfterAll;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Nested;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit.jupiter.SpringExtension;

import com.example.demo.exception.GameServiceException;
import com.example.demo.model.TsscGame;
import com.example.demo.model.TsscStory;
import com.example.demo.model.TsscTimecontrol;
import com.example.demo.model.TsscTopic;
import com.example.demo.dao.GameDao;
import com.example.demo.dao.TopicDao;
import com.example.demo.service.GameService;

@ExtendWith(SpringExtension.class)
@SpringBootTest
class GameServiceTest {
	private GameDao GameDao;
	
	private TopicDao TopicDao;
	
	private GameService gameService;
	
	private TsscGame newGame;
	
	private TsscTopic newTopic;
	
	@Autowired
	public GameServiceTest(GameService gameService, TopicDao TopicDao, GameDao GameDao) {
		this.gameService = gameService;
		this.TopicDao = TopicDao;
		this.GameDao = GameDao;
	}
	
	
	@BeforeAll
	static void setUp() {
		System.out.println("-----> SETUP <-----");
		
	}

	
	@Nested
	@DisplayName("Create Game Test")
	class testCreate {
		
		@Test //Prueba 10
		void testCreateGame0() throws GameServiceException {
			newGame = null;
			
			newTopic = null;
			
			Throwable exceptionOne = assertThrows(GameServiceException.class, ()->{gameService.createGame(newGame);});
			
			assertEquals("CreateGame: The new game or topic can be null", exceptionOne.getMessage());
			
			
			Throwable exceptionTwo = assertThrows(GameServiceException.class, ()->{gameService.createGame(newTopic);});
			
			assertEquals("CreateGame: The new game or topic can be null", exceptionTwo.getMessage());
			
			
		}
		
		@Test //Prueba 11
		void testCreateGame1() throws GameServiceException {
			newGame = new TsscGame();
			
			
			assertTrue(gameService.createGame(newGame),"The game haven't been created");

		}
		
		@Test //Prueba 12
		void testCreateGame2() throws GameServiceException {
			newGame = new TsscGame();
			newGame.setNGroups(0);
					
			Throwable exception = assertThrows(GameServiceException.class, ()->{gameService.createGame(newGame);});
			
			assertEquals("CreateGame: Number of groups equal or less to 0", exception.getMessage());
			
		}
		
		@Test//Prueba 13
		void testCreateGame3() throws GameServiceException {
			newGame = new TsscGame();
			newGame.setNSprints(0);
						
			Throwable exception = assertThrows(GameServiceException.class, ()->{gameService.createGame(newGame);});
			
			assertEquals("CreateGame: Number of sptrints equal or less to 0", exception.getMessage());
			
		}
		
		@Test//Prueba 14
		void testCreateGame4() throws GameServiceException {
			newTopic = new TsscTopic();
			
			Throwable exception = assertThrows(GameServiceException.class, ()->{gameService.createGame(newTopic);});
			
			assertEquals("CreateGame: The topic to add dosen't exist.", exception.getMessage());
			
		}
		
		@Test//Prueba 15
		void testCreateGame5() throws GameServiceException {
			newTopic = new TsscTopic();
			newTopic.setDefaultGroups(1);
			newTopic.setDefaultSprints(1);
			newTopic.setTsscStories(new ArrayList<>());
			newTopic.setTsscTimeControl(new ArrayList<>());
			newTopic.setTsscGames(new ArrayList<>());
			
			TopicDao.save(newTopic);
			
			assertTrue(gameService.createGame(newTopic),"CreateGame: The topic haven't been added.");
			
			
		}
		
		@Test //Prueba 16 (Punto 5.d del taller)
		void testCreateGame6() throws GameServiceException {
			newTopic = new TsscTopic();
			newTopic.setDefaultGroups(1);
			newTopic.setDefaultSprints(1);
			newTopic.setTsscGames(new ArrayList<>());
			newTopic.setTsscStories(new ArrayList<>());
			newTopic.setTsscTimeControl(new ArrayList<>());
			
			newTopic.getTsscStories().add(new TsscStory());
			newTopic.getTsscTimeControl().add(new TsscTimecontrol());
			
			TsscGame createdGame = null;
			
			TopicDao.save(newTopic);
			
			gameService.createGame(newTopic);
			
			createdGame = newTopic.getTsscGames().get(0);
			
			for (int i = 0; i < newTopic.getTsscStories().size(); i++) {
				assertTrue(newTopic.getTsscStories().get(i).getId() == createdGame.getTsscStories().get(i).getId(),"CreateGame: The Storys aren't the same");
			}
			
			for (int i = 0; i < newTopic.getTsscTimeControl().size(); i++) {
				assertTrue(newTopic.getTsscTimeControl().get(i).getId() == createdGame.getTsscTimecontrols().get(i).getId(),"CreateGame: The Timecontrols aren't the same");
				
			}
					
		}
		
	}
	
	@Nested
	@DisplayName("Update Game Test")
	class testUpdate {
		
		@Test //Prueba 17
		void testUpdateGame0() throws GameServiceException {
			newGame = null;
			
			newTopic = null;
			
			Throwable exceptionOne = assertThrows(GameServiceException.class, ()->{gameService.updateGame(newGame);});
			
			assertEquals("UpdateGame: The new game or topic can be null", exceptionOne.getMessage());
			
			
			Throwable exceptionTwo = assertThrows(GameServiceException.class, ()->{gameService.updateGame(newTopic);});
			
			assertEquals("UpdateGame: The new game or topic can be null", exceptionTwo.getMessage());
			
		}
		
		@Test //Prueba 18
		void testUpdateGame1() throws GameServiceException {
			newGame = new TsscGame();
			newGame.setNGroups(0);
						
			GameDao.save(newGame);
		
			Throwable exception = assertThrows(GameServiceException.class, ()->{gameService.updateGame(newGame);});
			
			assertEquals("UpdateGame: Number of groups equal or less to 0", exception.getMessage());
			
		}
		
		@Test //Prueba 19
		void testUpdateGame2() throws GameServiceException {
			newGame = new TsscGame();
			newGame.setNSprints(0);

			GameDao.save(newGame);
			
			Throwable exception = assertThrows(GameServiceException.class, ()->{gameService.updateGame(newGame);});
			
			assertEquals("UpdateGame: Number of sptrints equal or less to 0", exception.getMessage());
			
		}
		
		@Test //Prueba 20
		void testUpdateGame3() throws GameServiceException {
			newGame = new TsscGame();
						
			Throwable exception = assertThrows(GameServiceException.class, ()->{gameService.updateGame(newGame);});
			
			assertEquals("UpdateGame: The game to update dosen't exist", exception.getMessage());
		}

		@Test //Prueba 21
		void testUpdateGame4() throws GameServiceException {
			newGame = new TsscGame();
			
			GameDao.save(newGame);
							
			assertTrue(gameService.updateGame(newGame),"The game haven't been created");
				
		}
		
		@Test//Prueba 22
		void testUpdateGame5() throws GameServiceException {
			newTopic = new TsscTopic();
			
			Throwable exception = assertThrows(GameServiceException.class, ()->{gameService.updateGame(newTopic);});
			
			assertEquals("UpdateGame: The topic to update dosen't exist.", exception.getMessage());
		}
		
		@Test //Prueba 23 
		void testUpdateGame6() throws GameServiceException {
			newTopic = new TsscTopic();
			
			TopicDao.save(newTopic);
			
			assertTrue(gameService.updateGame(newTopic),"UpdateGame: The topic haven't been updated.");
		
		}
		
	}
	
	@AfterAll
	static void afterTest() {
		System.out.println("-----> DESTROY <-----");
	}
}
