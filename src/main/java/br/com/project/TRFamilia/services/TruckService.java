package br.com.project.TRFamilia.services;

import java.beans.FeatureDescriptor;
import java.time.LocalDateTime;
import java.util.List;
import java.util.Objects;
import java.util.stream.Stream;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;

import br.com.project.TRFamilia.dto.CreateTruckDTO;
import br.com.project.TRFamilia.dto.ResponseTruckDTO;
import br.com.project.TRFamilia.dto.UpdateTruckDTO;
import br.com.project.TRFamilia.enums.TruckStatus;
import br.com.project.TRFamilia.exceptions.ApiException;
import br.com.project.TRFamilia.models.Truck;
import br.com.project.TRFamilia.repositories.DriverRepository;
import br.com.project.TRFamilia.repositories.TruckRepository;

import org.springframework.beans.BeanUtils;
import org.springframework.beans.BeanWrapper;
import org.springframework.beans.BeanWrapperImpl;

@Service
public class TruckService {

	@Autowired private TruckRepository truckRepository;

	@Autowired private DriverRepository driverRepository;

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

	public static String[] getNullPropertyNames(Object source) {
        final BeanWrapper wrappedSource = new BeanWrapperImpl(source);
        return Stream.of(wrappedSource.getPropertyDescriptors())
            .map(FeatureDescriptor::getName)
            .filter(propertyName -> wrappedSource.getPropertyValue(propertyName) == null)
            .toArray(String[]::new);
    }

	public Truck updateTruck(Long id, UpdateTruckDTO dto) {
		Truck truck = truckRepository.findById(id)
			.orElseThrow(() -> new ApiException(404, "Truck not found", HttpStatus.NOT_FOUND));

		String[] nullProperties = getNullPropertyNames(dto);

		BeanUtils.copyProperties(dto, truck, nullProperties);

		truck.setUpdatedAt(LocalDateTime.now());

		return truckRepository.save(truck);
	}

	public void deleteTruck(Long id) {
		if (!truckRepository.existsById(id)) {
			throw new ApiException(404, "Truck not found", HttpStatus.NOT_FOUND);
		}
		truckRepository.deleteById(id);
	}

	public List<ResponseTruckDTO> getAllTrucks() {
		List<Truck> trucks = truckRepository.findAll();
		if (trucks.isEmpty()) {
			throw new ApiException(404, "No trucks found", HttpStatus.NOT_FOUND);
		}

		return trucks.stream()
			.map(ResponseTruckDTO::new)
			.toList();
	}

	public List<ResponseTruckDTO> getTruckWithNotHaveDrive() {
		List<Truck> allTrucks = truckRepository.findAll();
		if (allTrucks.isEmpty()) {
			throw new ApiException(404, "No trucks found", HttpStatus.NOT_FOUND);
		}


		List<Long> usedTruckIds = driverRepository.findAll().stream()
			.map(driver -> {
				Truck truck = driver.getTruck();
				return truck != null ? truck.getId() : null;
			})
			.filter(Objects::nonNull)
			.toList();

		return allTrucks.stream()
			.filter(truck -> !usedTruckIds.contains(truck.getId()))
			.map(ResponseTruckDTO::new)
			.toList();
	}
}
