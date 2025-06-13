package br.com.project.TRFamilia.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import br.com.project.TRFamilia.dto.CreateTruckDTO;
import br.com.project.TRFamilia.models.Truck;
import br.com.project.TRFamilia.services.TruckService;
import jakarta.validation.Valid;

@RestController
@RequestMapping("truck")
public class TruckController {

	@Autowired private TruckService truckService;

	@PostMapping("/create")
	public Truck createTruck(@RequestBody @Valid CreateTruckDTO createTruckDto) {
		return truckService.saveTruck(createTruckDto);
	}
}
