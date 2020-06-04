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

import com.example.demo.delegate.AdminDelegate;
import com.example.demo.model.TsscAdmin;

@ExtendWith(SpringExtension.class)
@RunWith(MockitoJUnitRunner.class)
@SpringBootTest
class AdminDelegateTest {
	
	@Mock 
	private RestTemplate restTemplate;
	
	@InjectMocks
	private AdminDelegate AdminDelegate;
	
	private TsscAdmin AdminTest;
	
	@Autowired
	public AdminDelegateTest(AdminDelegate AdminDelegate) {
		this.AdminDelegate = AdminDelegate;
	}
	
	public void setUpOne() {
		AdminTest = new TsscAdmin();
	}

	@Test
	void testCreateAdmin() {
		assertNotNull(AdminDelegate);
		setUpOne();
		
		when(restTemplate.postForObject("http://localhost:8080/api/Admins", AdminTest, TsscAdmin.class)).thenReturn(AdminTest);
		
		assertTrue(AdminDelegate.createAdmin(AdminTest)!=null);
	}
	
	@Test
	void testUpdateAdmin() {
		assertNotNull(AdminDelegate);
		setUpOne();
		
		when(restTemplate.postForObject("http://localhost:8080/api/Admins", AdminTest, TsscAdmin.class)).thenReturn(AdminTest);
		
		assertTrue(AdminDelegate.createAdmin(AdminTest)!=null);
		
		AdminTest.setPassword("123");
		
		AdminDelegate.updateAdmin(AdminTest);
	}
	
	@Test
	void testDeleteAdmin() {
		assertNotNull(AdminDelegate);
		setUpOne();
		
		when(restTemplate.postForObject("http://localhost:8080/api/Admins", AdminTest, TsscAdmin.class)).thenReturn(AdminTest);
		
		assertTrue(AdminDelegate.createAdmin(AdminTest)!=null);
		
		AdminDelegate.deleteAdmin(AdminTest.getId());
	}
	
	@Test
	void testGetAdmin() {
		assertNotNull(AdminDelegate);
		setUpOne();
		
		when(restTemplate.postForObject("http://localhost:8080/api/Admins", AdminTest, TsscAdmin.class)).thenReturn(AdminTest);
		
		assertTrue(AdminDelegate.createAdmin(AdminTest)!=null);
		
		when(restTemplate.getForObject("http://localhost:8080/api/Admins/"+AdminTest.getId(), TsscAdmin.class)).thenReturn(AdminTest);
		
		assertTrue(AdminDelegate.getAdmin(AdminTest.getId())!=null);
	}
	
	@Test
	void testFindAllAdmin() {
		assertNotNull(AdminDelegate);
		setUpOne();
		
		ArrayList<TsscAdmin> list  = new ArrayList<TsscAdmin>();
		
		list.add(AdminTest);
		
		when(restTemplate.postForObject("http://localhost:8080/api/Admins", AdminTest, TsscAdmin.class)).thenReturn(AdminTest);
		
		assertTrue(AdminDelegate.createAdmin(AdminTest)!=null);
		
		when(restTemplate.getForObject("http://localhost:8080/api/Admins", Iterable.class)).thenReturn(list);
		
		assertTrue(((ArrayList<TsscAdmin>)AdminDelegate.findAll()).get(0)!=null);
	}

}
