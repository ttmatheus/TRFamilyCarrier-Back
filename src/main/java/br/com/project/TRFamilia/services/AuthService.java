package br.com.project.TRFamilia.services;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import br.com.project.TRFamilia.security.JwtUtil;

@Service
public class AuthService {
	@Autowired
	JwtUtil jwtUtil;

	public ResponseEntity<?> validateJwt(String token) {
		boolean isValid = jwtUtil.validateToken(token);

		if (isValid) {
			String email = jwtUtil.extractEmail(token);
			return ResponseEntity.ok(email);
		} else {
			return ResponseEntity.status(401).body("Token inválido ou expirado");
		}
	}
}
