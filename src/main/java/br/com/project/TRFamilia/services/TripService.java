package br.com.project.TRFamilia.services;

import java.time.LocalDateTime;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import br.com.project.TRFamilia.dto.CreateTripDTO;
import br.com.project.TRFamilia.enums.TripStatus;
import br.com.project.TRFamilia.models.Driver;
import br.com.project.TRFamilia.models.Trip;
import br.com.project.TRFamilia.models.Truck;
import br.com.project.TRFamilia.repositories.DriverRepository;
import br.com.project.TRFamilia.repositories.TripRepository;
import br.com.project.TRFamilia.repositories.TruckRepository;

@Service
public class TripService {
	@Autowired private TripRepository tripRepository;

	@Autowired private DriverRepository driverRepository;

	@Autowired private TruckRepository truckRepository;

	public ResponseEntity<?> saveTrip(CreateTripDTO createTripDTO) {
		
		Optional<Driver> driver = driverRepository.findById(createTripDTO.getDriverId());
		Optional<Truck> truck = truckRepository.findById(createTripDTO.getTruckId());

		if(!truck.isPresent()) return ResponseEntity.status(HttpStatus.NOT_FOUND).body("Truck not found");

		if(!driver.isPresent()) return ResponseEntity.status(HttpStatus.NOT_FOUND).body("Driver not found");

		Trip trip = new Trip(
			driver.get(),
			truck.get(),
			createTripDTO.getDepartureTime(),
			createTripDTO.getArrivalTime(),
			createTripDTO.getReturnTime(),
			createTripDTO.getOrigin(),
			createTripDTO.getDestination(),
			createTripDTO.getOriginLatitude(),
			createTripDTO.getOriginLongitude(),
			createTripDTO.getDestinationLatitude(),
			createTripDTO.getDestinationLongitude(),
			createTripDTO.getInitialMileage(),
			createTripDTO.getFinalMileage(),
			createTripDTO.getCargoDescription(),
			createTripDTO.getCargoWeight(),
			createTripDTO.getReceiverName(),
			createTripDTO.getReceiverDocument(),
			createTripDTO.getTotalValue(),
			createTripDTO.getCommissionPercentage(),
			TripStatus.scheduled,
			createTripDTO.getNotes(),
			LocalDateTime.now(),
			LocalDateTime.now()
		);

		trip.setDriver(driver.get());

		tripRepository.save(trip);

		Driver driverData = driver.get();

		driverData.setTrip(trip);

		driverRepository.save(driverData);

		return ResponseEntity.ok().body(trip);
	}
}
