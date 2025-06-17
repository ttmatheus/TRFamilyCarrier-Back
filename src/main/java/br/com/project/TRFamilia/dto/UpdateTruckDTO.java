package br.com.project.TRFamilia.dto;

import java.math.BigDecimal;
import java.time.LocalDate;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class UpdateTruckDTO {
 
    private String licensePlate;

    private BigDecimal currentMileage;

    private String brand;

    private String model;

    private Integer year;

    private String vinNumber;

    private String fuelType;

    private BigDecimal maxLoadCapacity;

    private LocalDate maintenanceDueDate;

    private LocalDate insuranceExpiration;

    private String notes;
}
