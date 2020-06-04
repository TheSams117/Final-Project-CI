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

import com.example.demo.delegate.TimecontrolDelegate;
import com.example.demo.model.TsscTimecontrol;

@ExtendWith(SpringExtension.class)
@RunWith(MockitoJUnitRunner.class)
@SpringBootTest
class TimecontrolDelegateTest {
	
	@Mock 
	private RestTemplate restTemplate;
	
	@InjectMocks
	private TimecontrolDelegate TimecontrolDelegate;
	
	private TsscTimecontrol TimecontrolTest;
	
	@Autowired
	public TimecontrolDelegateTest(TimecontrolDelegate TimecontrolDelegate) {
		this.TimecontrolDelegate = TimecontrolDelegate;
	}
	
	public void setUpOne() {
		TimecontrolTest = new TsscTimecontrol();
	}

	@Test
	void testCreateTimecontrol() {
		assertNotNull(TimecontrolDelegate);
		setUpOne();
		
		when(restTemplate.postForObject("http://localhost:8080/api/Timecontrols/0", TimecontrolTest, TsscTimecontrol.class)).thenReturn(TimecontrolTest);
		
		assertTrue(TimecontrolDelegate.createTimecontrol(TimecontrolTest,0)!=null);
	}
	
	@Test
	void testUpdateTimecontrol() {
		assertNotNull(TimecontrolDelegate);
		setUpOne();
		
		when(restTemplate.postForObject("http://localhost:8080/api/Timecontrols/0", TimecontrolTest, TsscTimecontrol.class)).thenReturn(TimecontrolTest);
		
		assertTrue(TimecontrolDelegate.createTimecontrol(TimecontrolTest,0)!=null);
		
		TimecontrolTest.setName("Time 1");
		
		TimecontrolDelegate.updateTimecontrol(TimecontrolTest);
	}
	
	@Test
	void testDeleteTimecontrol() {
		assertNotNull(TimecontrolDelegate);
		setUpOne();
		
		when(restTemplate.postForObject("http://localhost:8080/api/Timecontrols/0", TimecontrolTest, TsscTimecontrol.class)).thenReturn(TimecontrolTest);
		
		assertTrue(TimecontrolDelegate.createTimecontrol(TimecontrolTest,0)!=null);
		
		TimecontrolDelegate.deleteTimecontrol(TimecontrolTest.getId());
	}
	
	@Test
	void testGetTimecontrol() {
		assertNotNull(TimecontrolDelegate);
		setUpOne();
		
		when(restTemplate.postForObject("http://localhost:8080/api/Timecontrols/0", TimecontrolTest, TsscTimecontrol.class)).thenReturn(TimecontrolTest);
		
		assertTrue(TimecontrolDelegate.createTimecontrol(TimecontrolTest,0)!=null);
		
		when(restTemplate.getForObject("http://localhost:8080/api/Timecontrols/"+TimecontrolTest.getId(), TsscTimecontrol.class)).thenReturn(TimecontrolTest);
		
		assertTrue(TimecontrolDelegate.getTimecontrol(TimecontrolTest.getId())!=null);
	}
	
	@Test
	void testFindAllTimecontrol() {
		assertNotNull(TimecontrolDelegate);
		setUpOne();
		
		ArrayList<TsscTimecontrol> list  = new ArrayList<TsscTimecontrol>();
		
		list.add(TimecontrolTest);
		
		when(restTemplate.postForObject("http://localhost:8080/api/Timecontrols/0", TimecontrolTest, TsscTimecontrol.class)).thenReturn(TimecontrolTest);
		
		assertTrue(TimecontrolDelegate.createTimecontrol(TimecontrolTest,0)!=null);
		
		when(restTemplate.getForObject("http://localhost:8080/api/Timecontrols", Iterable.class)).thenReturn(list);
		
		assertTrue(((ArrayList<TsscTimecontrol>)TimecontrolDelegate.findAll()).get(0)!=null);
	}

}