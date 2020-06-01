package com.example.demo.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import com.example.demo.dao.AdminDao;
import com.example.demo.model.TsscAdmin;

@Service
public class AdminServiceImp implements AdminService{
	
	@Autowired
	private AdminDao AdminDao;

	@Override
	@Transactional(readOnly=false, propagation = Propagation.REQUIRED, rollbackFor = Exception.class)
	public TsscAdmin createAdmin(TsscAdmin Admin) {
		return AdminDao.save(Admin);
	}
	
	@Override
	@Transactional(readOnly=false, propagation = Propagation.REQUIRED, rollbackFor = Exception.class)
	public TsscAdmin updateAdmin(TsscAdmin Admin) {
		return AdminDao.update(Admin);
	}
	
	@Override
	@Transactional(readOnly=false, propagation = Propagation.REQUIRED, rollbackFor = Exception.class)
	public void deleteAdmin(TsscAdmin Admin) {
		AdminDao.delete(Admin);
	}
	
	@Override
	@Transactional(readOnly=false, propagation = Propagation.REQUIRED, rollbackFor = Exception.class)
	public TsscAdmin getAdmin(long id) {
		return AdminDao.findById(id);
	}
	
	@Override
	@Transactional(readOnly=false, propagation = Propagation.REQUIRED, rollbackFor = Exception.class)
	public Iterable<TsscAdmin> findAll(){
		return AdminDao.findAll();
	}

}
