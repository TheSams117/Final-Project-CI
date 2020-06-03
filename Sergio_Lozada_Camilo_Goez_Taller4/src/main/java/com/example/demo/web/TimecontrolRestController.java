package com.example.demo.web;

import com.example.demo.model.TsscTimecontrol;

public interface TimecontrolRestController {
	public TsscTimecontrol createTimecontrol(long id,TsscTimecontrol timecontrol );
	public TsscTimecontrol updateTimecontrol(TsscTimecontrol timecontrol );
	public void deleteTimecontrol(long id);
	public TsscTimecontrol getTimecontrol(long id);
	public Iterable<TsscTimecontrol> findAll();

}
