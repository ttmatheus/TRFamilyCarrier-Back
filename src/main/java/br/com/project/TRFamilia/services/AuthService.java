package br.com.project.TRFamilia.services;

import java.util.Map;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import br.com.project.TRFamilia.dto.AuthInfoDto;
import br.com.project.TRFamilia.dto.LoginDTO;
import br.com.project.TRFamilia.dto.LoginResponseDTO;
import br.com.project.TRFamilia.dto.UserInfoDTO;
import br.com.project.TRFamilia.exceptions.ApiException;
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

			Map<String, Object> userClaims = jwtUtil.extractUserClaim(token);

			Integer userId = null;
			if (userClaims.get("id") instanceof Number number) {
				userId = number.intValue();
			}

			String role = null;
			if (userClaims.get("role") instanceof String str) {
				role = str;
			}

			return ResponseEntity.ok(new AuthInfoDto(email, role, userId));
		} else {
			throw new ApiException(401, "Invalid or expired token.", HttpStatus.UNAUTHORIZED);
		}
	}

	public ResponseEntity<?> loginUser(LoginDTO user) {
		Optional<User> userOpt = userRepository.findByEmail(user.getEmail());
		if (userOpt.isPresent() && passwordEncoder.matches(user.getPassword(), userOpt.get().getPassword())) {
			User userFinded = userOpt.get();
			
			UserInfoDTO userInfo = new UserInfoDTO(userFinded.getUserType().toString(), userFinded.getId(), userFinded.getEmail());
			LoginResponseDTO response = new LoginResponseDTO(jwtUtil.generateToken(userInfo), userInfo);

			return ResponseEntity.ok(response);
		} else {
			throw new ApiException(401, "Invalid email or password.", HttpStatus.UNAUTHORIZED);
		}
	}
}
