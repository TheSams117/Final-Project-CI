package com.example.demo.web;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import com.example.demo.model.TsscAdmin;
import com.example.demo.service.AdminService;

@RestController
public class AdminRestControllerImp implements AdminRestController {
	@Autowired
	private AdminService AdminService;
	
	@PostMapping("/Admins")
	@Override
	public TsscAdmin createAdmin(@RequestBody TsscAdmin Admin){
		return AdminService.createAdmin(Admin);
	}
	
	@PutMapping("/Admins")
	@Override
	public TsscAdmin updateAdmin(@RequestBody TsscAdmin Admin){
		return AdminService.updateAdmin(Admin);
	}
	
	@DeleteMapping("/Admins/{id}")
	@Override
	public void deleteAdmin(@PathVariable("id") long id){
		TsscAdmin Admin = AdminService.getAdmin(id);
		AdminService.deleteAdmin(Admin);
		
	}
	
	@GetMapping("/Admins/{id}")
	@Override
	public TsscAdmin getAdmin(@PathVariable("id") long id){
		return AdminService.getAdmin(id);
	}
	
	@GetMapping("/Admins")
	@Override
	public Iterable<TsscAdmin> findAll() {
		return AdminService.findAll();
	}
}
