package br.com.project.TRFamilia.dto;

import java.math.BigDecimal;
import java.util.Optional;

import br.com.project.TRFamilia.enums.TripStatus;
import br.com.project.TRFamilia.models.Trip;
import lombok.Getter;
import lombok.Setter;

@Setter
@Getter
class DriverDTO {
	private String name;
}

@Getter
@Setter
public class ResponseTripDTO {
	private Long id;
	private String destination;
	private String departureTime;
	private String status;
	private BigDecimal cargoWeight;
	private String origin;
	private String arrivalTime;
	private String receiverName;
	private String receiverDocument;
	private String cargoDescription;
	private Optional<Long> driverId;
	private Optional<Long> truckId;
	private Optional<DriverDTO> driver;
	private Optional<String> truckLicensePlate;
	private BigDecimal expensesTotal;

	public ResponseTripDTO(Trip trip) {
		this.id = trip.getId();
		this.destination = trip.getDestination();
		this.departureTime = trip.getDepartureTime() != null ? trip.getDepartureTime().toString() : "";
		this.status = trip.getStatus() != null ? trip.getStatus().toString() : TripStatus.in_progress.toString();
		this.cargoWeight = trip.getCargoWeight();
		this.origin = trip.getOrigin();
		this.arrivalTime = trip.getArrivalTime() != null ? trip.getArrivalTime().toString() : "";
		this.receiverName = trip.getReceiverName();
		this.receiverDocument = trip.getReceiverDocument();
		this.cargoDescription = trip.getCargoDescription();
		this.driverId = trip.getDriver() != null ? Optional.of(trip.getDriver().getId()) : Optional.empty();
		this.truckId = trip.getTruck() != null ? Optional.of(trip.getTruck().getId()) : Optional.empty();
		this.driver = trip.getDriver() != null ? Optional.of(new DriverDTO()) : Optional.empty();
		this.truckLicensePlate = trip.getTruck() != null ? Optional.of(trip.getTruck().getLicensePlate()) : Optional.empty();
		if (trip.getDriver() != null) {
			DriverDTO driverDTO = new DriverDTO();
			driverDTO.setName(trip.getDriver().getUser().getName());
			this.driver = Optional.of(driverDTO);
		} else {
			this.driver = Optional.empty();
		}
	}
}
