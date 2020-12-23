package com.bhaskar.singh;

import java.util.HashSet;
import java.util.Set;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

import com.bhaskar.singh.domain.security.Role;
import com.bhaskar.singh.domain.security.UserRole;
import com.bhaskar.singh.entity.User;
import com.bhaskar.singh.security.config.SecurityUtility;
import com.bhaskar.singh.service.UserService;

@SpringBootApplication
public class Deluxe2020BackendApplication {
	
	@Autowired
	private UserService userService;
	 

	public static void main(String[] args) {
		SpringApplication.run(Deluxe2020BackendApplication.class, args);
	}

}
