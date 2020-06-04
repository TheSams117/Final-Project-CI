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

import com.example.demo.delegate.StoryDelegate;
import com.example.demo.model.TsscStory;

@ExtendWith(SpringExtension.class)
@RunWith(MockitoJUnitRunner.class)
@SpringBootTest
class StoryDelegateTest {
	
	@Mock 
	private RestTemplate restTemplate;
	
	@InjectMocks
	private StoryDelegate StoryDelegate;
	
	private TsscStory StoryTest;
	
	@Autowired
	public StoryDelegateTest(StoryDelegate StoryDelegate) {
		this.StoryDelegate = StoryDelegate;
	}
	
	public void setUpOne() {
		StoryTest = new TsscStory();
	}

	@Test
	void testCreateStory() {
		assertNotNull(StoryDelegate);
		setUpOne();
		
		when(restTemplate.postForObject("http://localhost:8080/api/Storys/0", StoryTest, TsscStory.class)).thenReturn(StoryTest);
		
		assertTrue(StoryDelegate.createStory(StoryTest,0)!=null);
	}
	
	@Test
	void testUpdateStory() {
		assertNotNull(StoryDelegate);
		setUpOne();
		
		when(restTemplate.postForObject("http://localhost:8080/api/Storys/0", StoryTest, TsscStory.class)).thenReturn(StoryTest);
		
		assertTrue(StoryDelegate.createStory(StoryTest,0)!=null);
		
		StoryTest.setDescription("New Story");
		
		StoryDelegate.updateStory(StoryTest);
	}
	
	@Test
	void testDeleteStory() {
		assertNotNull(StoryDelegate);
		setUpOne();
		
		when(restTemplate.postForObject("http://localhost:8080/api/Storys/0", StoryTest, TsscStory.class)).thenReturn(StoryTest);
		
		assertTrue(StoryDelegate.createStory(StoryTest,0)!=null);
		
		StoryDelegate.deleteStory(StoryTest.getId());
	}
	
	@Test
	void testGetStory() {
		assertNotNull(StoryDelegate);
		setUpOne();
		
		when(restTemplate.postForObject("http://localhost:8080/api/Storys/0", StoryTest, TsscStory.class)).thenReturn(StoryTest);
		
		assertTrue(StoryDelegate.createStory(StoryTest,0)!=null);
		
		when(restTemplate.getForObject("http://localhost:8080/api/Storys/"+StoryTest.getId(), TsscStory.class)).thenReturn(StoryTest);
		
		assertTrue(StoryDelegate.getStory(StoryTest.getId())!=null);
	}
	
	@Test
	void testFindAllStory() {
		assertNotNull(StoryDelegate);
		setUpOne();
		
		ArrayList<TsscStory> list  = new ArrayList<TsscStory>();
		
		list.add(StoryTest);
		
		when(restTemplate.postForObject("http://localhost:8080/api/Storys/0", StoryTest, TsscStory.class)).thenReturn(StoryTest);
		
		assertTrue(StoryDelegate.createStory(StoryTest,0)!=null);
		
		when(restTemplate.getForObject("http://localhost:8080/api/Storys", Iterable.class)).thenReturn(list);
		
		assertTrue(((ArrayList<TsscStory>)StoryDelegate.findAll()).get(0)!=null);
	}

}
