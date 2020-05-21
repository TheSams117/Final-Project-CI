package com.example.demo.web;

import com.example.demo.model.TsscAdmin;

public interface AdminRestController {
	public TsscAdmin createAdmin(TsscAdmin Admin);
	public TsscAdmin updateAdmin(TsscAdmin Admin);
	public void deleteAdmin(long id);
	public TsscAdmin getAdmin(long id);
	public Iterable<TsscAdmin> findAll();

}
