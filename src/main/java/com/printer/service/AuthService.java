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

	/**
	 * Register a new user in the database.
	 * @param signUpRequest object that has the data of the user who will be registered.
	 * @return Returns a ResponseEntity with the status and message for the front.
	 */
	ResponseEntity<MessageResponseDTO> registerUser(SignupRequestDTO signUpRequest);

	/**
	 * Performs user authentication.
	 * @param loginRequest is the object with the data to log in.
	 * @return Returns a JwtResponseDTO with the Token and RefreshToken data.
	 */
	JwtResponseDTO authenticateUser(LoginRequestDTO loginRequest);

	/**
	 * Logs the user out of the system.
	 * @param logOutRequest is the object with the user ID of the session.
	 * @return Returns a ResponseEntity with the status and message for the front.
	 */
	ResponseEntity<MessageResponseDTO> logoutUser(LogOutRequestDTO logOutRequest);

	/**
	 * Realiza a atualização do Token caso ele tenha vencido.
	 * @param request é o RefreshToken para válidar o acesso.
	 * @return Returns a ResponseEntity with the status and message for the front or generates an expiration exception.
	 */
	ResponseEntity<TokenRefreshResponseDTO> refreshtoken(TokenRefreshRequestDTO request);

	/**
	 * Check if the user is logged in to the system.
	 * @return Returns a ResponseEntity with a boolean stating TRUE for logged in and FALSE for logged out.
	 */
	ResponseEntity<Boolean> isUserLogger();

}