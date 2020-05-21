package com.example.demo.web;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import com.example.demo.model.TsscTimecontrol;
import com.example.demo.service.TimecontrolService;

@RestController
public class TimecontrolRestControllerImp implements TimecontrolRestController {

	@Autowired
	private TimecontrolService TimecontrolService;
	
	@PostMapping("/Timecontrols")
	@Override
	public TsscTimecontrol createTimecontrol(@RequestBody TsscTimecontrol Timecontrol){
		return TimecontrolService.createTimecontrol(Timecontrol);
	}
	
	@PutMapping("/Timecontrols")
	@Override
	public TsscTimecontrol updateTimecontrol(@RequestBody TsscTimecontrol Timecontrol){
		return TimecontrolService.updateTimecontrol(Timecontrol);
	}
	
	@DeleteMapping("/Timecontrols/{id}")
	@Override
	public void deleteTimecontrol(@PathVariable("id") long id){
		TsscTimecontrol Timecontrol = TimecontrolService.getTimecontrol(id);
		TimecontrolService.deleteTimecontrol(Timecontrol);	
	}
	
	@GetMapping("/Timecontrols/{id}")
	@Override
	public TsscTimecontrol getTimecontrol(@PathVariable("id") long id){
		return TimecontrolService.getTimecontrol(id);
	}
	
	@GetMapping("/Timecontrols")
	@Override
	public Iterable<TsscTimecontrol> findAll() {
		return TimecontrolService.findAll();
	}

}
