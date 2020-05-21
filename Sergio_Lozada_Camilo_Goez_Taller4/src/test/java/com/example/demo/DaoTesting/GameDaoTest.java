package com.example.demo.DaoTesting;

import static org.junit.Assert.assertNotNull;
import static org.junit.jupiter.api.Assertions.*;

import java.time.LocalDate;
import java.time.LocalTime;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.annotation.Rollback;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import com.example.demo.dao.GameDao;
import com.example.demo.dao.TopicDao;
import com.example.demo.model.TsscGame;
import com.example.demo.model.TsscTopic;

@SpringBootTest
@Rollback(false)
class GameDaoTest {
	
	@Autowired
	private GameDao GameDao;
	
	@Autowired
	private TopicDao TopicDao;
	
	private TsscGame GameTest;
	
	private TsscTopic TopicTest;
	
	public void setUpOne() {
		GameTest = new TsscGame();
	}
	
	public void setUpTwo() {
		GameTest = new TsscGame();
		TopicTest = new TsscTopic();
	}

	@Test
	@Transactional(readOnly=false, propagation = Propagation.REQUIRED, rollbackFor = Exception.class)
	public void saveGameTest() {

		assertNotNull(GameDao);
		
		this.setUpOne(); 
		
		GameDao.save(GameTest);
		
		assertNotNull(GameDao.findById(GameTest.getId()));
	}
	
	@Test
	@Transactional(readOnly=false, propagation = Propagation.REQUIRED, rollbackFor = Exception.class)
	public void updateGameTest() {

		assertNotNull(GameDao);
		
		this.setUpOne(); 
		GameDao.save(GameTest);
		
		GameTest.setName("Game One");
		
		GameDao.update(GameTest);

		assertEquals(GameDao.findById(GameTest.getId()).getName(),"Game One");
		
		

	}
	
	@Test
	@Transactional(readOnly=false, propagation = Propagation.REQUIRED, rollbackFor = Exception.class)
	public void deleteGameTest() {
		
		assertNotNull(GameDao);
		
		this.setUpOne();
		
		GameDao.delete(GameTest);
		
		assertNull(GameDao.findById(GameTest.getId()));
		

	}
	
	@Test
	@Transactional(readOnly=false, propagation = Propagation.REQUIRED, rollbackFor = Exception.class)
	public void findByIdGameTest() {
		
		assertNotNull(GameDao);
		
		this.setUpOne();
		
		GameDao.save(GameTest);
		
		assertNotNull(GameDao.findById(GameTest.getId()));
		
		
		
		
	}
	
	@Test
	@Transactional(readOnly=false, propagation = Propagation.REQUIRED, rollbackFor = Exception.class)
	public void findAllGameTest() {
		
		assertNotNull(GameDao);
		
		this.setUpOne();
		
		GameDao.save(GameTest);
		
		assertNotNull(GameDao.findAll().get(0));
		
		
	}
	
	@Test
	@Transactional(readOnly=false, propagation = Propagation.REQUIRED, rollbackFor = Exception.class)
	public void findByTopicNameGameTest() {
		
		assertNotNull(GameDao);
		
		this.setUpTwo();
		
		TopicTest.setName("Topic One");
		
		GameTest.setName("Game One");
		
		GameTest.setTsscTopic(TopicTest);
		
		TopicDao.save(TopicTest);
		
		GameDao.save(GameTest);
		
		assertEquals(GameDao.findByTopicName("Topic One").get(0).getName(),"Game One");
	
	}
	
	@Test
	@Transactional(readOnly=false, propagation = Propagation.REQUIRED, rollbackFor = Exception.class)
	public void findByTopicDescriptionGameTest() {
		
		assertNotNull(GameDao);
		
		this.setUpTwo();
		
		TopicTest.setDescription("This is my topic one");
		
		GameTest.setName("Game One");
		
		GameTest.setTsscTopic(TopicTest);
		
		TopicDao.save(TopicTest);
		
		GameDao.save(GameTest);
		
		assertEquals(GameDao.findByTopicDescription("This is my topic one").get(0).getName(),"Game One");
		
	}
	
	@Test
	@Transactional(readOnly=false, propagation = Propagation.REQUIRED, rollbackFor = Exception.class)
	public void findByTopicIdGameTest() {
		
		assertNotNull(GameDao);
		
		this.setUpTwo();
		
		GameTest.setName("Game One");
		
		GameTest.setTsscTopic(TopicTest);
		
		TopicDao.save(TopicTest);
		
		GameDao.save(GameTest);
		
		assertEquals(GameDao.findByTopicId(TopicTest.getId()).get(0).getName(),"Game One");
	}
		
	@Test
	@Transactional(readOnly=false, propagation = Propagation.REQUIRED, rollbackFor = Exception.class)
	public void findByDateTimeTest() {
		
		assertNotNull(GameDao);
		
		this.setUpOne();
		
		GameTest.setName("Game One");
		
		GameTest.setScheduledDate(LocalDate.of(2020, 6, 6));
		GameTest.setScheduledTime(LocalTime.of(6, 0));
		
		GameDao.save(GameTest);
		
		assertEquals(GameDao.findByDateTime(LocalDate.of(2020, 6, 6), LocalTime.of(1, 0),LocalTime.of(23, 0)).get(0).getName(),"Game One");
		
		
	}
	
	@Test
	@Transactional(readOnly=false, propagation = Propagation.REQUIRED, rollbackFor = Exception.class)
	public void findByDateGameTest() {
		
		assertNotNull(GameDao);
		
		this.setUpOne();
		
		GameTest.setName("Game One");
		
		GameTest.setScheduledDate(LocalDate.of(2020, 6, 6));
		
		GameDao.save(GameTest);
		
		assertEquals(GameDao.findByDate(LocalDate.of(2020, 1, 1), LocalDate.of(2020, 12, 12)).get(0).getName(),"Game One");
		
		
	}
	
	
	@Test
	@Transactional(readOnly=false, propagation = Propagation.REQUIRED, rollbackFor = Exception.class)
	public void query2ATopicTest() {
		assertNotNull(GameDao);
		
		TsscTopic topicOne = new TsscTopic();
		TsscTopic topicTwo = new TsscTopic();
		TsscTopic topicThree = new TsscTopic();
		
		TsscGame gameOne = new TsscGame();
		TsscGame gameTwo= new TsscGame();
		TsscGame gameThree = new TsscGame();
		TsscGame gameFour = new TsscGame();
		
		gameOne.setScheduledDate(LocalDate.of(2020, 1, 1));
		gameTwo.setScheduledDate(LocalDate.of(2020, 1, 1));
		gameThree.setScheduledDate(LocalDate.of(2020, 1, 1));
		gameFour.setScheduledDate(LocalDate.of(2020, 1, 1));
		gameOne.setScheduledTime(LocalTime.of(1, 0));
		gameTwo.setScheduledTime(LocalTime.of(5, 0));
		gameThree.setScheduledTime(LocalTime.of(3, 0));
		gameFour.setScheduledTime(LocalTime.of(4, 0));
		
		
		
		topicOne.setName("Topic One");
		topicTwo.setName("Topic Two");
		topicThree.setName("Topic Three");
		
		gameOne.setTsscTopic(topicOne);
		gameTwo.setTsscTopic(topicOne);
		gameThree.setTsscTopic(topicTwo);
		gameFour.setTsscTopic(topicThree);

		TopicDao.save(topicOne);
		TopicDao.save(topicTwo);
		TopicDao.save(topicThree);
		
		GameDao.save(gameOne);
		GameDao.save(gameTwo);
		GameDao.save(gameThree);
		GameDao.save(gameFour);
		
		assertEquals( GameDao.Query2A(LocalDate.of(2020, 1, 1)).get(0).getName(), "Topic One");
		
	}
	
	@Test
	@Transactional(readOnly=false, propagation = Propagation.REQUIRED, rollbackFor = Exception.class)
	public void query2BTopicTest() {
		assertNotNull(TopicDao);
		
		this.setUpOne();
		
		GameTest.setName("Game One");
		
		GameDao.save(GameTest);
		
		GameTest.setScheduledDate(LocalDate.of(2020, 6, 6));
		
		assertEquals(GameDao.Query2B(LocalDate.of(2020, 6, 6)).get(0).getName(),"Game One");		
		
	}
}
