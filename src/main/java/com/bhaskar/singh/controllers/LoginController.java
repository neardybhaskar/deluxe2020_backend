package com.bhaskar.singh.controllers;

import java.util.Collections;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import com.bhaskar.singh.service.UserService;

@RestController
public class LoginController {
	
	@Autowired
	private UserService userService;

	@GetMapping("/token")
	public Map<String, String> token(HttpSession httpSession, HttpServletRequest request) {
		System.out.println(request.getRemoteHost());
		
		String remoteHost = request.getRemoteHost();
		int portNumber = request.getRemotePort();
		
		System.out.println(remoteHost+" "+portNumber);
		System.out.println(request.getRemoteAddr());
		
		return Collections.singletonMap("token", httpSession.getId());
	}
	
	@RequestMapping(value = "/checkSession")
	public ResponseEntity checkSession(HttpServletResponse response) {
		
		 return new ResponseEntity(HttpStatus.OK);
	}
	
	@RequestMapping(value = "/userLogout", method=RequestMethod.POST)
	public ResponseEntity logout() {
		SecurityContextHolder.clearContext();
		return new ResponseEntity(HttpStatus.OK);
	}

}
