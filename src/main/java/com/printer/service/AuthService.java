package com.printer.service;

import org.springframework.http.ResponseEntity;

import com.printer.dto.request.LogOutRequestDTO;
import com.printer.dto.request.LoginRequestDTO;
import com.printer.dto.request.SignupRequestDTO;
import com.printer.dto.request.TokenRefreshRequestDTO;
import com.printer.dto.response.JwtResponseDTO;
import com.printer.dto.response.MessageResponseDTO;
import com.printer.dto.response.TokenRefreshResponseDTO;

public interface AuthService {

	ResponseEntity<MessageResponseDTO> registerUser(SignupRequestDTO signUpRequest);

	JwtResponseDTO authenticateUser(LoginRequestDTO loginRequest);

	ResponseEntity<MessageResponseDTO> logoutUser(LogOutRequestDTO logOutRequest);

	ResponseEntity<TokenRefreshResponseDTO> refreshtoken(TokenRefreshRequestDTO request);

	ResponseEntity<Boolean> isUserLogger();

}