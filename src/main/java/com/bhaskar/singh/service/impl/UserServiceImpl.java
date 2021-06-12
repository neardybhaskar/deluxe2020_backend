package com.bhaskar.singh.service.impl;

import com.bhaskar.singh.domain.security.UserRole;
import com.bhaskar.singh.entity.User;
import com.bhaskar.singh.repository.RoleRepository;
import com.bhaskar.singh.repository.UserRepository;
import com.bhaskar.singh.service.UserService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;

import java.util.Optional;
import java.util.Set;

@Service
public class UserServiceImpl implements UserService {
	
	private static final Logger LOG = LoggerFactory.getLogger(UserServiceImpl.class);
	
	private final UserRepository userRepository;
	
	private final RoleRepository roleRepository;

	public UserServiceImpl(UserRepository userRepository, RoleRepository roleRepository) {
		this.userRepository = userRepository;
		this.roleRepository = roleRepository;
	}

	@Override
	public void createUser(User user, Set<UserRole> userRoles) {
		User localUser = this.userRepository.findByEmail(user.getEmail());
		
		if(localUser != null) {
			LOG.info("User with email {} already exist ",user.getEmail());
		} else {
			userRoles.forEach(userRole -> this.roleRepository.save(userRole.getRole()) );
			user.getUserRoles().addAll(userRoles);
			this.userRepository.save(user);
		}
	}

	@Override
	public User findByEmail(String email) {
		return this.userRepository.findByEmail(email);
	}

	@Override
	public User findById(long id) {
		Optional<User> optionalUser = this.userRepository.findById(id);
		return optionalUser.get();
	}
}
