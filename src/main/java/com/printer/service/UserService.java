package com.printer.service;

import com.printer.model.User;

public interface UserService {
	
	/**
	 * Returns the user that was searched for by ID.
	 * @param userId is the user id that will be searched.
	 * @return It is the user located in the database, if it is not found, a NULL object is returned.
	 */
	User findById(Long userId);
	
	/**
	 * Checks if the user exists in the database based on username
	 * @param username is the user's username.
	 * @return TRUE the user already exists and FALSE does not exist.
	 */
	boolean existsByUsername(String username);
	
	/**
	 * Checks if the user exists in the database based on email
	 * @param email is the user's email.
	 * @return TRUE the user already exists and FALSE does not exist.
	 */
	boolean existsByEmail(String email);
	
	/**
	 * Saves the user object to the database.
	 * @param user object with user information.
	 * @return Returns the user saved in the database.
	 */
	User saveUser(User user);
	
	/**
	 * Searches for the user in the database using username.
	 * @param username is the username that will be used to locate the user.
	 * @return Returns the user object if found in the database and NULL if nothing is found.
	 */
	User findByUsername(String username);

}