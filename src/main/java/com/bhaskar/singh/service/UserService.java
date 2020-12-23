package com.bhaskar.singh.service;

import java.util.Set;

import org.springframework.stereotype.Service;

import com.bhaskar.singh.domain.security.UserRole;
import com.bhaskar.singh.entity.User;

@Service
public interface UserService {
	
	 User createUser(User user, Set<UserRole> userRoles);
	 
	 User findByEmail(String username);

}
