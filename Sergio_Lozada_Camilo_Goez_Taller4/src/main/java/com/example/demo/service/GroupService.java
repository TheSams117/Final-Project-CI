package com.example.demo.service;

import com.example.demo.model.TsscGroup;

public interface GroupService {
	public TsscGroup createGroup(TsscGroup group);
	public TsscGroup updateGroup(TsscGroup group);
	public TsscGroup deleteGroup(long id);
	public TsscGroup getGroup(long id);
}
