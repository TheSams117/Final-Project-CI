package com.example.demo.DaoTesting;

import static org.junit.Assert.assertNotNull;
import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.annotation.Rollback;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import com.example.demo.dao.TopicDao;
import com.example.demo.model.TsscTopic;

@SpringBootTest
@Rollback(false)
class TopicDaoTest {

	@Autowired
	private TopicDao TopicDao;
	
	private TsscTopic TopicTest;
	
	public void setUpOne() {
		TopicTest = new TsscTopic();
	}
	
	public void setUpTwo() {
		TopicTest = new TsscTopic();
	}

	@Test
	@Transactional(readOnly=false, propagation = Propagation.REQUIRED, rollbackFor = Exception.class)
	public void saveTopicTest() {

		assertNotNull(TopicDao);
		
		this.setUpOne();
		
		TopicDao.save(TopicTest);
		
		assertNotNull(TopicDao.findById(TopicTest.getId()));
	}
	
	@Test
	@Transactional(readOnly=false, propagation = Propagation.REQUIRED, rollbackFor = Exception.class)
	public void updateTopicTest() {

		assertNotNull(TopicDao);
		
		this.setUpOne(); 
		TopicDao.save(TopicTest);
		
		TopicTest.setName("Topic one");
		TopicDao.update(TopicTest);

		assertEquals(TopicDao.findById(TopicTest.getId()).getName(),"Topic one");
		
		

	}
	
	@Test
	@Transactional(readOnly=false, propagation = Propagation.REQUIRED, rollbackFor = Exception.class)
	public void deleteTopicTest() {
		
		assertNotNull(TopicDao);
		
		this.setUpOne();
		
		TopicDao.delete(TopicTest);
		
		assertNull(TopicDao.findById(TopicTest.getId()));
		

	}
	
	@Test
	@Transactional(readOnly=false, propagation = Propagation.REQUIRED, rollbackFor = Exception.class)
	public void findByIdTopicTest() {
		
		assertNotNull(TopicDao);
		
		this.setUpOne();
		
		TopicDao.save(TopicTest);
		
		assertNotNull(TopicDao.findById(TopicTest.getId()));
		
		
		
		
	}
	
	@Test
	@Transactional(readOnly=false, propagation = Propagation.REQUIRED, rollbackFor = Exception.class)
	public void findAllTopicTest() {
		
		assertNotNull(TopicDao);
		
		this.setUpOne();
		
		TopicDao.save(TopicTest);
		
		assertNotNull(TopicDao.findAll().get(0));
		
		
	}
	
	@Test
	@Transactional(readOnly=false, propagation = Propagation.REQUIRED, rollbackFor = Exception.class)
	public void finbByNameTopicTest() {
		
		assertNotNull(TopicDao);
		
		this.setUpOne();
		
		TopicTest.setName("Topic One");
		
		TopicDao.save(TopicTest);
				
		assertNotNull(TopicDao.findByName("Topic One"));
		
		assertEquals(TopicDao.findByName("Topic One").getName(), "Topic One");
		
	}
	
	@Test
	@Transactional(readOnly=false, propagation = Propagation.REQUIRED, rollbackFor = Exception.class)
	public void finbByDescriptionTopicTest() {
		assertNotNull(TopicDao);
		
		this.setUpOne();
		
		TopicTest.setDescription("This is the new topic");;
		
		TopicDao.save(TopicTest);
		
		assertNotNull(TopicDao.findByDescription("This is the new topic"));
		
		assertEquals(TopicDao.findByDescription("This is the new topic").getDescription(), "This is the new topic");
	}
	
}
