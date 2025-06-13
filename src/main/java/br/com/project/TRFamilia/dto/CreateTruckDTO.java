package br.com.project.TRFamilia.dto;

import java.math.BigDecimal;
import java.time.LocalDate;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;

public class CreateTruckDTO {
 
	@NotBlank
    private String licensePlate;

	@NotNull
    private BigDecimal currentMileage;

	@NotBlank
    private String brand;

	@NotBlank
    private String model;

	@NotNull
    private Integer year;

	@NotBlank
    private String vinNumber;

	@NotBlank
    private String fuelType;

	@NotNull
    private BigDecimal maxLoadCapacity;

	@NotNull
    private LocalDate maintenanceDueDate;

	@NotNull
    private LocalDate insuranceExpiration;

	@NotBlank
    private String notes;

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

    public String getNotes() {
        return notes;
    }

    public void setNotes(String notes) {
        this.notes = notes;
    }
}
