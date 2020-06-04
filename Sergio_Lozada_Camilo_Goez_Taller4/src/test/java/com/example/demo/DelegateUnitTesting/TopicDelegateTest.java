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

import com.example.demo.delegate.TopicDelegate;
import com.example.demo.model.TsscTopic;

@ExtendWith(SpringExtension.class)
@RunWith(MockitoJUnitRunner.class)
@SpringBootTest
class TopicDelegateTest {
	
	@Mock 
	private RestTemplate restTemplate;
	
	@InjectMocks
	private TopicDelegate TopicDelegate;
	
	private TsscTopic TopicTest;
	
	@Autowired
	public TopicDelegateTest(TopicDelegate TopicDelegate) {
		this.TopicDelegate = TopicDelegate;
	}
	
	public void setUpOne() {
		TopicTest = new TsscTopic();
	}

	@Test
	void testCreateTopic() {
		assertNotNull(TopicDelegate);
		setUpOne();
		
		when(restTemplate.postForObject("http://localhost:8080/api/Topics", TopicTest, TsscTopic.class)).thenReturn(TopicTest);
		
		assertTrue(TopicDelegate.createTopic(TopicTest)!=null);
	}
	
	@Test
	void testUpdateTopic() {
		assertNotNull(TopicDelegate);
		setUpOne();
		
		when(restTemplate.postForObject("http://localhost:8080/api/Topics", TopicTest, TsscTopic.class)).thenReturn(TopicTest);
		
		assertTrue(TopicDelegate.createTopic(TopicTest)!=null);
		
		TopicTest.setName("Topic 1");
		
		TopicDelegate.updateTopic(TopicTest);
	}
	
	@Test
	void testDeleteTopic() {
		assertNotNull(TopicDelegate);
		setUpOne();
		
		when(restTemplate.postForObject("http://localhost:8080/api/Topics", TopicTest, TsscTopic.class)).thenReturn(TopicTest);
		
		assertTrue(TopicDelegate.createTopic(TopicTest)!=null);
		
		TopicDelegate.deleteTopic(TopicTest.getId());
	}
	
	@Test
	void testGetTopic() {
		assertNotNull(TopicDelegate);
		setUpOne();
		
		when(restTemplate.postForObject("http://localhost:8080/api/Topics", TopicTest, TsscTopic.class)).thenReturn(TopicTest);
		
		assertTrue(TopicDelegate.createTopic(TopicTest)!=null);
		
		when(restTemplate.getForObject("http://localhost:8080/api/Topics/"+TopicTest.getId(), TsscTopic.class)).thenReturn(TopicTest);
		
		assertTrue(TopicDelegate.getTopic(TopicTest.getId())!=null);
	}
	
	@Test
	void testFindAllTopic() {
		assertNotNull(TopicDelegate);
		setUpOne();
		
		ArrayList<TsscTopic> list  = new ArrayList<TsscTopic>();
		
		list.add(TopicTest);
		
		when(restTemplate.postForObject("http://localhost:8080/api/Topics", TopicTest, TsscTopic.class)).thenReturn(TopicTest);
		
		assertTrue(TopicDelegate.createTopic(TopicTest)!=null);
		
		when(restTemplate.getForObject("http://localhost:8080/api/Topics", Iterable.class)).thenReturn(list);
		
		assertTrue(((ArrayList<TsscTopic>)TopicDelegate.findAll()).get(0)!=null);
	}

}
