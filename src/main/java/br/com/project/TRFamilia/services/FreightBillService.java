package br.com.project.TRFamilia.services;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;

import br.com.project.TRFamilia.dto.CreateFreightBillDTO;
import br.com.project.TRFamilia.dto.ResponseFreightBillDTO;
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

	@Autowired private DriverRepository driverRepository;

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

        // Salvar o FreightBill antes de relacioná-lo à Trip
        freightBillRepository.save(freightBill);

        // Relacionar e salvar a Trip, se necessário (opcional, se bidirecional)
        trip.setFreightBill(freightBill);
        tripRepository.save(trip); // Opcional, só se precisar manter a relação bidirecional salva

        return freightBill;
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

    public List<ResponseFreightBillDTO> getFreightBillByUserId(Long id) {
        // Buscar o motorista pelo ID do usuário
        Optional<Driver> driver = driverRepository.findByUser_id(id);
        if (!driver.isPresent()) {
            throw new ApiException(404, "Driver not found for user with ID: " + id, HttpStatus.NOT_FOUND);
        }

        // Buscar todas as viagens associadas a esse motorista
        List<Trip> trips = tripRepository.findByDriverId_Id(driver.get().getId());
        if (trips.isEmpty()) {
            throw new ApiException(404, "Trips not found for user with ID: " + id, HttpStatus.NOT_FOUND);
        }

        // Para cada viagem, buscar os FreightBills e achatar a lista
        List<FreightBill> freightBills = trips.stream()
            .flatMap(trip -> freightBillRepository.findByTripId_Id(trip.getId()).stream())
            .toList();

        if (freightBills.isEmpty()) {
            throw new ApiException(404, "Freight Bill not found for user with ID: " + id, HttpStatus.NOT_FOUND);
        }

        // Mapear para DTOs
        List<ResponseFreightBillDTO> freightBillDTOs = freightBills.stream()
            .map(ResponseFreightBillDTO::new)
            .toList();

        return freightBillDTOs;
    }

    public List<ResponseFreightBillDTO> getAllFreightBills() {
        List<FreightBill> freightBills = freightBillRepository.findAll();
        return freightBills.stream()
            .map(ResponseFreightBillDTO::new)
            .toList();
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
