package com.example.demo.controller;

import java.time.LocalDate;
import java.time.LocalTime;

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
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import com.example.demo.exception.GameServiceException;
import com.example.demo.exception.TopicServiceException;
import com.example.demo.model.TsscGame;
import com.example.demo.model.TsscTopic;
import com.example.demo.delegate.GameDelegate;
import com.example.demo.delegate.TopicDelegate;

@Controller
public class TsscGameController {
	
	private GameDelegate gameDelegate;
	
	private TopicDelegate topicDelegate;
	
	@Autowired
	public TsscGameController(GameDelegate gameDelegate,TopicDelegate topicDelegate) throws GameServiceException {
		this.topicDelegate = topicDelegate;
		this.gameDelegate = gameDelegate;
		TsscGame n = new TsscGame();
		n.setScheduledDate(LocalDate.of(2222, 3, 1));
		n.setScheduledTime(LocalTime.MAX);
		n.setStartTime(LocalTime.MAX);
		n.setPauseSeconds((long)3);
		n.setAdminPassword("123456789");
		n.setGuestPassword("123456789");
		n.setUserPassword("123456789");
		n.setName("Juego 1");
		
		//gameDelegate.createGame(n);
		
	}
	
	@GetMapping("/game/")
	public String indexGame(Model model) {
		model.addAttribute("games", gameDelegate.findAll());
		return "game/index";
	}
	
	@GetMapping("/game/add")
	public String addGame(Model model) {
		model.addAttribute("tsscGame", new TsscGame());
		
		return "game/add-game-1";
	}
	
	@PostMapping("/game/add1")
	public String addGameStepOne(@Validated(ValidationGroupStepOne.class) @ModelAttribute TsscGame tsscGame,BindingResult bindingResult, @RequestParam(value = "action", required = true) String action, Model model) throws GameServiceException {
		
		if (bindingResult.hasErrors()) {
			
			return "game/add-game-1";
		} 
	
		if (action.equals("Cancelar")) {
			
			return "redirect:/game/";
		}	
		model.addAttribute("topics", topicDelegate.findAll());
		return "game/add-game-2";
		
		
	}
	
	@PostMapping("/game/add2")
	public String addGameStepTwo(@Validated(ValidationGroupStepTwo.class) @ModelAttribute TsscGame tsscGame,BindingResult bindingResult, @RequestParam(value = "action", required = true) String action, Model model) throws GameServiceException{
		
		if (bindingResult.hasErrors()) {
			model.addAttribute("topics", topicDelegate.findAll());
			return "game/add-game-2";
		} 
		
	
		if (action.equals("Cancelar")) {
			
			return "redirect:/game/";
			
		}
		
		gameDelegate.createGame(tsscGame);
		
			
		return "redirect:/game/";
		
		
	}
	
	@GetMapping("/game/edit/{id}")
	public String editGame(@PathVariable("id") long id, Model model) throws GameServiceException {
		TsscGame tsscGame = gameDelegate.getGame(id);
		
		if (tsscGame == null)
			
			throw new IllegalArgumentException("Invalid game Id:" + id);
		
		model.addAttribute("tsscGame", tsscGame);
		
		model.addAttribute("topics", topicDelegate.findAll());
		
		return "game/edit-game";
	}
	
	@PostMapping("/game/edit/{id}")
	public String updateGame(@PathVariable("id") long id,
			@RequestParam(value = "action", required = true) String action, @Validated({ValidationGroupStepOne.class,ValidationGroupStepTwo.class} ) @ModelAttribute TsscGame tsscGame,BindingResult bindingResult, Model model) throws TopicServiceException, GameServiceException {
		
		if (bindingResult.hasErrors()) {
			
			model.addAttribute("topics", topicDelegate.findAll());
			
			return "game/edit-game";
		}
		
		if (action != null && !action.equals("Cancelar")) {
			gameDelegate.updateGame(tsscGame);
		}
		return "redirect:/game/";
	}
	
	@GetMapping("/game/story/{id}")
	public String indexStory(@PathVariable("id") long id,Model model) throws GameServiceException {
		TsscGame tsscGame = gameDelegate.getGame(id);
		if (tsscGame == null)
			throw new IllegalArgumentException("Invalid game Id:" + id);
		model.addAttribute("idGame",id);
		model.addAttribute("tsscStories", tsscGame.getTsscStories());

		return "/story/index";
	}
	
	@GetMapping("/game/topic/{id}")
	public String editTopicFromGame(@PathVariable("id") long id, Model model,RedirectAttributes redirectAttrs) throws GameServiceException {
		TsscGame tsscGame = gameDelegate.getGame(id);
		if (tsscGame == null)
			throw new IllegalArgumentException("Invalid game Id:" + id);
		if(tsscGame.getTsscTopic() == null) {
			 redirectAttrs
	            .addFlashAttribute("mensaje", "No es posible editar el tema del juego. Primero asocie un tema al juego desde la edición del mismo")
	            
	            .addFlashAttribute("clase", "alert");
			 
			 return "redirect:/game/";
		}
		model.addAttribute("tsscTopic", tsscGame.getTsscTopic());
		
		return "game/edit-topic-from-game";
	}
	
	@PostMapping("/game/topic/{id}")
	public String updateTopicFromGame(@PathVariable("id") long id,
			@RequestParam(value = "action", required = true) String action, @Validated({ValidationGroupStepOne.class,ValidationGroupStepTwo.class} ) @ModelAttribute TsscTopic tsscTopic,BindingResult bindingResult, Model model) throws TopicServiceException, GameServiceException {
		
		if (bindingResult.hasErrors()) {
			
			
			return "game/edit-topic-from-game";
		}
		
		if (action != null && !action.equals("Cancelar")) {
			TsscGame tsscGame = gameDelegate.getGame(id);
			tsscGame.setTsscTopic(tsscTopic);
			gameDelegate.updateGame(tsscTopic);
			

		}
		return "redirect:/game/";
	
	}
		
	@GetMapping("/game/del/{id}")
	public String deleteGame(@PathVariable("id") long id) throws IllegalArgumentException, GameServiceException {
		TsscGame tsscGame = gameDelegate.getGame(id);
		gameDelegate.deleteGame(tsscGame.getId());
		return "redirect:/game/";
		
	}
}
