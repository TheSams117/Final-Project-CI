package com.example.demo.delegate;

import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Component;
import org.springframework.web.client.RestTemplate;

import com.example.demo.model.TsscTopic;

@Component
@Scope("singleton")
public class TopicDelegateImp implements TopicDelegate{

	private  RestTemplate restTemplate;
	
	public TopicDelegateImp() {
		restTemplate = new RestTemplate();
	}
	
	@Override
	public TsscTopic createTopic(TsscTopic Topic) {
		return restTemplate.postForObject("http://localhost:8080/api/Topics", Topic, TsscTopic.class);
	}

	@Override
	public void updateTopic(TsscTopic Topic) {
		restTemplate.put("http://localhost:8080/api/Topics", Topic);
	}

	@Override
	public void deleteTopic(long id) {
		restTemplate.delete("http://localhost:8080/api/Topics/"+id, id);
	}

	@Override
	public TsscTopic getTopic(long id) {
		return restTemplate.getForObject("http://localhost:8080/api/Topics/"+id, TsscTopic.class);
	}

	@SuppressWarnings("unchecked")
	@Override
	public Iterable<TsscTopic> findAll() {
		return restTemplate.getForObject("http://localhost:8080/api/Topics", Iterable.class);
	}

}
