package com.example.demo;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.ConfigurableApplicationContext;
import org.springframework.context.annotation.Bean;
import org.thymeleaf.extras.java8time.dialect.Java8TimeDialect;

import com.example.demo.model.TsscAdmin;
import com.example.demo.service.AdminService;


@SpringBootApplication
public class Taller3Application {
	
	@Bean
	public Java8TimeDialect java8TimeDialect() {
		return new Java8TimeDialect();
	}

	public static void main(String[] args) {
		ConfigurableApplicationContext c = SpringApplication.run(Taller3Application.class, args);
		AdminService u = c.getBean(AdminService.class);
		
		TsscAdmin admin1 = new TsscAdmin();
		admin1.setPassword("{noop}123");
		admin1.setSuperAdmin("sadmin");
		admin1.setUser("sadmin");
		u.save(admin1);
		
		TsscAdmin admin2 = new TsscAdmin();
		admin2.setPassword("{noop}123");
		admin2.setSuperAdmin("admin");
		admin2.setUser("admin");
		u.save(admin2);
	}

}
