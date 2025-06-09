package br.com.project.TRFamilia.services;

import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import br.com.project.TRFamilia.dto.CreateUserDTO;
import br.com.project.TRFamilia.dto.LoginDTO;
import br.com.project.TRFamilia.models.TipoUsuario;
import br.com.project.TRFamilia.models.User;
import br.com.project.TRFamilia.repositories.UserRepository;
import br.com.project.TRFamilia.security.JwtUtil;

@Service
public class UserService {
	
	@Autowired
	private UserRepository userRepository;

	@Autowired
	private PasswordEncoder passwordEncoder;

	@Autowired
	private JwtUtil jwtUtil;

	public User saveUser(CreateUserDTO userDto) {
		String cryptedPassword = passwordEncoder.encode(userDto.getSenha());

		User user = new User(
			userDto.getNome(),
			userDto.getEmail(),
			cryptedPassword,
			TipoUsuario.motorista,
			true
		);

		return userRepository.save(user);
	}

	public ResponseEntity<?> loginUser(LoginDTO user) {
		Optional<User> userOpt = userRepository.findByEmail(user.getEmail());
		if (userOpt.isPresent() && passwordEncoder.matches(user.getSenha(), userOpt.get().getSenhaHash())) {
			return ResponseEntity.ok(jwtUtil.generateToken(user.getEmail()));
		} else {
			return ResponseEntity.status(HttpStatus.UNAUTHORIZED).body("Email ou senha inválidos");
		}
	}
}
