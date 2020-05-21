package com.example.demo.service;

import com.example.demo.model.TsscAdmin;

public interface AdminService {
	public TsscAdmin createAdmin(TsscAdmin Admin);
	public TsscAdmin updateAdmin(TsscAdmin Admin);
	public void deleteAdmin(TsscAdmin Admin);
	public TsscAdmin getAdmin(long id);
	public Iterable<TsscAdmin> findAll();

}
