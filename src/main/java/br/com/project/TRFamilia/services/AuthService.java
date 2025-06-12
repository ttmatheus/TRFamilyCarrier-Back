package br.com.project.TRFamilia.services;

import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import br.com.project.TRFamilia.dto.LoginDTO;
import br.com.project.TRFamilia.dto.LoginResponseDTO;
import br.com.project.TRFamilia.dto.UserInfoDTO;
import br.com.project.TRFamilia.models.User;
import br.com.project.TRFamilia.repositories.UserRepository;
import br.com.project.TRFamilia.security.JwtUtil;

@Service
public class AuthService {
	@Autowired
	JwtUtil jwtUtil;

	@Autowired
	private UserRepository userRepository;

	@Autowired
	private PasswordEncoder passwordEncoder;

	public ResponseEntity<?> validateJwt(String token) {
		boolean isValid = jwtUtil.validateToken(token);

		if (isValid) {
			String email = jwtUtil.extractEmail(token);
			return ResponseEntity.ok(email);
		} else {
			return ResponseEntity.status(401).body("Invalid or expired token.");
		}
	}

	public ResponseEntity<?> loginUser(LoginDTO user) {
		Optional<User> userOpt = userRepository.findByEmail(user.getEmail());
		if (userOpt.isPresent() && passwordEncoder.matches(user.getPassword(), userOpt.get().getHashPassword())) {
			User userFinded = userOpt.get();
			
			UserInfoDTO userInfo = new UserInfoDTO(userFinded.getType().toString());
			LoginResponseDTO response = new LoginResponseDTO(jwtUtil.generateToken(userFinded.getEmail()), userInfo);

			return ResponseEntity.ok(response);
		} else {
			return ResponseEntity.status(HttpStatus.UNAUTHORIZED).body("Invalid email or password.");
		}
	}
}
