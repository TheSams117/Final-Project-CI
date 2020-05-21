package com.example.demo.delegate;

import org.springframework.stereotype.Component;
import org.springframework.web.client.RestTemplate;

import com.example.demo.model.TsscAdmin;

@Component
public class AdminDelegateImp implements AdminDelegate {
	
	private  RestTemplate restTemplate;
	
	public AdminDelegateImp() {
		restTemplate = new RestTemplate();
	}
	
	@Override
	public TsscAdmin createAdmin(TsscAdmin Admin) {
		return restTemplate.postForObject("http://localhost:8080/Admins", Admin, TsscAdmin.class);
	}

	@Override
	public void updateAdmin(TsscAdmin Admin) {
		restTemplate.put("http://localhost:8080/Admins", Admin);
	}

	@Override
	public void deleteAdmin(long id) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public TsscAdmin getAdmin(long id) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public Iterable<TsscAdmin> findAll() {
		// TODO Auto-generated method stub
		return null;
	}

}
