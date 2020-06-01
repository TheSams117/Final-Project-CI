package com.example.demo.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

import com.example.demo.exception.GameServiceException;
import com.example.demo.exception.StoryServiceException;
import com.example.demo.exception.TopicServiceException;
import com.example.demo.model.TsscStory;
import com.example.demo.delegate.StoryDelegate;

@Controller
public class TsscStoryController {
	
	private StoryDelegate storyDelegate;
	
	private long idGame;
	
	@Autowired
	public TsscStoryController(StoryDelegate storyDelegate) {
		this.storyDelegate = storyDelegate;
	}
	
	@GetMapping("/story/")
	public String indexStory(Model model) {
	
		return "story/index/";
	}
	
	@GetMapping("/story/add/{id}")
	public String addStory(@PathVariable("id") long idGame,Model model) {
		this.idGame = idGame;
		model.addAttribute("tsscStory",new TsscStory());
		return "story/add-story-1";
	}
	
	@PostMapping("/story/add1")
	public String addStoryStepOne(@Validated(ValidationGroupStepOne.class) @ModelAttribute TsscStory tsscStory,BindingResult bindingResult, @RequestParam(value = "action", required = true) String action, Model model) throws GameServiceException {
		
		if (bindingResult.hasErrors()) {
			
			return "story/add-story-1";
		} 
	
		if (action.equals("Cancelar")) {
			
			return "redirect:/story/";
		}	
		
		model.addAttribute("idGame",idGame);
		
		return "story/add-story-2";
		
		
	}
	
	@PostMapping("/story/add2")
	public String addStoryStepTwo(@Validated(ValidationGroupStepTwo.class) @ModelAttribute TsscStory tsscStory,BindingResult bindingResult, @RequestParam(value = "action", required = true) String action, Model model) throws GameServiceException, StoryServiceException{
		
		if (bindingResult.hasErrors()) {
			
			return "story/add-story-2";
		} 
		
	
		if (action.equals("Cancelar")) {
			
			return "redirect:/game/";
			
		}
		
		storyDelegate.createStory(tsscStory,idGame);
		
			
		return "redirect:/game/story/"+idGame;
		
		
	}
	
	@GetMapping("/story/edit/{id}")
	public String editStory(@PathVariable("id") long id, Model model) throws TopicServiceException, StoryServiceException {
		TsscStory tsscStory = storyDelegate.getStory(id);
		if (tsscStory == null)
			throw new IllegalArgumentException("Invalid topic Id:" + id);
		
		model.addAttribute("tsscStory", tsscStory);
		
		return "story/edit-story";
	}
	
	@PostMapping("/story/edit/{id}")
	public String updateStory(@PathVariable("id") long id,
			@RequestParam(value = "action", required = true) String action, @Validated({ValidationGroupStepOne.class,ValidationGroupStepTwo.class} ) @ModelAttribute TsscStory tsscStory,BindingResult bindingResult, Model model) throws TopicServiceException, StoryServiceException {
		
		if (bindingResult.hasErrors()) {

			return "story/edit-story";
		}
		
		if (action != null && !action.equals("Cancelar")) {
			storyDelegate.createStory(tsscStory,idGame);
		}
		return "redirect:/game/story/"+idGame;
	}
	
	@GetMapping("/story/del/{id}")
	public String deleteStory(@PathVariable("id") long id) throws IllegalArgumentException, GameServiceException, StoryServiceException {
		TsscStory tsscStory= storyDelegate.getStory(id);
		storyDelegate.deleteStory(tsscStory.getId());
		return "redirect:/game/story/"+idGame;
		
	}
}
