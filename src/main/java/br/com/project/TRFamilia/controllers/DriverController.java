package br.com.project.TRFamilia.controllers;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import br.com.project.TRFamilia.annotations.JustAdmin;
import br.com.project.TRFamilia.dto.CreateDriverDTO;
import br.com.project.TRFamilia.dto.DriverResponseDTO;
import br.com.project.TRFamilia.dto.ResponseDriverDTO;
import br.com.project.TRFamilia.services.DriverService;
import jakarta.validation.Valid;

@RestController
@RequestMapping("driver")
public class DriverController {

	@Autowired
	private DriverService driverService;

	@PostMapping("/create")
	@JustAdmin
	public ResponseEntity<?> createUser(@RequestBody @Valid CreateDriverDTO driver) {
		return driverService.saveDriver(driver);
	}

	@GetMapping("/drivers")
	@JustAdmin
	public List<ResponseDriverDTO> getAllDrivers() {
		return driverService.getAllDrivers();
	}

	@GetMapping("/{id}")
	public DriverResponseDTO getDriver(
		@PathVariable Long id) {
		return driverService.getDriverByUserId(id);
	}
}
