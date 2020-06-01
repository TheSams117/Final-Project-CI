package com.example.demo.delegate;

import org.springframework.stereotype.Component;
import org.springframework.web.client.RestTemplate;

import com.example.demo.model.TsscTimecontrol;

@Component
public class TimecontrolDelegateImp implements TimecontrolDelegate{

	private  RestTemplate restTemplate;
	
	public TimecontrolDelegateImp() {
		restTemplate = new RestTemplate();
	}
	
	@Override
	public TsscTimecontrol createTimecontrol(TsscTimecontrol Timecontrol) {
		return restTemplate.postForObject("http://localhost:8080/api/Timecontrols", Timecontrol, TsscTimecontrol.class);
	}

	@Override
	public void updateTimecontrol(TsscTimecontrol Timecontrol) {
		restTemplate.put("http://localhost:8080/api/Timecontrols", Timecontrol);
	}

	@Override
	public void deleteTimecontrol(long id) {
		restTemplate.delete("http://localhost:8080/api/Timecontrols/"+id, id);
	}

	@Override
	public TsscTimecontrol getTimecontrol(long id) {
		return restTemplate.getForObject("http://localhost:8080/api/Timecontrols/"+id, TsscTimecontrol.class);
	}

	@SuppressWarnings("unchecked")
	@Override
	public Iterable<TsscTimecontrol> findAll() {
		return restTemplate.getForObject("http://localhost:8080/api/Timecontrols", Iterable.class);
	}

}
