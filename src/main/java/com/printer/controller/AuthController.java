package com.printer.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.printer.dto.request.LogOutRequestDTO;
import com.printer.dto.request.LoginRequestDTO;
import com.printer.dto.request.SignupRequestDTO;
import com.printer.dto.request.TokenRefreshRequestDTO;
import com.printer.dto.response.JwtResponseDTO;
import com.printer.dto.response.MessageResponseDTO;
import com.printer.dto.response.TokenRefreshResponseDTO;
import com.printer.service.AuthService;

import jakarta.validation.Valid;

@RestController
@RequestMapping("/auth")
@CrossOrigin(origins = "*", maxAge = 3600)
public class AuthController {
	
	@Autowired
	private AuthService authService;

	@PostMapping("/signup")
	public ResponseEntity<MessageResponseDTO> registerUser(@Valid @RequestBody SignupRequestDTO signUpRequest) {
		return this.authService.registerUser(signUpRequest);
	}

	@PostMapping("/signin")
	public ResponseEntity<JwtResponseDTO> authenticateUser(@Valid @RequestBody LoginRequestDTO loginRequest) {
		return ResponseEntity.ok(this.authService.authenticateUser(loginRequest));
	}

	@PostMapping("/logout")
	public ResponseEntity<MessageResponseDTO> logoutUser(@Valid @RequestBody LogOutRequestDTO logOutRequest) {
		return this.authService.logoutUser(logOutRequest);
	}

	@PostMapping("/refreshtoken")
	public ResponseEntity<TokenRefreshResponseDTO> refreshtoken(@Valid @RequestBody TokenRefreshRequestDTO request) {
		return this.authService.refreshtoken(request);
	}

	@GetMapping("/isLogged")
	public ResponseEntity<Boolean> isUserLogger() {
		return this.authService.isUserLogger();
	}

}