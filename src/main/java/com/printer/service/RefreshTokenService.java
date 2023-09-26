package com.printer.service;

import java.util.Optional;

import org.springframework.transaction.annotation.Transactional;

import com.printer.model.RefreshToken;

public interface RefreshTokenService {
	
	Optional<RefreshToken> findByToken(String token);

	RefreshToken createRefreshToken(Long userId);

	RefreshToken verifyExpiration(RefreshToken token);

	@Transactional
	int deleteByUserId(Long userId);

}