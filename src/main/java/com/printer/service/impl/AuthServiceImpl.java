package com.printer.service.impl;

import java.util.HashSet;
import java.util.List;
import java.util.Optional;
import java.util.Set;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AnonymousAuthenticationToken;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import com.printer.dto.request.LogOutRequestDTO;
import com.printer.dto.request.LoginRequestDTO;
import com.printer.dto.request.SignupRequestDTO;
import com.printer.dto.request.TokenRefreshRequestDTO;
import com.printer.dto.response.JwtResponseDTO;
import com.printer.dto.response.MessageResponseDTO;
import com.printer.dto.response.TokenRefreshResponseDTO;
import com.printer.enums.ERole;
import com.printer.enums.MessageSystem;
import com.printer.exception.TokenRefreshException;
import com.printer.model.RefreshToken;
import com.printer.model.Role;
import com.printer.model.User;
import com.printer.security.jwt.JwtUtils;
import com.printer.service.AuthService;
import com.printer.service.RefreshTokenService;
import com.printer.service.RoleService;
import com.printer.service.UserService;

@Service
public class AuthServiceImpl implements AuthService {

	@Autowired
	private RefreshTokenService refreshTokenService;
	@Autowired
	private UserService userService;
	@Autowired
	private RoleService roleService;
	@Autowired
	private PasswordEncoder encoder;
	@Autowired
	private AuthenticationManager authenticationManager;
	@Autowired
	private JwtUtils jwtUtils;

	@Override
	public ResponseEntity<MessageResponseDTO> registerUser(SignupRequestDTO signUpRequest) {
		ResponseEntity<MessageResponseDTO> validUser = userExists(signUpRequest);
		if (validUser != null) {
			return validUser;
		}
		User user = new User(signUpRequest.getUsername(), signUpRequest.getEmail(),
				this.encoder.encode(signUpRequest.getPassword()), signUpRequest.getLanguage());
		Set<String> strRoles = signUpRequest.getRole();
		Set<Role> roles = new HashSet<>();
		roles.addAll(setRoles(strRoles, roles));
		user.setRoles(roles);
		this.userService.saveUser(user);
		return new ResponseEntity<>(new MessageResponseDTO(MessageSystem.USER_CREATE_SUCCESS.getString()), HttpStatus.OK);
	}

	@Override
	public JwtResponseDTO authenticateUser(LoginRequestDTO loginRequest) {
		Authentication authentication = this.authenticationManager.authenticate(
				new UsernamePasswordAuthenticationToken(loginRequest.getUsername(), loginRequest.getPassword()));
		SecurityContextHolder.getContext().setAuthentication(authentication);

		String jwt = this.jwtUtils.generateJwtToken(authentication);
		
		User userLogged = this.userService.findByUsername(authentication.getName());
		String languageUser = userLogged.getLanguage();
		this.removeTrashRefreshtoken(userLogged.getId());
		
		List<String> roles = authentication.getAuthorities().stream().map(GrantedAuthority::getAuthority).toList();

		RefreshToken refreshToken = this.refreshTokenService.createRefreshToken(userLogged.getId());
		return new JwtResponseDTO(jwt, refreshToken.getToken(), userLogged.getId(), userLogged.getUsername(),
				userLogged.getEmail(), roles, languageUser);
	}

	@Override
	public ResponseEntity<MessageResponseDTO> logoutUser(LogOutRequestDTO logOutRequest) {
		this.refreshTokenService.deleteByUserId(logOutRequest.getUserId());
		return ResponseEntity.ok(new MessageResponseDTO(MessageSystem.LOGOUT_SYSTEM.getString()));
	}

	@Override
	public ResponseEntity<TokenRefreshResponseDTO> refreshtoken(TokenRefreshRequestDTO request) {
		String requestRefreshToken = request.getRefreshToken();

		Optional<User> resultUser = this.refreshTokenService.findByToken(requestRefreshToken)
				.map(this.refreshTokenService::verifyExpiration).map(RefreshToken::getUser);
		if (resultUser.isPresent()) {
			String token = this.jwtUtils.generateTokenFromUsername(resultUser.get().getUsername());
			return ResponseEntity.ok(new TokenRefreshResponseDTO(token, requestRefreshToken));
		}

		throw new TokenRefreshException(requestRefreshToken, MessageSystem.NOT_REFLESH_TOKEN_DATABASE.getString());
	}

	@Override
	public ResponseEntity<Boolean> isUserLogger() {
		Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
		if (!(authentication instanceof AnonymousAuthenticationToken)) {
			return new ResponseEntity<>(authentication.isAuthenticated(), HttpStatus.OK);
		}
		return new ResponseEntity<>(false, HttpStatus.UNAUTHORIZED);
	}

	private Set<Role> setRoles(Set<String> strRoles, Set<Role> roles) {
		if (strRoles == null) {
			roles.add(this.roleService.findByName(ERole.ROLE_USER));
		} else {
			strRoles.forEach(role -> {
				switch (role) {
				case "admin":
					roles.add(this.roleService.findByName(ERole.ROLE_ADMIN));
					break;
				case "mod":
					roles.add(this.roleService.findByName(ERole.ROLE_MODERATOR));
					break;
				default:
					roles.add(this.roleService.findByName(ERole.ROLE_USER));
				}
			});
		}
		return roles;
	}

	private ResponseEntity<MessageResponseDTO> userExists(SignupRequestDTO signUpRequest) {
		if (this.userService.existsByUsername(signUpRequest.getUsername())) {
			return new ResponseEntity<>(new MessageResponseDTO(MessageSystem.USERNAME_EXISTS.getString()),
					HttpStatus.BAD_REQUEST);
		}
		if (this.userService.existsByEmail(signUpRequest.getEmail())) {
			return new ResponseEntity<>(new MessageResponseDTO(MessageSystem.EMAIL_EXISTS.getString()),
					HttpStatus.BAD_REQUEST);
		}
		return null;
	}

	private void removeTrashRefreshtoken(long idUser) {
		this.refreshTokenService.deleteByUserId(idUser);
	}

}