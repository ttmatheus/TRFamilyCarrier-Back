package br.com.project.TRFamilia.services;

import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import br.com.project.TRFamilia.services.GeocodingService.Location;

import br.com.project.TRFamilia.dto.CreateTripDTO;
import br.com.project.TRFamilia.dto.ResponseTripDTO;
import br.com.project.TRFamilia.dto.TripDTO;
import br.com.project.TRFamilia.enums.TripStatus;
import br.com.project.TRFamilia.exceptions.ApiException;
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

	@Autowired private GeocodingService geocodingService;

	public ResponseEntity<?> saveTrip(CreateTripDTO createTripDTO) {
		
		Optional<Driver> driver = driverRepository.findById(createTripDTO.getDriverId());
		Optional<Truck> truck = truckRepository.findById(createTripDTO.getTruckId());

		if(!truck.isPresent()) throw new ApiException(404, "Truck not found", HttpStatus.NOT_FOUND);

		if(!driver.isPresent()) throw new ApiException(404, "Driver not found", HttpStatus.NOT_FOUND);

		Location origin = geocodingService.getCoordinates(createTripDTO.getOrigin());
		Location destination = geocodingService.getCoordinates(createTripDTO.getDestination());

		Trip trip = new Trip(
			driver.get(),
			truck.get(),
			createTripDTO.getDepartureTime(),
			createTripDTO.getArrivalTime(),
			createTripDTO.getReturnTime(),
			createTripDTO.getOrigin(),
			createTripDTO.getDestination(),
			origin.lat,
			origin.lng,
			destination.lat,
			destination.lng,
			BigDecimal.ZERO,
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

	public List<TripDTO> getTripByUserId(Long id) {
		Optional<Driver> driver = driverRepository.findByUser_id(id);
		if(!driver.isPresent()) {
			throw new ApiException(404, "Driver not found for user with ID: " + id, HttpStatus.NOT_FOUND);
		}
		List<Trip> trips = tripRepository.findByDriverId_Id(driver.get().getId());
		if (trips.isEmpty()) {
			throw new ApiException(404, "No trips found for user with ID: " + id, HttpStatus.NOT_FOUND);
		}
		return trips.stream()
                .map(TripDTO::new)
                .toList();
	}

	public List<ResponseTripDTO> getAllTrips() {
		List<Trip> trips = tripRepository.findAll();
		if (trips.isEmpty()) {
			throw new ApiException(404, "No trips found", HttpStatus.NOT_FOUND);
		}
		return trips.stream()
				.map(ResponseTripDTO::new)
				.toList();
	}
}
