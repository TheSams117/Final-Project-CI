package com.example.demo.delegate;

import com.example.demo.model.TsscTimecontrol;

public interface TimecontrolDelegate {

	public TsscTimecontrol createTimecontrol(TsscTimecontrol Timecontrol);
	public void updateTimecontrol(TsscTimecontrol Timecontrol);
	public void deleteTimecontrol(long id);
	public TsscTimecontrol getTimecontrol(long id);
	public Iterable<TsscTimecontrol> findAll();
}
