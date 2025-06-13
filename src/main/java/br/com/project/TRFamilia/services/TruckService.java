package br.com.project.TRFamilia.services;

import java.time.LocalDateTime;

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
}
