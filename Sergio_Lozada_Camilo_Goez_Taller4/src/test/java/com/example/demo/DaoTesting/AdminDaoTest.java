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

import com.example.demo.dao.AdminDao;
import com.example.demo.model.TsscAdmin;

@SpringBootTest
@Rollback(false)
class AdminDaoTest {
	
	@Autowired
	private AdminDao adminDao;
	
	private TsscAdmin adminTest;
	
	public void setUpOne() {
		adminTest = new TsscAdmin();
	}

	@Test
	@Transactional(readOnly=false, propagation = Propagation.REQUIRED, rollbackFor = Exception.class)
	public void saveAdminTest() {

		assertNotNull(adminDao);
		
		this.setUpOne(); 
		
		adminDao.save(adminTest);
		
		assertNotNull(adminDao.findById(adminTest.getId()));
	}
	
	@Test
	@Transactional(readOnly=false, propagation = Propagation.REQUIRED, rollbackFor = Exception.class)
	public void updateAdminTest() {

		assertNotNull(adminDao);
		
		this.setUpOne(); 
		adminTest.setUser("UserOne");
		
		adminDao.update(adminTest);

		assertEquals(adminDao.findById(adminTest.getId()).getUser(),"UserOne");
		
		

	}
	
	@Test
	@Transactional(readOnly=false, propagation = Propagation.REQUIRED, rollbackFor = Exception.class)
	public void deleteAdminTest() {
		
		assertNotNull(adminDao);
		
		this.setUpOne();
		
		adminDao.delete(adminTest);
		
		assertNull(adminDao.findById(adminTest.getId()));
		

	}
	
	@Test
	@Transactional(readOnly=false, propagation = Propagation.REQUIRED, rollbackFor = Exception.class)
	public void findByIdAdminTest() {
		
		assertNotNull(adminDao);
		
		this.setUpOne();
		
		adminDao.save(adminTest);
		
		assertNotNull(adminDao.findById(adminTest.getId()));
		
		
		
		
	}
	
	@Test
	@Transactional(readOnly=false, propagation = Propagation.REQUIRED, rollbackFor = Exception.class)
	public void findAllAdminTest() {
		
		assertNotNull(adminDao);
		
		this.setUpOne();
		
		adminDao.save(adminTest);
		
		assertNotNull(adminDao.findAll().get(0));
		
		
	}

}
