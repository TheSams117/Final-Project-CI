package com.example.demo.controller;

import java.util.ArrayList;
import java.util.List;

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

import com.example.demo.controller.ValidationGame;
import com.example.demo.exception.GameServiceException;
import com.example.demo.exception.StoryServiceException;
import com.example.demo.exception.TopicServiceException;
import com.example.demo.model.TsscGame;
import com.example.demo.model.TsscStory;
import com.example.demo.delegate.GameDelegate;
import com.example.demo.delegate.StoryDelegate;

@Controller
public class TsscStoryController {
	
	private StoryDelegate storyDelegate;
	private GameDelegate gameDelegate;
	
	private long idGame;
	private boolean isFromGame;
	
	@Autowired
	public TsscStoryController(StoryDelegate storyDelegate,GameDelegate gameDelegate) {
		this.storyDelegate = storyDelegate;
		this.gameDelegate = gameDelegate;
	}
	
	@GetMapping("/story/")
	public String indexStory(Model model) {
		model.addAttribute("tsscStories",storyDelegate.findAll());
		isFromGame = false;
		return "story/index2";
	}
	
	@GetMapping("/story/{id}")
	public String indexStoryGame(@PathVariable("id") long idGame, Model model) {
		this.idGame = idGame;
		model.addAttribute("tsscStories",gameDelegate.getGame(idGame).getTsscStories());
		isFromGame = true;
		return "story/index1";
	}
	
	@GetMapping("/story/add")
	public String addStory(Model model) {
		
		if(!isFromGame) {
			model.addAttribute("games",gameDelegate.findAll());
		}
		
		model.addAttribute("tsscStory",new TsscStory());
		
		return (isFromGame)? "story/add-story-1-1" : "story/add-story-1-2";
	}
	
	@PostMapping("/story/add1-1")
	public String addStoryStepOneOne(@Validated(ValidationGroupStepOne.class) @ModelAttribute TsscStory tsscStory,BindingResult bindingResult, @RequestParam(value = "action", required = true) String action, Model model) throws GameServiceException {
		
		if (action.equals("Cancelar")) {
			
			return "redirect:/story/"+idGame ;
		}
		
		if (bindingResult.hasErrors()) {
			
			return "story/add-story-1-1";
		} 
		
		model.addAttribute("idGame",idGame);
		
		
		return "story/add-story-2";
		
		
	}
	
	@PostMapping("/story/add1-2")
	public String addStoryStepOneTwo(@Validated({ValidationGroupStepOne.class,ValidationGame.class}) @ModelAttribute TsscStory tsscStory,BindingResult bindingResult, @RequestParam(value = "action", required = true) String action, Model model) throws GameServiceException {
		
		if (action.equals("Cancelar")) {
			
			return "redirect:/story/" ;
		}
		
		if (bindingResult.hasErrors()) {
			model.addAttribute("games",gameDelegate.findAll());
			return "story/add-story-1-2";
		} 
	
		idGame = tsscStory.getTsscGame().getId();	
		model.addAttribute("idGame",idGame);
		
		
		return "story/add-story-2";
		
		
	}
	
	@PostMapping("/story/add2")
	public String addStoryStepTwo(@Validated(ValidationGroupStepTwo.class) @ModelAttribute TsscStory tsscStory,BindingResult bindingResult, @RequestParam(value = "action", required = true) String action, Model model) throws GameServiceException, StoryServiceException{
		
		
		if (action.equals("Cancelar")) {
			
			return (isFromGame)? "redirect:/game/story/"+idGame : "redirect:/story/";
			
		}

		if (bindingResult.hasErrors()) {
			
			return "story/add-story-2";
		} 
		
	
		

		storyDelegate.createStory(tsscStory,idGame);
		
			
		return (isFromGame)? "redirect:/game/story/"+idGame : "redirect:/story/" ;
		
		
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
		
		
		if (action.equals("Cancelar")) {
			
			return (isFromGame)? "redirect:/game/story/"+idGame : "redirect:/story/";
			
		}
		
		if (bindingResult.hasErrors()) {

			return "story/edit-story";
		}
		
		if (action != null && !action.equals("Cancelar")) {
	
			storyDelegate.updateStory(tsscStory);
		}
		return (isFromGame)? "redirect:/game/story/"+idGame:"redirect:/story/";
	}
	
	@GetMapping("/story/del/{id}")
	public String deleteStory(@PathVariable("id") long id) throws IllegalArgumentException, GameServiceException, StoryServiceException {
		TsscStory tsscStory= storyDelegate.getStory(id);
		storyDelegate.deleteStory(tsscStory.getId());
		
		return (isFromGame)? "redirect:/game/story/"+idGame:"redirect:/story/";
		
	}
}
