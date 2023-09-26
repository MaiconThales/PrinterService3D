package com.printer.service;

import com.printer.model.User;

public interface UserService {
	
	User findById(Long userId);
	
	boolean existsByUsername(String username);
	
	boolean existsByEmail(String email);
	
	User saveUser(User user);
	
	User findByUsername(String username);

}