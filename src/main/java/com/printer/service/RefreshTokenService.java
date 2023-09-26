package com.printer.service;

import java.util.Optional;

import org.springframework.transaction.annotation.Transactional;

import com.printer.model.RefreshToken;

public interface RefreshTokenService {
	
	/**
	 * Finds the RefreshToken from the session token.
	 * @param token the session token.
	 * @return Returns an Optional of the object located in the database.
	 */
	Optional<RefreshToken> findByToken(String token);

	/**
	 * A RefreshToken record is created from the user ID.
	 * @param userId Session user ID.
	 * @return Returns the RefreshToken saved in the database.
	 */
	RefreshToken createRefreshToken(Long userId);

	/**
	 * Checks for expiration of the RefreshToken.
	 * If it is expired, an exception is generated.
	 * @param token is the RefreshToken of the session.
	 * @return Returns the RefreshToken if it is valid.
	 */
	RefreshToken verifyExpiration(RefreshToken token);

	/**
	 * Reports the RefreshToken by user ID.
	 * @param userId is the user ID of the session.
	 * @return Returns the number of affected rows in the database.
	 */
	@Transactional
	int deleteByUserId(Long userId);

}