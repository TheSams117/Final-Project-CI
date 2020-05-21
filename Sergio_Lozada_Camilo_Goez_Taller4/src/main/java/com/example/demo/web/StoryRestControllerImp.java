package com.example.demo.web;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import com.example.demo.exception.StoryServiceException;
import com.example.demo.model.TsscStory;
import com.example.demo.service.StoryService;

@RestController
public class StoryRestControllerImp implements StoryRestController {
	
	@Autowired
	private StoryService StoryService;
	
	@PostMapping("/Storys/{id}")
	@Override
	public TsscStory createStory(@RequestBody TsscStory Story,@PathVariable("id") long id) throws StoryServiceException {
		return StoryService.createStoryService(Story, id);
	}
	
	@PutMapping("/Storys")
	@Override
	public TsscStory updateStory(@RequestBody TsscStory Story) throws StoryServiceException {
		return StoryService.updateStoryService(Story);
	}
	
	@DeleteMapping("/Storys/{id}")
	@Override
	public void deleteStory(@PathVariable("id") long id) throws StoryServiceException {
		TsscStory Story = StoryService.getStoryService(id);
		StoryService.deleteStoryService(Story);
		
	}
	
	@GetMapping("/Storys/{id}")
	@Override
	public TsscStory getStory(@PathVariable("id") long id) throws StoryServiceException {
		return StoryService.getStoryService(id);
	}
	
	@GetMapping("/Storys")
	@Override
	public Iterable<TsscStory> findAll() {
		return StoryService.findAll();
	}

}
