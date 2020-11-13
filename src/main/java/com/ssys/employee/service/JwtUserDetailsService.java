package com.ssys.employee.service;

import java.util.ArrayList;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import com.ssys.employee.entity.User;
import com.ssys.employee.model.UserDTO;
import com.ssys.employee.repository.UserRepository;

import lombok.extern.slf4j.Slf4j;

@Service
@Slf4j
public class JwtUserDetailsService implements UserDetailsService {

	@Autowired
	private UserRepository userRep;

	@Autowired
	private PasswordEncoder bcryptEncoder;

	@Override
	public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
		User user = userRep.findByUsername(username);
		if (user == null) {
			log.error("User not found with username: " + username);
			throw new UsernameNotFoundException("User not found with username: " + username);
		}
		return new org.springframework.security.core.userdetails.User(user.getUsername(), user.getPassword(),
				new ArrayList<>());
	}

	public User save(UserDTO user) {
		try {
			User newUser = new User();
			newUser.setUsername(user.getUsername());
			newUser.setPassword(bcryptEncoder.encode(user.getPassword()));
			log.info("Registering new user: " + user.getUsername());
			return userRep.save(newUser);
		} catch (Exception e) {
			throw e;
		}
	}
}
