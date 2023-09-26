package com.printer.dto.response;

import lombok.AllArgsConstructor;
import lombok.Data;

@Data
@AllArgsConstructor
public class TokenRefreshResponseDTO {
	
	private String accessToken;
	private String refreshToken;
	private String tokenType = "Bearer";
	
	public TokenRefreshResponseDTO(String accessToken, String refreshToken) {
		this.accessToken = accessToken;
		this.refreshToken = refreshToken;
	}

}