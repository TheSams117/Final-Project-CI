package com.example.demo.delegate;

import org.springframework.stereotype.Component;
import org.springframework.web.client.RestTemplate;

import com.example.demo.model.TsscStory;

@Component
public class StoryDelegateImp implements StoryDelegate{

	private  RestTemplate restTemplate;
	
	public StoryDelegateImp() {
		restTemplate = new RestTemplate();
	}
	
	@Override
	public TsscStory createStory(TsscStory Story, long gameId) {
		return restTemplate.postForObject("http://localhost:8080/api/Storys/"+gameId, Story, TsscStory.class);
	}

	@Override
	public void updateStory(TsscStory Story) {
		restTemplate.put("http://localhost:8080/api/Storys", Story);
	}

	@Override
	public void deleteStory(long id) {
		restTemplate.delete("http://localhost:8080/api/Storys/"+id, id);
	}

	@Override
	public TsscStory getStory(long id) {
		return restTemplate.getForObject("http://localhost:8080/api/Storys/"+id, TsscStory.class);
	}

	@SuppressWarnings("unchecked")
	@Override
	public Iterable<TsscStory> findAll() {
		return restTemplate.getForObject("http://localhost:8080/api/Storys", Iterable.class);
	}
	
	

}
