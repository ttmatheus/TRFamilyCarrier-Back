package br.com.project.TRFamilia.dto;

import java.math.BigDecimal;

import br.com.project.TRFamilia.enums.TruckStatus;
import br.com.project.TRFamilia.models.Truck;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class ResponseTruckDTO {
	private Long id;
	private String licensePlate;
	private String brand;
	private String model;
	private String year;
	private String fuelType;
	private BigDecimal maxLoadCapacity;
	private BigDecimal currentMileage;
	private String maintenanceDueDate;
	private String insuranceExpiration;
	private TruckStatus status;

	public ResponseTruckDTO(Truck truck) {
		this.id = truck.getId();
		this.licensePlate = truck.getLicensePlate();
		this.brand = truck.getBrand();
		this.model = truck.getModel();
		this.year = truck.getYear().toString();
		this.fuelType = truck.getFuelType();
		this.maxLoadCapacity = truck.getMaxLoadCapacity();
		this.currentMileage = truck.getCurrentMileage();
		this.maintenanceDueDate = truck.getMaintenanceDueDate().toString();
		this.insuranceExpiration = truck.getInsuranceExpiration().toString();
		this.status = truck.getStatus();
	}
}
