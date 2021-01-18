package com.bhaskar.singh.controllers;

import java.util.HashMap;
import java.util.HashSet;
import java.util.Set;

import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import com.bhaskar.singh.domain.security.Role;
import com.bhaskar.singh.domain.security.UserRole;
import com.bhaskar.singh.entity.User;
import com.bhaskar.singh.security.config.SecurityUtility;
import com.bhaskar.singh.service.UserService;

@RestController
public class RegisterController {
	
	@Autowired
	private UserService userService;
	
	@RequestMapping( value = "/registerUser", method = RequestMethod.POST)
	public ResponseEntity registerUser(HttpServletRequest request,
			@RequestBody HashMap<String, String> mapper) throws Exception {
		
		String firstName = mapper.get("firstname");
		String lastName = mapper.get("lastname");
		String email = mapper.get("email");
		String password = mapper.get("password");
		
		if(userService.findByEmail(email) != null) {
			return new ResponseEntity("emailExists",HttpStatus.CONFLICT);
		}
		
		User user = new User();
		user.setFirstName(firstName);
		user.setLastName(lastName);
		user.setEmail(email);
		user.setPassword(SecurityUtility.passwordEncoder().encode(password));
		
		Role role = new Role();
		role.setId(1l);
		role.setName("USER");
		
		Set<UserRole> userRoles = new HashSet<UserRole>();
		userRoles.add(new UserRole(user, role));
		userService.createUser(user, userRoles);
		
		return new ResponseEntity(HttpStatus.OK);
	}

}
