package br.com.project.TRFamilia.services;

import java.beans.FeatureDescriptor;
import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;
import java.util.stream.Stream;

import org.springframework.beans.BeanUtils;
import org.springframework.beans.BeanWrapper;
import org.springframework.beans.BeanWrapperImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import br.com.project.TRFamilia.dto.CreateUserDTO;
import br.com.project.TRFamilia.dto.UpdateUserDTO;
import br.com.project.TRFamilia.enums.UserType;
import br.com.project.TRFamilia.exceptions.ApiException;
import br.com.project.TRFamilia.models.User;
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
			throw new ApiException(409, "Email already exists.", HttpStatus.CONFLICT);
		}
		String cryptedPassword = passwordEncoder.encode(userDto.getPassword());

		User user = new User(
			userDto.getName(),
			userDto.getEmail(),
			cryptedPassword,
			UserType.driver,
			userDto.isActive() || true
		);

		userRepository.save(user);

		return ResponseEntity.ok(user);
	}

	public List<User> getAdmins() {
		return userRepository.findByUserType(UserType.admin);
	}
	public List<User> getDrivers() {
		return userRepository.findByUserType(UserType.driver);
	}
	public List<User> getAllUsers() {
		return userRepository.findAll();
	}

	public static String[] getNullPropertyNames(Object source) {
        final BeanWrapper wrappedSource = new BeanWrapperImpl(source);
        return Stream.of(wrappedSource.getPropertyDescriptors())
            .map(FeatureDescriptor::getName)
            .filter(propertyName -> wrappedSource.getPropertyValue(propertyName) == null)
            .toArray(String[]::new);
    }

	public User updateUser(Long id, UpdateUserDTO dto) {
		User user = userRepository.findById(id)
			.orElseThrow(() -> new ApiException(404, "User not found", HttpStatus.NOT_FOUND));

		String[] nullProperties = getNullPropertyNames(dto);

		BeanUtils.copyProperties(dto, user, nullProperties);

		user.setUpdatedAt(LocalDateTime.now());

		return userRepository.save(user);
	}
}
