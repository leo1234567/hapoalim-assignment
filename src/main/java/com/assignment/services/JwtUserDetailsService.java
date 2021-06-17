package com.assignment.services;

import java.util.ArrayList;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

@Service
public class JwtUserDetailsService implements UserDetailsService {
	private static final Logger logger = LoggerFactory.getLogger(JwtUserDetailsService.class);

	@Override
	public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
		logger.info(String.format("Load user: %s", username));

		//actual implementation should use data base here
		if ("user1".equals(username)) {
			return new User("user1", "$2a$10$.936qEjx3ls9kp6C.AlTduKlQ7iTpX/BV4t8xO0txHVhLSsEr.txm",
					new ArrayList<>());
		} else if ("user2".equals(username)) {
			return new User("user2", "$2a$10$.9DOi.JiJRikbARltNuDPupZdSV1tpFzCJ7D7lSGzP9sNFO2F0H2e",
					new ArrayList<>());
		}else {
			throw new UsernameNotFoundException("User not found with username: " + username);
		}
	}

}