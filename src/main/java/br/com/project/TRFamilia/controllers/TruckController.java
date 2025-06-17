package br.com.project.TRFamilia.controllers;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import br.com.project.TRFamilia.annotations.JustAdmin;
import br.com.project.TRFamilia.dto.CreateTruckDTO;
import br.com.project.TRFamilia.dto.ResponseTruckDTO;
import br.com.project.TRFamilia.dto.UpdateTruckDTO;
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
	public Truck updateTruck(@PathVariable Long id, @RequestBody @Valid UpdateTruckDTO updateDto) {
		return truckService.updateTruck(id, updateDto);
	}

	@DeleteMapping("/{id}")
	@JustAdmin
	public void deleteTruck(@PathVariable Long id) {
		truckService.deleteTruck(id);
	}

	@GetMapping("/trucks")
	@JustAdmin
	public List<ResponseTruckDTO> getAllTrucks() {
		return truckService.getAllTrucks();
	}

	@GetMapping("/trucks/notHaveDrive")
	@JustAdmin
	public List<ResponseTruckDTO> getTruckWithNotHaveDrive() {
		return truckService.getTruckWithNotHaveDrive();
	}
}
