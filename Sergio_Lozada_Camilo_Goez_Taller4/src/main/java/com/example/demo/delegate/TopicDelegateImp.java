package com.example.demo.delegate;

import org.springframework.stereotype.Component;
import org.springframework.web.client.RestTemplate;

import com.example.demo.model.TsscGame;
import com.example.demo.model.TsscTopic;

@Component
public class TopicDelegateImp implements TopicDelegate{

	private  RestTemplate restTemplate;
	
	public TopicDelegateImp() {
		// TODO Auto-generated constructor stub
		restTemplate = new RestTemplate();
	}
	
	@Override
	public TsscTopic createTopic(TsscTopic Topic) {
		// TODO Auto-generated method stub
		return restTemplate.postForObject("http://localhost:8080/Topics", Topic, TsscTopic.class);
	}

	@Override
	public void updateTopic(TsscTopic Topic) {
		// TODO Auto-generated method stub
		restTemplate.put("http://localhost:8080/Topics", Topic);
	}

	@Override
	public void deleteTopic(long id) {
		// TODO Auto-generated method stub
		restTemplate.delete("http://localhost:8080/Topics", id);
	}

	@Override
	public TsscTopic getTopic(long id) {
		// TODO Auto-generated method stub
		return restTemplate.getForObject("http://localhost:8080/Topics"+id, TsscTopic.class);
	}

	@SuppressWarnings("unchecked")
	@Override
	public Iterable<TsscTopic> findAll() {
		// TODO Auto-generated method stub
		return restTemplate.getForObject("http://localhost:8080/Topics", Iterable.class);
	}

}
