package br.com.project.TRFamilia.services;

import java.time.LocalDateTime;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import br.com.project.TRFamilia.dto.CreateFreightBillDTO;
import br.com.project.TRFamilia.enums.FreightBillStatus;
import br.com.project.TRFamilia.models.FreightBill;
import br.com.project.TRFamilia.models.Trip;
import br.com.project.TRFamilia.repositories.FreightBillRepository;
import br.com.project.TRFamilia.repositories.TripRepository;

@Service
public class FreightBillService {
	
	@Autowired private FreightBillRepository freightBillRepository;

	@Autowired private TripRepository tripRepository;

	public ResponseEntity<?> saveFreightBill(CreateFreightBillDTO createFreightBillDTO) {

		Optional<Trip> trip = tripRepository.findById(createFreightBillDTO.getTripId());

		if(!trip.isPresent()) return ResponseEntity.status(404).body("Trip not found");
		

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
}
