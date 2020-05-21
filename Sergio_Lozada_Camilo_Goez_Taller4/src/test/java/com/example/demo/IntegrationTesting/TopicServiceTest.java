package com.example.demo.IntegrationTesting;

import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.AfterAll;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Nested;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit.jupiter.SpringExtension;

import com.example.demo.exception.TopicServiceException;
import com.example.demo.model.TsscTopic;
import com.example.demo.service.TopicService;

@ExtendWith(SpringExtension.class)
@SpringBootTest
class TopicServiceTest {
	
	private TopicService topicService;
	
	private TsscTopic newTopic;

	@Autowired
	public TopicServiceTest(TopicService topicService) {
		this.topicService = topicService;
	}
	
	
	@BeforeAll
	static void setUp() {
		System.out.println("-----> SETUP <-----");
		
	}
	
	@Nested
	@DisplayName("Create Topic Test")
	class testCreate {
		
		@Test //Prueba 1
		void testCreateTopic0() throws TopicServiceException {
			newTopic = null;
			
			Throwable exception = assertThrows(TopicServiceException.class, ()->{topicService.createTopic(newTopic);});
			
			assertEquals("CreateTopic: The new topic can be null", exception.getMessage());
		}
		
		@Test //Prueba 2
		void testCreateTopic1() throws TopicServiceException {
			newTopic = new TsscTopic();
			newTopic.setDefaultGroups(1);
			newTopic.setDefaultSprints(1);

			assertTrue(topicService.createTopic(newTopic)!=null,"The topic haven't been created");
		}
		
		@Test //Prueba 3
		void testCreateTopic2() throws TopicServiceException {
			newTopic = new TsscTopic();
			newTopic.setDefaultGroups(0);
			newTopic.setDefaultSprints(1);
					
			Throwable exception = assertThrows(TopicServiceException.class, ()->{topicService.createTopic(newTopic);});
			
			assertEquals("CreateTopic: Number of groups equal or less to 0", exception.getMessage());
			
		}
		
		@Test //Prueba 4
		void testCreateTopic3() throws TopicServiceException {
			newTopic = new TsscTopic();
			newTopic.setDefaultGroups(1);
			newTopic.setDefaultSprints(0);
						
			Throwable exception = assertThrows(TopicServiceException.class, ()->{topicService.createTopic(newTopic);});
			
			assertEquals("CreateTopic: Number of sptrints equal or less to 0", exception.getMessage());
			
		}
	}
	
	@Nested
	@DisplayName("Update Topic Test")
	class testUpdate {
		@Test //Prueba 5
		void testUpdateTopic0() throws TopicServiceException {
			newTopic = null;
			
			Throwable exception = assertThrows(TopicServiceException.class, ()->{topicService.updateTopic(newTopic);});
			
			assertEquals("UpdateTopic: The topic to update can be null", exception.getMessage());
			
		}
		
		@Test //Prueba 6
		void testUpdateTopic1() throws TopicServiceException {
			newTopic = new TsscTopic();
			newTopic.setDefaultGroups(1);
			newTopic.setDefaultSprints(1);
						
			Throwable exception = assertThrows(TopicServiceException.class, ()->{topicService.updateTopic(newTopic);});
			
			assertEquals("UpdateTopic: The topic to update dosen't exist.", exception.getMessage());
		}
		
		@Test //Prueba 7
		void testUpdateTopic2() throws TopicServiceException {
			newTopic = new TsscTopic();
			newTopic.setDefaultGroups(1);
			newTopic.setDefaultSprints(1);
			
			topicService.createTopic(newTopic);
			assertTrue(topicService.updateTopic(newTopic)!=null,"The topic haven't been updated");
			
		}
		
		@Test//Prueba 8
		void testUpdateTopic3() throws TopicServiceException {
			newTopic = new TsscTopic();
			newTopic.setDefaultGroups(0);
			newTopic.setDefaultSprints(1);
			
			TsscTopic newTopic1 = new TsscTopic();
			newTopic1.setDefaultGroups(1);
			newTopic1.setDefaultSprints(1);
			
			topicService.createTopic(newTopic1);
			
			Throwable exception = assertThrows(TopicServiceException.class, ()->{topicService.updateTopic(newTopic);});
			
			assertEquals("UpdateTopic: Number of groups equal or less to 0", exception.getMessage());
			
		}
		
		@Test //Prueba 9
		void testUpdateTopic4() throws TopicServiceException {
			newTopic = new TsscTopic();
			newTopic.setDefaultGroups(1);
			newTopic.setDefaultSprints(0);
			
			TsscTopic newTopic1 = new TsscTopic();
			newTopic1.setDefaultGroups(1);
			newTopic1.setDefaultSprints(1);
			
			topicService.createTopic(newTopic1);
						
			Throwable exception = assertThrows(TopicServiceException.class, ()->{topicService.updateTopic(newTopic);});
			
			assertEquals("UpdateTopic: Number of sptrints equal or less to 0", exception.getMessage());
			
		}
	}
	
	@AfterAll
	static void afterTest() {
		System.out.println("-----> DESTROY <-----");
	}
}
