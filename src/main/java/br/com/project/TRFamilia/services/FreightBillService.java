package br.com.project.TRFamilia.services;

import java.time.LocalDateTime;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;

import br.com.project.TRFamilia.dto.CreateFreightBillDTO;
import br.com.project.TRFamilia.enums.FreightBillStatus;
import br.com.project.TRFamilia.exceptions.ApiException;
import br.com.project.TRFamilia.models.FreightBill;
import br.com.project.TRFamilia.models.Trip;
import br.com.project.TRFamilia.repositories.FreightBillRepository;
import br.com.project.TRFamilia.repositories.TripRepository;

@Service
public class FreightBillService {

    @Autowired private FreightBillRepository freightBillRepository;

    @Autowired private TripRepository tripRepository;

    public FreightBill saveFreightBill(CreateFreightBillDTO dto) {
        Trip trip = tripRepository.findById(dto.getTripId())
            .orElseThrow(() -> new ApiException(404, "Trip not found", HttpStatus.NOT_FOUND));

        if (freightBillRepository.existsByTrip(trip)) {
            throw new ApiException(400, "Freight bill already exists for this trip", HttpStatus.BAD_REQUEST);
        }

        FreightBill freightBill = new FreightBill(
            dto.getInitialValue(),
            dto.getRemainingValue(),
            dto.getTruckExpensesTotal(),
            dto.getTripExpensesTotal(),
            dto.getDriverPaymentValue(),
            dto.getCompanyRevenue(),
            FreightBillStatus.pending,
            dto.getNotes(),
            LocalDateTime.now(),
            LocalDateTime.now(),
            trip
        );

        trip.setFreightBill(freightBill);

        tripRepository.save(trip);
        return freightBillRepository.save(freightBill);
    }

    public FreightBill updateFreightBill(Long id, CreateFreightBillDTO dto) {
        FreightBill freightBill = freightBillRepository.findById(id)
            .orElseThrow(() -> new ApiException(404, "Freight bill not found", HttpStatus.NOT_FOUND));

        freightBill.setInitialValue(dto.getInitialValue());
        freightBill.setRemainingValue(dto.getRemainingValue());
        freightBill.setTruckExpensesTotal(dto.getTruckExpensesTotal());
        freightBill.setTripExpensesTotal(dto.getTripExpensesTotal());
        freightBill.setDriverPaymentValue(dto.getDriverPaymentValue());
        freightBill.setCompanyRevenue(dto.getCompanyRevenue());
        freightBill.setNotes(dto.getNotes());
        freightBill.setUpdatedAt(LocalDateTime.now());

        return freightBillRepository.save(freightBill);
    }

    public void deleteFreightBill(Long id) {
        if (!freightBillRepository.existsById(id)) {
            throw new ApiException(404, "Freight bill not found", HttpStatus.NOT_FOUND);
        }
        freightBillRepository.deleteById(id);
    }

    public FreightBill getFreightBillById(Long id) {
        return freightBillRepository.findById(id)
            .orElseThrow(() -> new ApiException(404, "Freight bill not found", HttpStatus.NOT_FOUND));
    }
}