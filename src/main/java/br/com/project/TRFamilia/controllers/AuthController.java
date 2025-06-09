package br.com.project.TRFamilia.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import br.com.project.TRFamilia.services.AuthService;

@RestController
@RequestMapping("/auth")
public class AuthController {

	@Autowired
	AuthService authService;

	@GetMapping("/validateJwt/{token}")
	public ResponseEntity<?> validateJwt(@PathVariable String token) {
		return authService.validateJwt(token);
	} 
	
}
