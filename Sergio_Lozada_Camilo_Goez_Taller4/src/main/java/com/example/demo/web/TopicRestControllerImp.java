package com.example.demo.web;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import com.example.demo.exception.TopicServiceException;
import com.example.demo.model.TsscTopic;
import com.example.demo.service.TopicService;

@RestController
public class TopicRestControllerImp implements TopicRestController {
	
	@Autowired
	private TopicService TopicService;
	
	@PostMapping("/api/Topics")
	@Override
	public TsscTopic createTopic(@RequestBody TsscTopic topic) throws TopicServiceException {
		return TopicService.createTopic(topic);
	}
	
	@PutMapping("/api/Topics")
	@Override
	public TsscTopic updateTopic(@RequestBody TsscTopic topic) throws TopicServiceException {
		return TopicService.updateTopic(topic);
	}
	
	@DeleteMapping("/api/Topics/{id}")
	@Override
	public void deleteTopic(@PathVariable("id") long id) throws TopicServiceException {
		TsscTopic topic = TopicService.getTopic(id);
		TopicService.deleteTopic(topic);
		
	}
	
	@GetMapping("/api/Topics/{id}")
	@Override
	public TsscTopic getTopic(@PathVariable("id") long id) throws TopicServiceException {
		return TopicService.getTopic(id);
	}
	
	@GetMapping("/api/Topics")
	@Override
	public Iterable<TsscTopic> findAll() {
		return TopicService.findAll();
	}
	
	
	

}
