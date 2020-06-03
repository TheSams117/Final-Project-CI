package com.example.demo.service;

import com.example.demo.model.TsscTimecontrol;

public interface TimecontrolService {
	public TsscTimecontrol createTimecontrol(TsscTimecontrol timecontrol,long id );
	public TsscTimecontrol updateTimecontrol(TsscTimecontrol timecontrol );
	public void deleteTimecontrol(TsscTimecontrol timecontrol);
	public TsscTimecontrol getTimecontrol(long id);
	public Iterable<TsscTimecontrol> findAll();

}
