package br.com.project.TRFamilia.models;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.time.LocalDateTime;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Table;

@Entity
@Table(name = "trucks")
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
    private String status;

    @Column(name = "notes")
    private String notes;

    @Column(name = "created_at")
    private LocalDateTime createdAt;

    @Column(name = "updated_at")
    private LocalDateTime updatedAt;

    public Truck() {
    }

    public Truck(Long id, String licensePlate, BigDecimal currentMileage, String brand, String model, Integer year,
                 String vinNumber, String fuelType, BigDecimal maxLoadCapacity, LocalDate maintenanceDueDate,
                 LocalDate insuranceExpiration, String status, String notes, LocalDateTime createdAt,
                 LocalDateTime updatedAt) {
        this.id = id;
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

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getLicensePlate() {
        return licensePlate;
    }

    public void setLicensePlate(String licensePlate) {
        this.licensePlate = licensePlate;
    }

    public BigDecimal getCurrentMileage() {
        return currentMileage;
    }

    public void setCurrentMileage(BigDecimal currentMileage) {
        this.currentMileage = currentMileage;
    }

    public String getBrand() {
        return brand;
    }

    public void setBrand(String brand) {
        this.brand = brand;
    }

    public String getModel() {
        return model;
    }

    public void setModel(String model) {
        this.model = model;
    }

    public Integer getYear() {
        return year;
    }

    public void setYear(Integer year) {
        this.year = year;
    }

    public String getVinNumber() {
        return vinNumber;
    }

    public void setVinNumber(String vinNumber) {
        this.vinNumber = vinNumber;
    }

    public String getFuelType() {
        return fuelType;
    }

    public void setFuelType(String fuelType) {
        this.fuelType = fuelType;
    }

    public BigDecimal getMaxLoadCapacity() {
        return maxLoadCapacity;
    }

    public void setMaxLoadCapacity(BigDecimal maxLoadCapacity) {
        this.maxLoadCapacity = maxLoadCapacity;
    }

    public LocalDate getMaintenanceDueDate() {
        return maintenanceDueDate;
    }

    public void setMaintenanceDueDate(LocalDate maintenanceDueDate) {
        this.maintenanceDueDate = maintenanceDueDate;
    }

    public LocalDate getInsuranceExpiration() {
        return insuranceExpiration;
    }

    public void setInsuranceExpiration(LocalDate insuranceExpiration) {
        this.insuranceExpiration = insuranceExpiration;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public String getNotes() {
        return notes;
    }

    public void setNotes(String notes) {
        this.notes = notes;
    }

    public LocalDateTime getCreatedAt() {
        return createdAt;
    }

    public void setCreatedAt(LocalDateTime createdAt) {
        this.createdAt = createdAt;
    }

    public LocalDateTime getUpdatedAt() {
        return updatedAt;
    }

    public void setUpdatedAt(LocalDateTime updatedAt) {
        this.updatedAt = updatedAt;
    }
}
