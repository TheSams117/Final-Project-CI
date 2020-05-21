package com.example.demo.service;

import com.example.demo.model.TsscSprint;

public interface SprintService {
	public TsscSprint createSprint(TsscSprint sprint);
	public TsscSprint updateSprint(TsscSprint sprint);
	public TsscSprint deleteSprint(long id);
	public TsscSprint getSprint(long id);
}
