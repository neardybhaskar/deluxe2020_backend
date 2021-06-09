package com.bhaskar.singh.controllers;

import com.bhaskar.singh.domain.security.UserRole;
import com.bhaskar.singh.entity.User;
import com.bhaskar.singh.service.UserService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpSession;
import java.util.HashMap;
import java.util.Map;

@RestController
public class LoginController {

	private final UserService userService;

	public LoginController(UserService userService) {
		this.userService = userService;
	}

	@GetMapping("{username}/token")
	public Map<String, String> token(HttpSession httpSession,
									 @PathVariable("username") String username) {
		User user = userService.findByEmail(username);
		String roleName = null;
		for (UserRole userRole: user.getUserRoles()) {
			roleName = userRole.getRole().getName();
		}

		Map<String, String> token = new HashMap<>();
		token.put("token", httpSession.getId());
		token.put("userRole", roleName);
		token.put("userId", user.getId().toString());

		return token;
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
