package com.example.demo.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.example.demo.dao.TimecontrolDao;
import com.example.demo.model.TsscTimecontrol;

@Service
public class TimecontrolServiceImp implements TimecontrolService {
	@Autowired
	private TimecontrolDao timecontrolDao;


	public TsscTimecontrol createTimecontrol(TsscTimecontrol timecontrol) {
		return timecontrolDao.save(timecontrol);
	}

	public TsscTimecontrol updateTimecontrol(TsscTimecontrol timecontrol) {
		return timecontrolDao.update(timecontrol);
	}

	public void deleteTimecontrol(TsscTimecontrol timecontrol) {
		timecontrolDao.delete(timecontrol);
	}

	public TsscTimecontrol getTimecontrol(long id) {
		return timecontrolDao.findById(id);
	}
	
	public Iterable<TsscTimecontrol> findAll(){
		return timecontrolDao.findAll();
	}

}
