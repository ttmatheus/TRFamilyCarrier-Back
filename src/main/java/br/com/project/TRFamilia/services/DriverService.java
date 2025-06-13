package br.com.project.TRFamilia.services;

import java.time.LocalDateTime;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import br.com.project.TRFamilia.dto.CreateDriverDTO;
import br.com.project.TRFamilia.enums.DriverStatus;
import br.com.project.TRFamilia.models.Driver;
import br.com.project.TRFamilia.models.Truck;
import br.com.project.TRFamilia.models.User;
import br.com.project.TRFamilia.repositories.DriverRepository;
import br.com.project.TRFamilia.repositories.TruckRepository;
import br.com.project.TRFamilia.repositories.UserRepository;

@Service
public class DriverService {
	@Autowired private UserRepository userRepository;

	@Autowired private DriverRepository driverRepository;

	@Autowired private TruckRepository truckRepository;

	public ResponseEntity<?> saveDriver(CreateDriverDTO createDriverDTO) {

		Optional<User> user = userRepository.findById(createDriverDTO.getUser());
		Optional<Truck> truck = truckRepository.findById(createDriverDTO.getTrucks());

		if(!user.isPresent()) return ResponseEntity.status(HttpStatus.NOT_FOUND).body("User not found");

		if(!truck.isPresent()) return ResponseEntity.status(HttpStatus.NOT_FOUND).body("Truck not found");

		Driver driver = new Driver(
			user.get(),
			createDriverDTO.getCpf(),
			createDriverDTO.getLicenseNumber(),
			createDriverDTO.getLicenseExpiration(),
			createDriverDTO.getPhoneNumber(),
			createDriverDTO.getAddress(),
			createDriverDTO.getBirthDate(),
			createDriverDTO.getHireDate(),
			createDriverDTO.getStats(),
			truck.get(),
			DriverStatus.active,
			LocalDateTime.now(),
			LocalDateTime.now()
		);

		driverRepository.save(driver);

		return ResponseEntity.ok().body(driver);
	}
}
