package br.com.project.TRFamilia.services;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import br.com.project.TRFamilia.models.User;
import br.com.project.TRFamilia.repositories.UserRepository;

@Service
public class UserService {
	
	@Autowired
	private UserRepository userRepository;

	@Autowired
	private PasswordEncoder passwordEncoder;

	public User saveUser(User user) {
		String cryptedPassword = passwordEncoder.encode(user.getPassword());
		user.setPassword(cryptedPassword);

		return userRepository.save(user);
	}
}
