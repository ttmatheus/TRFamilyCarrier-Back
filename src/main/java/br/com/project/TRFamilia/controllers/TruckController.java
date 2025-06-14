package br.com.project.TRFamilia.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import br.com.project.TRFamilia.annotations.JustAdmin;
import br.com.project.TRFamilia.dto.CreateTruckDTO;
import br.com.project.TRFamilia.models.Truck;
import br.com.project.TRFamilia.services.TruckService;
import jakarta.validation.Valid;

@RestController
@RequestMapping("truck")
public class TruckController {

	@Autowired private TruckService truckService;

	@PostMapping("/create")
	@JustAdmin
	public Truck createTruck(@RequestBody @Valid CreateTruckDTO createTruckDto) {
		return truckService.saveTruck(createTruckDto);
	}

	@PutMapping("/{id}")
	@JustAdmin
	public Truck updateTruck(@PathVariable Long id, @RequestBody @Valid CreateTruckDTO updateDto) {
		return truckService.updateTruck(id, updateDto);
	}

	@DeleteMapping("/{id}")
	@JustAdmin
	public void deleteTruck(@PathVariable Long id) {
		truckService.deleteTruck(id);
	}
}
