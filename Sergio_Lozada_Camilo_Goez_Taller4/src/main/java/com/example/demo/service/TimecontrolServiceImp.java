package com.example.demo.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import com.example.demo.dao.GameDao;
import com.example.demo.dao.TimecontrolDao;
import com.example.demo.model.TsscGame;
import com.example.demo.model.TsscTimecontrol;

@Service
public class TimecontrolServiceImp implements TimecontrolService {
	@Autowired
	private TimecontrolDao timecontrolDao;
	
	@Autowired
	private GameDao gameDao;

	public TsscTimecontrol createTimecontrol(TsscTimecontrol timecontrol, long id) {
		TsscGame game  = gameDao.findById(id);
		timecontrol.setTsscGame(game);
		//game.addTsscTimecontrol(timecontrol);
		gameDao.update(game);
		return timecontrolDao.save(timecontrol);
	}
	
	public TsscTimecontrol updateTimecontrol(TsscTimecontrol timecontrol) {
		timecontrol.setTsscGame(timecontrolDao.findById(timecontrol.getId()).getTsscGame());
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
