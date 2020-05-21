package com.example.demo.controller;

import java.util.Optional;

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

import com.example.demo.exception.TopicServiceException;
import com.example.demo.model.TsscTopic;
import com.example.demo.service.TopicService;

@Controller
public class TsscTopicController {
	
	private TopicService topicService;

	@Autowired
	public TsscTopicController(TopicService topicService) {
		this.topicService = topicService;
		TsscTopic newTopic = new TsscTopic();
		newTopic.setDescription("Este es el tema nuevo");
		newTopic.setGroupPrefix("T1");
		newTopic.setDefaultGroups(1);
		newTopic.setDefaultSprints(2);
		newTopic.setName("Tema1");
		try {
			 
			topicService.createTopic(newTopic);
		} catch (TopicServiceException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	
	@GetMapping("/topic/")
	public String indexTopic(Model model) {
		model.addAttribute("topics", topicService.findAll());
		return "topic/index";
	}
	
	@GetMapping("/topic/add")
	public String addTopic(Model model) {
		model.addAttribute("tsscTopic", new TsscTopic());
		
		return "topic/add-topic-1";
	}
	
	@PostMapping("/topic/add1")
	public String addTopicStepOne(@Validated(ValidationGroupStepOne.class) @ModelAttribute TsscTopic tsscTopic,BindingResult bindingResult, @RequestParam(value = "action", required = true) String action, Model model) {
		
		if (bindingResult.hasErrors()) {
			model.addAttribute("tsscTopic", new TsscTopic());
			return "topic/add-topic-1";
		} 
	
		if (action.equals("Cancelar")) {
			
			return "redirect:/topic/";
		}
		
		return "topic/add-topic-2";
		
		
	}
	
	@PostMapping("/topic/add2")
	public String addTopicStepTwo(@Validated(ValidationGroupStepTwo.class) @ModelAttribute TsscTopic tsscTopic,BindingResult bindingResult, @RequestParam(value = "action", required = true) String action, Model model) throws TopicServiceException {
		
		if (bindingResult.hasErrors()) {
			
			return "topic/add-topic-2";
		} 
	
		if (action.equals("Cancelar")) {
			
			return "redirect:/topic/";
			
		}
		
		topicService.createTopic(tsscTopic);
			
		return "redirect:/topic/";
		
		
	}
	
	@GetMapping("/topic/edit/{id}")
	public String editTopic(@PathVariable("id") long id, Model model) throws TopicServiceException {
		TsscTopic tsscTopic = topicService.getTopic(id);
		if (tsscTopic == null)
			throw new IllegalArgumentException("Invalid topic Id:" + id);
		
		model.addAttribute("tsscTopic", tsscTopic);
		
		return "topic/edit-topic";
	}
	
	@PostMapping("/topic/edit/{id}")
	public String updateTopic(@PathVariable("id") long id,
			@RequestParam(value = "action", required = true) String action, @Validated({ValidationGroupStepOne.class,ValidationGroupStepTwo.class} ) @ModelAttribute TsscTopic tsscTopic,BindingResult bindingResult, Model model) throws TopicServiceException {
		
		if (bindingResult.hasErrors()) {

			return "topic/edit-topic";
		}
		
		if (action != null && !action.equals("Cancelar")) {
			topicService.createTopic(tsscTopic);
		}
		return "redirect:/topic/";
	}
	
	@GetMapping("/topic/del/{id}")
	public String deleteTopic(@PathVariable("id") long id) throws IllegalArgumentException, TopicServiceException {
		TsscTopic tsscTopic = topicService.getTopic(id).orElseThrow(() -> new IllegalArgumentException("Invalid topic Id:" + id));
		topicService.deleteTopic(tsscTopic);
		return "redirect:/topic/";
		
	}
	
	

	
}
