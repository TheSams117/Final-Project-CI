package com.example.demo.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

import com.example.demo.delegate.AdminDelegate;

@Controller
public class TsscAdminController {
	
	@SuppressWarnings("unused")
	private AdminDelegate adminDelegate;

	@Autowired
	public TsscAdminController(AdminDelegate adminDelegate) {
		this.adminDelegate = adminDelegate;
	}
	
	@GetMapping("/login")
	public String log() {
		return "login/login";
	}
}
