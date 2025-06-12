package br.com.project.TRFamilia.services;

import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import br.com.project.TRFamilia.dto.CreateUserDTO;
import br.com.project.TRFamilia.models.User;
import br.com.project.TRFamilia.models.UserType;
import br.com.project.TRFamilia.repositories.UserRepository;

@Service
public class UserService {
	
	@Autowired
	private UserRepository userRepository;

	@Autowired
	private PasswordEncoder passwordEncoder;

	public ResponseEntity<?> saveUser(CreateUserDTO userDto) {
		Optional<User> emailExists = userRepository.findByEmail(userDto.getEmail());
		if(emailExists.isPresent()) {
			return ResponseEntity.status(HttpStatus.CONFLICT).body("Email already exists.");
		}
		String cryptedPassword = passwordEncoder.encode(userDto.getPassword());

		User user = new User(
			userDto.getNome(),
			userDto.getEmail(),
			cryptedPassword,
			UserType.driver,
			true
		);

		userRepository.save(user);

		return ResponseEntity.ok(user);
	}
}
