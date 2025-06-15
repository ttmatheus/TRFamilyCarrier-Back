package br.com.project.TRFamilia.models;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.time.LocalDateTime;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

import br.com.project.TRFamilia.enums.TruckStatus;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import lombok.Getter;
import lombok.Setter;

@Entity
@Table(name = "trucks")
@Getter
@Setter
@JsonIgnoreProperties({"hibernateLazyInitializer", "handler"})
public class Truck {
	@Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "license_plate") 
    private String licensePlate;

    @Column(name = "current_mileage")
    private BigDecimal currentMileage;

    @Column(name = "brand")
    private String brand;

    @Column(name = "model")
    private String model;

    @Column(name = "year")
    private Integer year;

    @Column(name = "vin_number")
    private String vinNumber;

    @Column(name = "fuel_type")
    private String fuelType;

    @Column(name = "max_load_capacity")
    private BigDecimal maxLoadCapacity;

    @Column(name = "maintenance_due_date")
    private LocalDate maintenanceDueDate;

    @Column(name = "insurance_expiration")
    private LocalDate insuranceExpiration;

    @Column(name = "status")
    @Enumerated(EnumType.STRING)
    private TruckStatus status;

    @Column(name = "notes")
    private String notes;

    @Column(name = "created_at")
    private LocalDateTime createdAt;

    @Column(name = "updated_at")
    private LocalDateTime updatedAt;

    public Truck() {
    }

    public Truck(String licensePlate, BigDecimal currentMileage, String brand, String model, Integer year,
                 String vinNumber, String fuelType, BigDecimal maxLoadCapacity, LocalDate maintenanceDueDate,
                 LocalDate insuranceExpiration, TruckStatus status, String notes, LocalDateTime createdAt,
                 LocalDateTime updatedAt) {
        this.licensePlate = licensePlate;
        this.currentMileage = currentMileage;
        this.brand = brand;
        this.model = model;
        this.year = year;
        this.vinNumber = vinNumber;
        this.fuelType = fuelType;
        this.maxLoadCapacity = maxLoadCapacity;
        this.maintenanceDueDate = maintenanceDueDate;
        this.insuranceExpiration = insuranceExpiration;
        this.status = status;
        this.notes = notes;
        this.createdAt = createdAt;
        this.updatedAt = updatedAt;
    }
}
