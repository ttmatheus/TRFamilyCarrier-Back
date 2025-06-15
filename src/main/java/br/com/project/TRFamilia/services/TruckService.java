package br.com.project.TRFamilia.services;

import java.time.LocalDateTime;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import br.com.project.TRFamilia.dto.CreateTruckDTO;
import br.com.project.TRFamilia.enums.TruckStatus;
import br.com.project.TRFamilia.models.Truck;
import br.com.project.TRFamilia.repositories.TruckRepository;

@Service
public class TruckService {

	@Autowired private TruckRepository truckRepository;

	public Truck saveTruck(CreateTruckDTO createTruckDTO) {
		Truck truck = new Truck(
			createTruckDTO.getLicensePlate(),
			createTruckDTO.getCurrentMileage(),
			createTruckDTO.getBrand(),
			createTruckDTO.getModel(),
			createTruckDTO.getYear(),
			createTruckDTO.getVinNumber(),
			createTruckDTO.getFuelType(),
			createTruckDTO.getMaxLoadCapacity(),
			createTruckDTO.getMaintenanceDueDate(),
			createTruckDTO.getInsuranceExpiration(),
			TruckStatus.available,
			createTruckDTO.getNotes(),
			LocalDateTime.now(),
			LocalDateTime.now()
		);

		return truckRepository.save(truck);
	}

	public Truck updateTruck(Long id, CreateTruckDTO dto) {
		Truck truck = truckRepository.findById(id)
			.orElseThrow(() -> new RuntimeException("Caminhão não encontrado com ID: " + id));

			truck.setLicensePlate(dto.getLicensePlate());
			truck.setCurrentMileage(dto.getCurrentMileage());
			truck.setBrand(dto.getBrand());
			truck.setModel(dto.getModel());
			truck.setYear(dto.getYear());
			truck.setVinNumber(dto.getVinNumber());
			truck.setFuelType(dto.getFuelType());
			truck.setMaxLoadCapacity(dto.getMaxLoadCapacity());
			truck.setMaintenanceDueDate(dto.getMaintenanceDueDate());
			truck.setInsuranceExpiration(dto.getInsuranceExpiration());
			truck.setNotes(dto.getNotes());
			truck.setUpdatedAt(LocalDateTime.now());

		return truckRepository.save(truck);
	}

	public void deleteTruck(Long id) {
		if (!truckRepository.existsById(id)) {
			throw new RuntimeException("Caminhão não encontrado com ID: " + id);
		}
		truckRepository.deleteById(id);
	}
}
