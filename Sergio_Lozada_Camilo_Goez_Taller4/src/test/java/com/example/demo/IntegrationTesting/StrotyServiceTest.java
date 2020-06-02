package com.example.demo.IntegrationTesting;

import static org.junit.jupiter.api.Assertions.*;

import java.math.BigDecimal;

import org.junit.jupiter.api.AfterAll;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Nested;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit.jupiter.SpringExtension;

import com.example.demo.exception.StoryServiceException;
import com.example.demo.model.TsscGame;
import com.example.demo.model.TsscStory;
import com.example.demo.dao.GameDao;
import com.example.demo.dao.StoryDao;
import com.example.demo.service.StoryService;

@ExtendWith(SpringExtension.class)
@SpringBootTest
class StrotyServiceTest {
	
	private GameDao GameDao;
	
	private StoryDao StoryDao;
	
	private StoryService storyService;
	
	private TsscGame newGame;
	
	private TsscStory newStory;
	
	@Autowired
	public StrotyServiceTest(StoryService storyService, StoryDao StoryDao, GameDao GameDao) {
		this.storyService = storyService;
		this.StoryDao = StoryDao;
		this.GameDao = GameDao;
		
	}

	@BeforeAll
	static void setUp() {
		System.out.println("-----> SETUP <-----");
	}
	
	void setUpOne() {
		
	}

	@Nested
	@DisplayName("Create Story Test")
	class testCreate {
		@Test //Prueba 24
		void testCreateStory0() throws StoryServiceException {
			newStory = null;
			
			Throwable exceptionOne = assertThrows(StoryServiceException.class, ()->{storyService.createStoryService(newStory,1);});
			
			assertEquals("CreateStory: The new story can be null", exceptionOne.getMessage());
		}
		
		@Test //Prueba 25
		void testCreateStory1() throws StoryServiceException {
			newStory = new TsscStory();
			
			Throwable exceptionOne = assertThrows(StoryServiceException.class, ()->{storyService.createStoryService(newStory,0);});
			
			assertEquals("CreateStory: The new story need have a game", exceptionOne.getMessage());
		}
		
		@Test //Prueba 26
		void testCreateStory2() throws StoryServiceException {
			newStory = new TsscStory();
			newGame = new TsscGame();
			
			newStory.setTsscGame(newGame);
			
			Throwable exceptionOne = assertThrows(StoryServiceException.class, ()->{storyService.createStoryService(newStory,1);});
			
			assertEquals("CreateStory: The game of new Story haven't been created ", exceptionOne.getMessage());
		
		}
		
		@Test //Prueba 27
		void testCreateStory3() throws StoryServiceException {
			newStory = new TsscStory();
			newGame = new TsscGame();
			
			newStory.setBusinessValue(new BigDecimal(0));
			newStory.setInitialSprint(new BigDecimal(0));
			newStory.setPriority(new BigDecimal(0));
			
			newStory.setTsscGame(newGame);
			
			GameDao.save(newGame);
			
			Throwable exceptionOne = assertThrows(StoryServiceException.class, ()->{storyService.createStoryService(newStory,1);});
			
			assertEquals("CreateStory: The business value, initial sprint or the priority are equal or less to 0", exceptionOne.getMessage());
		
		}
		
		@Test //Prueba 28
		void testCreateStory4() throws StoryServiceException {
			newStory = new TsscStory();
			newGame = new TsscGame();
			
			newStory.setBusinessValue(new BigDecimal(1));
			newStory.setInitialSprint(new BigDecimal(1));
			newStory.setPriority(new BigDecimal(1));
			
			newStory.setTsscGame(newGame);
		
			GameDao.save(newGame);
						
			assertTrue(storyService.createStoryService(newStory,1) != null,"The story haven't been created");
			
		}
		
	}
	
	@Nested
	@DisplayName("Update Story Test")
	class testUpdate {
		@Test //Prueba 29
		void testUpdateStory0() throws StoryServiceException {
			newStory = null;
			
			Throwable exceptionOne = assertThrows(StoryServiceException.class, ()->{storyService.updateStoryService(newStory);});
			
			assertEquals("UpdateStory: The story to update can be null", exceptionOne.getMessage());
		
		}
		
		@Test //Prueba 30
		void testUpdateStory1() throws StoryServiceException {
			newStory = new TsscStory();
						
			Throwable exceptionOne = assertThrows(StoryServiceException.class, ()->{storyService.updateStoryService(newStory);});
			
			assertEquals("UpdateStory: The story to update doesn't exist", exceptionOne.getMessage());
		
		}
		
		@Test //Prueba 31
		void testUpdateStory2() throws StoryServiceException {
			newStory = new TsscStory();
			
			StoryDao.save(newStory);
						
			Throwable exceptionOne = assertThrows(StoryServiceException.class, ()->{storyService.updateStoryService(newStory);});
			
			assertEquals("UpdateStory: The story to update need have a game", exceptionOne.getMessage());
		
		}
		
		@Test //Prueba 32
		void testUpdateStory3() throws StoryServiceException {
			newStory = new TsscStory();
			newGame = new TsscGame();

			StoryDao.save(newStory);
			
			newStory.setTsscGame(newGame);
			

			
			
			
			Throwable exceptionOne = assertThrows(StoryServiceException.class, ()->{storyService.updateStoryService(newStory);});
			
			assertEquals("UpdateStory: The game of Story to update haven't been created ", exceptionOne.getMessage());
		}
		
		@Test //Prueba 33
		void testUpdateStory4() throws StoryServiceException {
			newStory = new TsscStory();
			newGame = new TsscGame();
			
			StoryDao.save(newStory);
			
			newStory.setBusinessValue(new BigDecimal(0));
			newStory.setInitialSprint(new BigDecimal(0));
			newStory.setPriority(new BigDecimal(0));
			
			newStory.setTsscGame(newGame);
			
			GameDao.save(newGame);
			
			Throwable exceptionOne = assertThrows(StoryServiceException.class, ()->{storyService.updateStoryService(newStory);});
			
			assertEquals("UpdateStory: The business value, initial sprint or the priority are equal or less to 0", exceptionOne.getMessage());
		
		}
		
		@Test //Prueba 34
		void testUpdateStory5() throws StoryServiceException {
			newStory = new TsscStory();
			newGame = new TsscGame();
			
			StoryDao.save(newStory);
			
			newStory.setBusinessValue(new BigDecimal(1));
			newStory.setInitialSprint(new BigDecimal(1));
			newStory.setPriority(new BigDecimal(1));
			
			newStory.setTsscGame(newGame);
			GameDao.save(newGame);
			

			
			assertTrue(storyService.updateStoryService(newStory) != null,"The story haven't been updated");
		
		}
		
	}
	
	@AfterAll
	static void afterTest() {
		System.out.println("-----> DESTROY <-----");
	}

}