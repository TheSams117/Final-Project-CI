package com.example.demo.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

import com.example.demo.service.AdminService;

@Controller
public class TsscAdminController {
	
	@SuppressWarnings("unused")
	private AdminService adminService;

	@Autowired
	public TsscAdminController(AdminService adminService) {
		this.adminService = adminService;
	}
	
	@GetMapping("/login")
	public String log() {
		return "login/login";
	}
}
