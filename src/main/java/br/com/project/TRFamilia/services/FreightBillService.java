package br.com.project.TRFamilia.services;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import br.com.project.TRFamilia.dto.CreateFreightBillDTO;
import br.com.project.TRFamilia.dto.FreightBillDTO;
import br.com.project.TRFamilia.enums.FreightBillStatus;
import br.com.project.TRFamilia.exceptions.ApiException;
import br.com.project.TRFamilia.models.Driver;
import br.com.project.TRFamilia.models.FreightBill;
import br.com.project.TRFamilia.models.Trip;
import br.com.project.TRFamilia.repositories.DriverRepository;
import br.com.project.TRFamilia.repositories.FreightBillRepository;
import br.com.project.TRFamilia.repositories.TripRepository;

@Service
public class FreightBillService {
	
	@Autowired private FreightBillRepository freightBillRepository;

	@Autowired private TripRepository tripRepository;

	@Autowired private DriverRepository driverRepository;

	public ResponseEntity<?> saveFreightBill(CreateFreightBillDTO createFreightBillDTO) {

		Optional<Trip> trip = tripRepository.findById(createFreightBillDTO.getTripId());

		if(!trip.isPresent()) throw new ApiException(404, "Trip not found", HttpStatus.NOT_FOUND);
		

		FreightBill freightBill = new FreightBill(
			createFreightBillDTO.getInitialValue(),
			createFreightBillDTO.getRemainingValue(),
			createFreightBillDTO.getTruckExpensesTotal(),
			createFreightBillDTO.getTripExpensesTotal(),
			createFreightBillDTO.getDriverPaymentValue(),
			createFreightBillDTO.getCompanyRevenue(),
			FreightBillStatus.pending,
			createFreightBillDTO.getNotes(),
			LocalDateTime.now(),
			LocalDateTime.now(),
			trip.get()
		);

		freightBill.setTrip(trip.get());

		freightBillRepository.save(freightBill);

		Trip tripData = trip.get();

		tripData.setFreightBill(freightBill);

		tripRepository.save(tripData);

		return ResponseEntity.ok().body(freightBill);
	}

	public List<FreightBillDTO> getFreightBillByUserId(Long id) {
		Optional<Driver> driver = driverRepository.findByUser_id(id);
		if(!driver.isPresent()) {
			throw new ApiException(404, "Driver not found for user with ID: " + id, HttpStatus.NOT_FOUND);
		}
		Optional<Trip> trip = tripRepository.findOneByDriverId_Id(driver.get().getId());
		if(!trip.isPresent()) {
			throw new ApiException(404, "Trip not found for user with ID: " + id, HttpStatus.NOT_FOUND);
		}

		List<FreightBill> freightBills = freightBillRepository.findByTripId_Id(trip.get().getId());

		if(!freightBills.isEmpty()) {
			throw new ApiException(404, "Freight Bill not found for trip with ID: " + trip.get().getId(), HttpStatus.NOT_FOUND);
		}

		List<FreightBillDTO> freightBillDTOs = freightBills.stream()
			.map(FreightBillDTO::new)
			.toList();

		return freightBillDTOs;
	}
}
