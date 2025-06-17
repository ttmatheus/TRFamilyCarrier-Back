package br.com.project.TRFamilia.services;

import java.time.LocalDateTime;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;

import br.com.project.TRFamilia.dto.CreateMaintenanceRecordDTO;
import br.com.project.TRFamilia.exceptions.ApiException;
import br.com.project.TRFamilia.models.MaintenanceRecord;
import br.com.project.TRFamilia.models.Truck;
import br.com.project.TRFamilia.repositories.MaintenanceRecordRepository;
import br.com.project.TRFamilia.repositories.TruckRepository;

@Service
public class MaintenanceRecordService {

    @Autowired private MaintenanceRecordRepository maintenanceRecordRepository;
    @Autowired private TruckRepository truckRepository;

    public MaintenanceRecord create(CreateMaintenanceRecordDTO dto) {
        Truck truck = truckRepository.findById(dto.getTruckId())
            .orElseThrow(() -> new ApiException(404, "Truck not found", HttpStatus.NOT_FOUND));

        MaintenanceRecord record = new MaintenanceRecord(
            null,
            truck,
            dto.getMaintenanceType(),
            dto.getDescription(),
            dto.getCost(),
            dto.getMileage(),
            dto.getMaintenanceDate(),
            dto.getNextMaintenanceDate(),
            dto.getServiceProvider(),
            dto.getReceiptUrl(),
            LocalDateTime.now(),
            LocalDateTime.now()
        );

        return maintenanceRecordRepository.save(record);
    }

    public MaintenanceRecord update(Long id, CreateMaintenanceRecordDTO dto) {
        MaintenanceRecord record = maintenanceRecordRepository.findById(id)
            .orElseThrow(() -> new ApiException(404, "Maintenance record not found", HttpStatus.NOT_FOUND));

        Truck truck = truckRepository.findById(dto.getTruckId())
            .orElseThrow(() -> new ApiException(404, "Truck not found", HttpStatus.NOT_FOUND));

        record.setTruck(truck);
        record.setMaintenanceType(dto.getMaintenanceType());
        record.setDescription(dto.getDescription());
        record.setCost(dto.getCost());
        record.setMileage(dto.getMileage());
        record.setMaintenanceDate(dto.getMaintenanceDate());
        record.setNextMaintenanceDate(dto.getNextMaintenanceDate());
        record.setServiceProvider(dto.getServiceProvider());
        record.setReceiptUrl(dto.getReceiptUrl());
        record.setUpdatedAt(LocalDateTime.now());

        return maintenanceRecordRepository.save(record);
    }

    public List<MaintenanceRecord> getAll() {
        return maintenanceRecordRepository.findAll();
    }

    public MaintenanceRecord getById(Long id) {
        return maintenanceRecordRepository.findById(id)
            .orElseThrow(() -> new ApiException(404, "Maintenance record not found", HttpStatus.NOT_FOUND));
    }
}
