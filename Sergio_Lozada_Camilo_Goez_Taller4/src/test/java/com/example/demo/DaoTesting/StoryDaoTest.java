package com.example.demo.DaoTesting;

import static org.junit.Assert.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNull;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.annotation.Rollback;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import com.example.demo.dao.StoryDao;
import com.example.demo.model.TsscStory;

@SpringBootTest
@Rollback(false)
class StoryDaoTest {
	
	@Autowired
	private StoryDao storyDao;
	
	private TsscStory storyTest;
	
	public void setUpOne() {
		storyTest = new TsscStory();
	}

	@Test
	@Transactional(readOnly=false, propagation = Propagation.REQUIRED, rollbackFor = Exception.class)
	public void saveStoryTest() {

		assertNotNull(storyDao);
		
		this.setUpOne(); 
		
		storyDao.save(storyTest);
		
		assertNotNull(storyDao.findById(storyTest.getId()));
	}
	
	@Test
	@Transactional(readOnly=false, propagation = Propagation.REQUIRED, rollbackFor = Exception.class)
	public void updateStoryTest() {

		assertNotNull(storyDao);
		
		this.setUpOne(); 
		
		storyDao.save(storyTest);
		
		storyTest.setDescription("New Story");
		
		storyDao.update(storyTest);

		assertEquals(storyDao.findById(storyTest.getId()).getDescription(),"New Story");
		
		

	}
	
	@Test
	@Transactional(readOnly=false, propagation = Propagation.REQUIRED, rollbackFor = Exception.class)
	public void deleteStoryTest() {
		
		assertNotNull(storyDao);
		
		this.setUpOne();
		
		storyDao.delete(storyTest);
		
		assertNull(storyDao.findById(storyTest.getId()));
		

	}
	
	@Test
	@Transactional(readOnly=false, propagation = Propagation.REQUIRED, rollbackFor = Exception.class)
	public void findByIdStoryTest() {
		
		assertNotNull(storyDao);
		
		this.setUpOne();
		
		storyDao.save(storyTest);
		
		assertNotNull(storyDao.findById(storyTest.getId()));
		
		
		
		
	}
	
	@Test
	@Transactional(readOnly=false, propagation = Propagation.REQUIRED, rollbackFor = Exception.class)
	public void findAllStoryTest() {
		
		assertNotNull(storyDao);
		
		this.setUpOne();
		
		storyDao.save(storyTest);
		
		assertNotNull(storyDao.findAll().get(0));
		
		
	}


}
