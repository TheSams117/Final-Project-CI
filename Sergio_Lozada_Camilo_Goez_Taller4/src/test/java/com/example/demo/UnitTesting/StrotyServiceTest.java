package com.example.demo.UnitTesting;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.verifyNoInteractions;
import static org.mockito.Mockito.verifyNoMoreInteractions;
import static org.mockito.Mockito.when;

import java.math.BigDecimal;

import org.junit.jupiter.api.AfterAll;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Nested;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.MockitoJUnitRunner;
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
@RunWith(MockitoJUnitRunner.class)
@SpringBootTest
class StrotyServiceTest {
	
	@Mock
	private GameDao GameDao;
	
	@Mock
	private StoryDao StoryDao;
	
	@InjectMocks
	private StoryService storyService;
	
	private TsscGame newGame;
	
	private TsscStory newStory;
	
	@Autowired
	public StrotyServiceTest(StoryService storyService) {
		this.storyService = storyService;
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
			
			verifyNoInteractions(StoryDao);
		
		}
		
		@Test //Prueba 25
		void testCreateStory1() throws StoryServiceException {
			newStory = new TsscStory();
			
			newGame = new TsscGame();
			
			when(GameDao.findById(newGame.getId())).thenReturn(newGame);

			Throwable exceptionOne = assertThrows(StoryServiceException.class, ()->{storyService.createStoryService(newStory,newGame.getId());});
			
			assertEquals("CreateStory: The new story need have a game", exceptionOne.getMessage());
			
			verifyNoInteractions(StoryDao);
		
		}
		
		@Test //Prueba 26
		void testCreateStory2() throws StoryServiceException {
			newStory = new TsscStory();
			newGame = new TsscGame();
			
			newStory.setTsscGame(newGame);
			
			Throwable exceptionOne = assertThrows(StoryServiceException.class, ()->{storyService.createStoryService(newStory,1);});
			
			assertEquals("CreateStory: The game of new Story haven't been created ", exceptionOne.getMessage());
			
			verifyNoInteractions(StoryDao);
		
		}
		
		@Test //Prueba 27
		void testCreateStory3() throws StoryServiceException {
			newStory = new TsscStory();
			newGame = new TsscGame();
			
			newStory.setTsscGame(newGame);
			
			newStory.setBusinessValue(new BigDecimal(0));
			newStory.setInitialSprint(new BigDecimal(0));
			newStory.setPriority(new BigDecimal(0));
		
			GameDao.save(newGame);
			
			when(GameDao.findById(newGame.getId())).thenReturn(newGame);
			
			Throwable exceptionOne = assertThrows(StoryServiceException.class, ()->{storyService.createStoryService(newStory,0);});
			
			assertEquals("CreateStory: The business value, initial sprint or the priority are equal or less to 0", exceptionOne.getMessage());
			
			verify(GameDao).findById(newStory.getTsscGame().getId());
			verifyNoInteractions(StoryDao);
		
		}
		
		@Test //Prueba 28
		void testCreateStory4() throws StoryServiceException {
			newStory = new TsscStory();
			newGame = new TsscGame();
			
			newStory.setTsscGame(newGame);
			
			newStory.setBusinessValue(new BigDecimal(1));
			newStory.setInitialSprint(new BigDecimal(1));
			newStory.setPriority(new BigDecimal(1));
		
			GameDao.save(newGame);
			
			when(GameDao.findById(newGame.getId())).thenReturn(newGame);
			when(StoryDao.save(newStory)).thenReturn(newStory);
			
			assertTrue(storyService.createStoryService(newStory,0) != null,"The story haven't been created");

			verify(StoryDao).save(newStory);
			
		
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
			
			verifyNoInteractions(StoryDao);
			verifyNoInteractions(GameDao);
		
		}
		
		@Test //Prueba 30
		void testUpdateStory1() throws StoryServiceException {
			newStory = new TsscStory();
			
			when(StoryDao.findById(newStory.getId())).thenReturn(null);
			
			Throwable exceptionOne = assertThrows(StoryServiceException.class, ()->{storyService.updateStoryService(newStory);});
			
			assertEquals("UpdateStory: The story to update doesn't exist", exceptionOne.getMessage());
			
			verify(StoryDao).findById(newStory.getId());
			verifyNoInteractions(GameDao);
		
		}
		
		@Test //Prueba 31
		void testUpdateStory2() throws StoryServiceException {
			newStory = new TsscStory();
			
			when(StoryDao.findById(newStory.getId())).thenReturn(newStory);
			
			Throwable exceptionOne = assertThrows(StoryServiceException.class, ()->{storyService.updateStoryService(newStory);});
			
			assertEquals("UpdateStory: The story to update need have a game", exceptionOne.getMessage());
			
			verify(StoryDao).findById(newStory.getId());
			verifyNoMoreInteractions(StoryDao);
			verifyNoInteractions(GameDao);
		
		}
		
		@Test //Prueba 32
		void testUpdateStory3() throws StoryServiceException {
			newStory = new TsscStory();
			newGame = new TsscGame();
			
			newStory.setTsscGame(newGame);
			
			when(StoryDao.findById(newStory.getId())).thenReturn(newStory);
			when(GameDao.findById(newStory.getTsscGame().getId())).thenReturn(null);
			
			Throwable exceptionOne = assertThrows(StoryServiceException.class, ()->{storyService.updateStoryService(newStory);});
			
			assertEquals("UpdateStory: The game of Story to update haven't been created ", exceptionOne.getMessage());
			
			verify(StoryDao).findById(newStory.getId());
			verify(GameDao).findById(newStory.getTsscGame().getId());
			verifyNoMoreInteractions(StoryDao);
			verifyNoMoreInteractions(GameDao);
		
		}
		
		@Test //Prueba 33
		void testUpdateStory4() throws StoryServiceException {
			newStory = new TsscStory();
			newGame = new TsscGame();
			
			newStory.setBusinessValue(new BigDecimal(0));
			newStory.setInitialSprint(new BigDecimal(0));
			newStory.setPriority(new BigDecimal(0));
			
			newStory.setTsscGame(newGame);
			
			GameDao.save(newGame);
			
			when(StoryDao.findById(newStory.getId())).thenReturn(newStory);
			
			when(GameDao.findById(newStory.getTsscGame().getId())).thenReturn(newGame);
			
			Throwable exceptionOne = assertThrows(StoryServiceException.class, ()->{storyService.updateStoryService(newStory);});
			
			assertEquals("UpdateStory: The business value, initial sprint or the priority are equal or less to 0", exceptionOne.getMessage());
			
			verify(StoryDao).findById(newStory.getId());
			verify(GameDao).findById(newStory.getTsscGame().getId());
			verifyNoMoreInteractions(StoryDao);
		
		}
		
		@Test //Prueba 34
		void testUpdateStory5() throws StoryServiceException {
			newStory = new TsscStory();
			newGame = new TsscGame();
			
			newStory.setBusinessValue(new BigDecimal(1));
			newStory.setInitialSprint(new BigDecimal(1));
			newStory.setPriority(new BigDecimal(1));
			
			newStory.setTsscGame(newGame);
			GameDao.save(newGame);
			
			when(StoryDao.findById(newStory.getId())).thenReturn(newStory);
			when(GameDao.findById(newStory.getTsscGame().getId())).thenReturn(newGame);
			when(StoryDao.update(newStory)).thenReturn(newStory);
			
			assertTrue(storyService.updateStoryService(newStory) != null,"The story haven't been updated");
			
			verify(StoryDao).findById(newStory.getId());
			verify(GameDao).findById(newStory.getTsscGame().getId());
			verify(StoryDao).update(newStory);
			verifyNoMoreInteractions(StoryDao);
			
		
		}
		
	}
	
	@AfterAll
	static void afterTest() {
		System.out.println("-----> DESTROY <-----");
	}

}
