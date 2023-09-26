package com.printer.service.impl;

import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.printer.model.User;
import com.printer.repository.UserRepository;
import com.printer.service.UserService;

@Service
public class UserServiceImpl implements UserService {
	
	@Autowired
	private UserRepository userRepository;

	@Override
	public User findById(Long userId) {
		return userRepository.findById(userId).orElseGet(User::new);
	}

	@Override
	public boolean existsByUsername(String username) {
		return this.userRepository.existsByUsername(username);
	}
	
	@Override
	public boolean existsByEmail(String email) {
		return this.userRepository.existsByEmail(email);
	}
	
	@Override
	public User saveUser(User user) {
		return this.userRepository.save(user);
	}
	
	@Override
	public User findByUsername(String username) {
		Optional<User> userOptional = this.userRepository.findByUsername(username);
		if(userOptional.isPresent()) {
			return userOptional.get();
		}
		return null;
	}

}