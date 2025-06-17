package br.com.project.TRFamilia.services;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import br.com.project.TRFamilia.dto.CreateDriverDTO;
import br.com.project.TRFamilia.dto.DriverResponseDTO;
import br.com.project.TRFamilia.dto.ResponseDriverDTO;
import br.com.project.TRFamilia.dto.ResponseTruckDTO;
import br.com.project.TRFamilia.dto.UserResponseDTO;
import br.com.project.TRFamilia.enums.DriverStatus;
import br.com.project.TRFamilia.exceptions.ApiException;
import br.com.project.TRFamilia.models.Driver;
import br.com.project.TRFamilia.models.Truck;
import br.com.project.TRFamilia.models.User;
import br.com.project.TRFamilia.repositories.DriverRepository;
import br.com.project.TRFamilia.repositories.TruckRepository;
import br.com.project.TRFamilia.repositories.UserRepository;
import jakarta.persistence.EntityNotFoundException;

@Service
public class DriverService {
	@Autowired private UserRepository userRepository;

	@Autowired private DriverRepository driverRepository;

	@Autowired private TruckRepository truckRepository;

	public ResponseEntity<?> saveDriver(CreateDriverDTO createDriverDTO) {

		Optional<User> user = userRepository.findById(createDriverDTO.getUser());
		Optional<Truck> truck = truckRepository.findById(createDriverDTO.getTrucks());

		if(!user.isPresent()) throw new ApiException(404, "User not found", HttpStatus.NOT_FOUND);

		if(!truck.isPresent()) throw new ApiException(404, "Truck not found", HttpStatus.NOT_FOUND);

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

	public List<ResponseDriverDTO> getAllDrivers() {
		List<Driver> drivers = driverRepository.findAll();

		if (drivers.isEmpty()) {
			throw new ApiException(404, "No drivers found", HttpStatus.NOT_FOUND);
		}

		return drivers.stream()
			.map(ResponseDriverDTO::new)
			.toList();
	}

	public DriverResponseDTO getDriverByUserId(Long userId) {
        // Assuming @EntityGraph in DriverRepository ensures user and truck are eagerly fetched.
        // If not, use Hibernate.initialize(driver.getUser()); and Hibernate.initialize(driver.getTruck()); here.
        Driver driver = driverRepository.findByUser_id(userId)
            .orElseThrow(() -> new EntityNotFoundException("Motorista não encontrado para o User ID: " + userId));

        // --- MAP User entity to UserResponseDTO ---
        UserResponseDTO userResponseDTO = null;
        if (driver.getUser() != null) {
            userResponseDTO = new UserResponseDTO();
            userResponseDTO.setId(driver.getUser().getId());
            userResponseDTO.setName(driver.getUser().getName());
            userResponseDTO.setEmail(driver.getUser().getEmail()); // Assuming User entity has an 'email' field
            // Add any other fields you want to include from User
        }

        // --- MAP Truck entity to ResponseTruckDTO ---
        ResponseTruckDTO responseTruckDTO = null;
        if (driver.getTruck() != null) {
            responseTruckDTO = new ResponseTruckDTO(driver.getTruck());
        }

        // Now pass the DTOs to the DriverResponseDTO constructor
        return new DriverResponseDTO(
            driver.getId(),
            userResponseDTO, 
            responseTruckDTO,
            driver.getLicenseNumber(),
            driver.getLicenseExpiration(),
            driver.getStats()
        );
    }
}
