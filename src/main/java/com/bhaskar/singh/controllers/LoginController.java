package com.bhaskar.singh.controllers;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import javax.servlet.http.HttpSession;
import java.util.Collections;
import java.util.Map;

@RestController
public class LoginController {

	@GetMapping("/token")
	public Map<String, String> token(HttpSession httpSession) {

		return Collections.singletonMap("token", httpSession.getId());
	}
	
	@RequestMapping(value = "/checkSession")
	public ResponseEntity<?> checkSession() {
		
		 return new ResponseEntity<>(HttpStatus.OK);
	}
	
	@RequestMapping(value = "/userLogout", method=RequestMethod.POST)
	public ResponseEntity<?> logout() {
		SecurityContextHolder.clearContext();
		return new ResponseEntity<>(HttpStatus.OK);
	}

}
