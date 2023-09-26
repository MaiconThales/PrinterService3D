package com.printer.service.impl;

import java.time.Instant;
import java.util.Optional;
import java.util.UUID;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import com.printer.exception.TokenRefreshException;
import com.printer.model.RefreshToken;
import com.printer.repository.RefreshTokenRepository;
import com.printer.service.RefreshTokenService;
import com.printer.service.UserService;

@Service
public class RefreshTokenServiceImpl implements RefreshTokenService {
	
	@Value("${auth.app.jwtRefreshExpirationMs}")
	private Long refreshTokenDurationMs;

	private final RefreshTokenRepository refreshTokenRepository;

	private final UserService userService;
	
	@Autowired
	public RefreshTokenServiceImpl(RefreshTokenRepository refreshTokenRepository, UserService userService) {
		this.refreshTokenRepository = refreshTokenRepository;
		this.userService = userService;
	}

	@Override
	public Optional<RefreshToken> findByToken(String token) {
		return this.refreshTokenRepository.findByToken(token);
	}

	@Override
	public RefreshToken createRefreshToken(Long userId) {
		RefreshToken refreshToken = new RefreshToken();
		refreshToken.setUser(this.userService.findById(userId));
		refreshToken.setExpiryDate(Instant.now().plusMillis(refreshTokenDurationMs));
		refreshToken.setToken(UUID.randomUUID().toString());
		return this.refreshTokenRepository.save(refreshToken);
	}

	@Override
	public RefreshToken verifyExpiration(RefreshToken token) {
		if (token.getExpiryDate().compareTo(Instant.now()) < 0) {
			this.refreshTokenRepository.delete(token);
			throw new TokenRefreshException(token.getToken(),
					"Refresh token was expired. Please make a new signin request");
		}

		return token;
	}

	@Override
	public int deleteByUserId(Long userId) {
		return this.refreshTokenRepository.deleteByUser(this.userService.findById(userId));
	}

}