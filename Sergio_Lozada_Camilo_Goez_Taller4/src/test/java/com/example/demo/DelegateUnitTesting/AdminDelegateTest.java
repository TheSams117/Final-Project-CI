package com.example.demo.DelegateUnitTesting;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.when;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.MockitoJUnitRunner;
import org.omg.CORBA.PUBLIC_MEMBER;
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
		
		when(restTemplate.postForObject("http://localhost:8080/Admins", AdminTest, TsscAdmin.class)).thenReturn(AdminTest);
		
		assertTrue(AdminDelegate.createAdmin(AdminTest)!=null);
	}

}
