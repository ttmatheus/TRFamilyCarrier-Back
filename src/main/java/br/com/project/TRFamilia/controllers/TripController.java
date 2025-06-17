package br.com.project.TRFamilia.controllers;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import br.com.project.TRFamilia.annotations.JustAdmin;
import br.com.project.TRFamilia.dto.CreateTripDTO;
import br.com.project.TRFamilia.dto.ResponseTripDTO;
import br.com.project.TRFamilia.dto.TripDTO;
import br.com.project.TRFamilia.services.TripService;
import jakarta.validation.Valid;

@RestController
@RequestMapping("trip")
public class TripController {
	@Autowired private TripService tripService;

	@PostMapping("/create")
	@JustAdmin
	public ResponseEntity<?> createTrip(@RequestBody @Valid CreateTripDTO createTripDTO) {
		return tripService.saveTrip(createTripDTO);
	}

	@GetMapping
	public List<TripDTO> getTrip(
		@RequestParam(name = "user_id", required = true) Long userId) {
		return tripService.getTripByUserId(userId);
	}

	@GetMapping("/trips")
	@JustAdmin
	public List<ResponseTripDTO> getAllTrips() {
		return tripService.getAllTrips();
	}
}
