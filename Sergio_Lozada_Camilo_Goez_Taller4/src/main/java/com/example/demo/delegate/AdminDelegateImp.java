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
		return restTemplate.postForObject("http://localhost:8080/api/Admins", Admin, TsscAdmin.class);
	}

	@Override
	public void updateAdmin(TsscAdmin Admin) {
		restTemplate.put("http://localhost:8080/api/Admins", Admin);
	}

	@Override
	public void deleteAdmin(long id) {
		restTemplate.delete("http://localhost:8080/api/Admins/"+id,id);
		
	}

	@Override
	public TsscAdmin getAdmin(long id) {
		return restTemplate.getForObject("http://localhost:8080/api/Admins/"+id, TsscAdmin.class);
	}

	@SuppressWarnings("unchecked")
	@Override
	public Iterable<TsscAdmin> findAll() {
		return restTemplate.getForObject("http://localhost:8080/api/Admins", Iterable.class);
	}

}
