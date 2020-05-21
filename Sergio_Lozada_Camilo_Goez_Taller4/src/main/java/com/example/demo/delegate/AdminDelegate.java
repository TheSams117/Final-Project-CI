package com.example.demo.delegate;

import com.example.demo.model.TsscAdmin;

public interface AdminDelegate {
	public TsscAdmin createAdmin(TsscAdmin Admin);
	public void updateAdmin(TsscAdmin Admin);
	public void deleteAdmin(long id);
	public TsscAdmin getAdmin(long id);
	public Iterable<TsscAdmin> findAll();
}
