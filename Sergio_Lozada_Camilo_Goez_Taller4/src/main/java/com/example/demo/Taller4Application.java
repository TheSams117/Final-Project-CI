package com.example.demo;

import java.time.LocalDate;
import java.time.LocalTime;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.ConfigurableApplicationContext;
import org.springframework.context.annotation.Bean;
import org.thymeleaf.extras.java8time.dialect.Java8TimeDialect;

import com.example.demo.exception.GameServiceException;
import com.example.demo.exception.TopicServiceException;
import com.example.demo.model.TsscAdmin;
import com.example.demo.model.TsscGame;
import com.example.demo.model.TsscTopic;
import com.example.demo.service.AdminService;
import com.example.demo.service.GameService;
import com.example.demo.service.TopicService;


@SpringBootApplication
public class Taller4Application {
	
	@Bean
	public Java8TimeDialect java8TimeDialect() {
		return new Java8TimeDialect();
	}

	public static void main(String[] args) throws GameServiceException, TopicServiceException {
		ConfigurableApplicationContext c = SpringApplication.run(Taller4Application.class, args);
		
		AdminService u = c.getBean(AdminService.class);
		TopicService t = c.getBean(TopicService.class);
		GameService g = c.getBean(GameService.class);
		
		TsscAdmin admin1 = new TsscAdmin();
		admin1.setPassword("{noop}123");
		admin1.setSuperAdmin("sadmin");
		admin1.setUser("sadmin");
		u.createAdmin(admin1);
		
		TsscAdmin admin2 = new TsscAdmin();
		admin2.setPassword("{noop}123");
		admin2.setSuperAdmin("admin");
		admin2.setUser("admin");
		u.createAdmin(admin2);
		
		TsscGame n = new TsscGame();
		n.setScheduledDate(LocalDate.of(2020, 6, 3));
		n.setScheduledTime(LocalTime.MAX);
		n.setStartTime(LocalTime.MAX);
		n.setPauseSeconds((long)3);
		n.setAdminPassword("123456789");
		n.setGuestPassword("123456789");
		n.setUserPassword("123456789");
		n.setName("Juego 1");
		g.createGame(n);
		
		
		TsscTopic newTopic = new TsscTopic();
		newTopic.setDescription("Este es el tema nuevo");
		newTopic.setGroupPrefix("T1");
		newTopic.setDefaultGroups(1);
		newTopic.setDefaultSprints(2);
		newTopic.setName("Tema1");
		t.createTopic(newTopic);
	}

}
