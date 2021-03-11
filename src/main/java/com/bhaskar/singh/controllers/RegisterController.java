package com.bhaskar.singh.controllers;

import com.bhaskar.singh.domain.security.Role;
import com.bhaskar.singh.domain.security.UserRole;
import com.bhaskar.singh.entity.User;
import com.bhaskar.singh.security.config.SecurityUtility;
import com.bhaskar.singh.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import java.util.HashMap;
import java.util.HashSet;
import java.util.Set;

@RestController
public class RegisterController {
	
	private final UserService userService;

	@Autowired
	public RegisterController(UserService userService) {
		this.userService = userService;
	}

	@RequestMapping( value = "/registerUser", method = RequestMethod.POST)
	public ResponseEntity<?> registerUser(@RequestBody HashMap<String, String> mapper) {

		try {
			String firstName = mapper.get("firstname");
			String lastName = mapper.get("lastname");
			String email = mapper.get("email");
			String password = mapper.get("password");

			if(userService.findByEmail(email) != null) {
				return new ResponseEntity<>("emailExists",HttpStatus.CONFLICT);
			}

			User user = new User();
			user.setFirstName(firstName);
			user.setLastName(lastName);
			user.setEmail(email);
			user.setPassword(SecurityUtility.passwordEncoder().encode(password));

			Role role = new Role();
			role.setId(1L);
			role.setName("USER");

			Set<UserRole> userRoles = new HashSet<>();
			userRoles.add(new UserRole(user, role));
			userService.createUser(user, userRoles);
		} catch (Exception e) {
			e.getStackTrace();
		}
		
		return new ResponseEntity<>(HttpStatus.OK);
	}

}
