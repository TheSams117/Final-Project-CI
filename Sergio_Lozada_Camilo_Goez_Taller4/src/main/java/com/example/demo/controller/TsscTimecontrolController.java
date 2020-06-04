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

import com.example.demo.delegate.GameDelegate;
import com.example.demo.delegate.TimecontrolDelegate;
import com.example.demo.model.TsscTimecontrol;

@Controller
public class TsscTimecontrolController {
	private TimecontrolDelegate timecontrolDelegate;
	private GameDelegate gameDelegate;
	
	private long idGame;
	
	
	@Autowired
	public TsscTimecontrolController(TimecontrolDelegate timecontrolDelegate,GameDelegate gameDelegate) {
		this.timecontrolDelegate = timecontrolDelegate;
		this.gameDelegate = gameDelegate;
	}
	
	@GetMapping("/timecontrol/{id}")
	public String indexTimecontrol(@PathVariable("id") long idGame, Model model) {
		this.idGame = idGame;

		model.addAttribute("tsscTimecontrols",gameDelegate.getGame(idGame).getTsscTimecontrols());
		return "timecontrol/index";
	}
	
	@GetMapping("/timecontrol/add")
	public String addTimecontrol(Model model) {
		model.addAttribute("tsscTimecontrol", new TsscTimecontrol());
		return "timecontrol/add-timecontrol";
	}
	
	@PostMapping("/timecontrol/add")
	public String addTimecontrolOne(@Validated(ValidationGroupStepOne.class) @ModelAttribute TsscTimecontrol tsscTimecontrol,BindingResult bindingResult, @RequestParam(value = "action", required = true) String action, Model model) {
		
		if (action.equals("Cancelar")) {
			
			return "redirect:/game/timecontrol/"+idGame;
			
		}
		
		if (bindingResult.hasErrors()) {
			
			return "timecontrol/add-timecontrol";
		} 
		
		timecontrolDelegate.createTimecontrol(tsscTimecontrol,idGame);
		
		return "redirect:/game/timecontrol/"+idGame;
	}
	
	@GetMapping("/timecontrol/edit/{id}")
	public String updateTimecontrol(@PathVariable("id") long id, Model model) {
		TsscTimecontrol tsscTimecontrol = timecontrolDelegate.getTimecontrol(id);
		if (tsscTimecontrol == null)
			throw new IllegalArgumentException("Invalid timecontrol Id:" + id);
		
		model.addAttribute("tsscTimecontrol", tsscTimecontrol);
		return "timecontrol/edit-timecontrol";
	}
	
	@PostMapping("timecontrol/edit/{id}")
	public String updateTimecontrolOne(@PathVariable("id") long id,
			@RequestParam(value = "action", required = true) String action, @Validated(ValidationGroupStepOne.class) @ModelAttribute TsscTimecontrol tsscTimecontrol,BindingResult bindingResult, Model model) {
		
		if (action.equals("Cancelar")) {
			
			return "redirect:/game/timecontrol/"+idGame;
			
		}
		
		if (bindingResult.hasErrors()) {
			
			return "timecontrol/edit-timecontrol";
		}
		
		if (action != null) {
			timecontrolDelegate.updateTimecontrol(tsscTimecontrol);
		}
		
		
		return "redirect:/game/timecontrol/"+idGame;
	}
	
	@GetMapping("/timecontrol/del/{id}")
	public String deleteTimecontrol(@PathVariable("id") long id) {
		timecontrolDelegate.deleteTimecontrol(id);
		return "redirect:/timecontrol/"+idGame;
	}

}
