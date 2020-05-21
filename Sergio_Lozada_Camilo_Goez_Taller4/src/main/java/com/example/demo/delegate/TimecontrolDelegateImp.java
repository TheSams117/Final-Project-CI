package com.example.demo.delegate;

import org.springframework.stereotype.Component;
import org.springframework.web.client.RestTemplate;

import com.example.demo.model.TsscTimecontrol;

@Component
public class TimecontrolDelegateImp implements TimecontrolDelegate{

	private  RestTemplate restTemplate;
	
	public TimecontrolDelegateImp() {
		// TODO Auto-generated constructor stub
		restTemplate = new RestTemplate();
	}
	
	@Override
	public TsscTimecontrol createTimecontrol(TsscTimecontrol Timecontrol) {
		// TODO Auto-generated method stub
		return restTemplate.postForObject("http://localhost:8080/Timecontrols", Timecontrol, TsscTimecontrol.class);
	}

	@Override
	public void updateTimecontrol(TsscTimecontrol Timecontrol) {
		// TODO Auto-generated method stub
		restTemplate.put("http://localhost:8080/Timecontrols", Timecontrol);
	}

	@Override
	public void deleteTimecontrol(long id) {
		// TODO Auto-generated method stub
		restTemplate.delete("http://localhost:8080/Timecontrols", id);
	}

	@Override
	public TsscTimecontrol getTimecontrol(long id) {
		// TODO Auto-generated method stub
		return restTemplate.getForObject("http://localhost:8080/Timecontrols"+id, TsscTimecontrol.class);
	}

	@SuppressWarnings("unchecked")
	@Override
	public Iterable<TsscTimecontrol> findAll() {
		// TODO Auto-generated method stub
		return restTemplate.getForObject("http://localhost:8080/Timecontrols", Iterable.class);
	}

}
