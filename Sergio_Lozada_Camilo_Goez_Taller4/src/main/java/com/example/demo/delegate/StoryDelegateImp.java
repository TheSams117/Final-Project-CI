package com.example.demo.delegate;

import org.springframework.stereotype.Component;
import org.springframework.web.client.RestTemplate;

import com.example.demo.model.TsscStory;

@Component
public class StoryDelegateImp implements StoryDelegate{

	private  RestTemplate restTemplate;
	
	public StoryDelegateImp() {
		// TODO Auto-generated constructor stub
		restTemplate = new RestTemplate();
	}
	
	@Override
	public TsscStory createStory(TsscStory Story) {
		// TODO Auto-generated method stub
		return restTemplate.postForObject("http://localhost:8080/Storys", Story, TsscStory.class);
	}

	@Override
	public void updateStory(TsscStory Story) {
		// TODO Auto-generated method stub
		restTemplate.put("http://localhost:8080/Storys", Story);
	}

	@Override
	public void deleteStory(long id) {
		// TODO Auto-generated method stub
		restTemplate.delete("http://localhost:8080/Storys", id);
	}

	@Override
	public TsscStory getStory(long id) {
		// TODO Auto-generated method stub
		return restTemplate.getForObject("http://localhost:8080/Storys"+id, TsscStory.class);
	}

	@SuppressWarnings("unchecked")
	@Override
	public Iterable<TsscStory> findAll() {
		// TODO Auto-generated method stub
		return restTemplate.getForObject("http://localhost:8080/Storys", Iterable.class);
	}
	
	

}
