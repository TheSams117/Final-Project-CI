package com.example.demo.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.example.demo.dao.AdminDao;
import com.example.demo.model.TsscAdmin;

@Service
public class AdminServiceImp implements AdminService{
	
	@Autowired
	private AdminDao AdminDao;

	@Override
	public TsscAdmin createAdmin(TsscAdmin Admin) {
		return AdminDao.save(Admin);
	}
	
	@Override
	public TsscAdmin updateAdmin(TsscAdmin Admin) {
		return AdminDao.update(Admin);
	}
	
	@Override
	public void deleteAdmin(TsscAdmin Admin) {
		AdminDao.delete(Admin);
	}
	
	@Override
	public TsscAdmin getAdmin(long id) {
		return AdminDao.findById(id);
	}
	
	@Override
	public Iterable<TsscAdmin> findAll(){
		return AdminDao.findAll();
	}

}
