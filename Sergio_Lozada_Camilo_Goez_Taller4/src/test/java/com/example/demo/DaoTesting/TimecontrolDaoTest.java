package com.example.demo.DaoTesting;
import static org.junit.Assert.assertNotNull;
import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.annotation.Rollback;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import com.example.demo.dao.TimecontrolDao;
import com.example.demo.model.TsscTimecontrol;

@SpringBootTest
@Rollback(false)
class TimecontrolDaoTest {
	
	@Autowired
	private TimecontrolDao TimecontrolDao;
	
	private TsscTimecontrol TimecontrolTest;
	
	public void setUpOne() {
		TimecontrolTest = new TsscTimecontrol();
	}

	@Test
	@Transactional(readOnly=false, propagation = Propagation.REQUIRED, rollbackFor = Exception.class)
	public void saveTimecontrolTest() {

		assertNotNull(TimecontrolDao);
		
		this.setUpOne(); 
		
		TimecontrolDao.save(TimecontrolTest);
		
		assertNotNull(TimecontrolDao.findById(TimecontrolTest.getId()));
	}
	
	@Test
	@Transactional(readOnly=false, propagation = Propagation.REQUIRED, rollbackFor = Exception.class)
	public void updateTimecontrolTest() {

		assertNotNull(TimecontrolDao);
		
		this.setUpOne(); 
		
		TimecontrolDao.save(TimecontrolTest);
		
		TimecontrolTest.setName("Timeconrol one");
		
		TimecontrolDao.update(TimecontrolTest);

		assertEquals(TimecontrolDao.findById(TimecontrolTest.getId()).getName(),"Timeconrol one");
		
		

	}
	
	@Test
	@Transactional(readOnly=false, propagation = Propagation.REQUIRED, rollbackFor = Exception.class)
	public void deleteTimecontrolTest() {
		
		assertNotNull(TimecontrolDao);
		
		this.setUpOne();
		
		TimecontrolDao.delete(TimecontrolTest);
		
		assertNull(TimecontrolDao.findById(TimecontrolTest.getId()));
		

	}
	
	@Test
	@Transactional(readOnly=false, propagation = Propagation.REQUIRED, rollbackFor = Exception.class)
	public void findByIdTimecontrolTest() {
		
		assertNotNull(TimecontrolDao);
		
		this.setUpOne();
		
		TimecontrolDao.save(TimecontrolTest);
		
		assertNotNull(TimecontrolDao.findById(TimecontrolTest.getId()));
		
		
		
		
	}
	
	@Test
	@Transactional(readOnly=false, propagation = Propagation.REQUIRED, rollbackFor = Exception.class)
	public void findAllTimecontrolTest() {
		
		assertNotNull(TimecontrolDao);
		
		this.setUpOne();
		
		TimecontrolDao.save(TimecontrolTest);
		
		assertNotNull(TimecontrolDao.findAll().get(0));
		
		
	}


}
