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

	@Transactional(readOnly=false, propagation = Propagation.REQUIRED, rollbackFor = Exception.class)
	public TsscTimecontrol createTimecontrol(TsscTimecontrol timecontrol, long id) {
		TsscGame game  = gameDao.findById(id);
		game.addTsscTimecontrol(timecontrol);
		gameDao.update(game);
		return timecontrolDao.save(timecontrol);
	}
	
	@Transactional(readOnly=false, propagation = Propagation.REQUIRED, rollbackFor = Exception.class)
	public TsscTimecontrol updateTimecontrol(TsscTimecontrol timecontrol) {
		timecontrol.setTsscGame(timecontrolDao.findById(timecontrol.getId()).getTsscGame());
		return timecontrolDao.update(timecontrol);
	}
	
	@Transactional(readOnly=false, propagation = Propagation.REQUIRED, rollbackFor = Exception.class)
	public void deleteTimecontrol(TsscTimecontrol timecontrol) {
		timecontrolDao.delete(timecontrol);
	}

	@Transactional(readOnly=false, propagation = Propagation.REQUIRED, rollbackFor = Exception.class)
	public TsscTimecontrol getTimecontrol(long id) {
		return timecontrolDao.findById(id);
	}
	
	@Transactional(readOnly=false, propagation = Propagation.REQUIRED, rollbackFor = Exception.class)
	public Iterable<TsscTimecontrol> findAll(){
		return timecontrolDao.findAll();
	}

}
