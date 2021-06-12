package com.bhaskar.singh.service;

import com.bhaskar.singh.domain.security.UserRole;
import com.bhaskar.singh.entity.User;
import org.springframework.stereotype.Service;

import java.util.Set;

@Service
public interface UserService {
	
	 void createUser(User user, Set<UserRole> userRoles);
	 
	 User findByEmail(String username);

	 User findById(long id);

}
