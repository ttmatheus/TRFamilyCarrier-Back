package br.com.project.TRFamilia.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import br.com.project.TRFamilia.annotations.JustAdmin;
import br.com.project.TRFamilia.dto.CreateTripDTO;
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
}
