package com.bhaskar.singh.service;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import com.bhaskar.singh.entity.User;
import com.bhaskar.singh.repository.UserRepository;

@Service
public class UserDetailsServiceImpl implements UserDetailsService{
	
	private static final Logger LOG = LoggerFactory.getLogger(UserDetailsServiceImpl.class);
	
	@Autowired
	private UserRepository userRepository;

	@Override
	public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
		
		User user = userRepository.findByEmail(username);
		if(user == null) {
			LOG.warn("Username {} not found ",username);
			throw new UsernameNotFoundException("User not found for email "+username);
		}
		
		return user;
	}

}
