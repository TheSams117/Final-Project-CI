package com.example.demo.security;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import com.example.demo.model.TsscAdmin;
import com.example.demo.dao.AdminDao;

@Service
public class MyCustomUserDetailsService implements UserDetailsService {
	
	@Autowired
	private AdminDao adminDao;
	
	@Override
	
	public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
		
		TsscAdmin admin = adminDao.findByUser(username);

		if (admin != null) {
			User.UserBuilder builder = User.withUsername(username);
			builder.password(admin.getPassword());
			builder.roles(admin.getSuperAdmin());
			return builder.build();
		} else {
			throw new UsernameNotFoundException("User not found.");
		}
	}
}