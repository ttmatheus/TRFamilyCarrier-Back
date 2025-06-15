package br.com.project.TRFamilia.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestHeader;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import br.com.project.TRFamilia.dto.LoginDTO;
import br.com.project.TRFamilia.exceptions.ApiException;
import br.com.project.TRFamilia.services.AuthService;
import jakarta.validation.Valid;

@RestController
@RequestMapping("/auth")
public class AuthController {

	@Autowired
	AuthService authService;

	@GetMapping("/validateJwt")
	public ResponseEntity<?> validateJwt(@RequestHeader("Authorization") String authHeader) {
		if(authHeader != null && authHeader.startsWith("Bearer ")) {
			String token = authHeader.substring(7);
			return authService.validateJwt(token);
		} else {
			throw new ApiException(401, "Missing or invalid Authorization header", HttpStatus.UNAUTHORIZED);
		}
	} 

	@PostMapping("/login")
	public ResponseEntity<?> loginUser(@RequestBody @Valid LoginDTO login) {
		return authService.loginUser(login);
	}
	
}
