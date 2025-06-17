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
class TruckDTO {
	private String license_plate;
}

@Getter
@Setter
public class ResponseTripDTO {
	private Long id;
	private String destination;
	private String departure_time;
	private String status;
	private BigDecimal cargo_weight;
	private String origin;
	private String arrival_time;
	private String receiver_name;
	private String receiver_document;
	private String cargo_description;
	private Optional<Long> driver_id;
	private Optional<Long> truck_id;
	private Optional<DriverDTO> driver;
	private Optional<TruckDTO> truck;

	public ResponseTripDTO(Trip trip) {
		this.id = trip.getId();
		this.destination = trip.getDestination();
		this.departure_time = trip.getDepartureTime() != null ? trip.getDepartureTime().toString() : "";
		this.status = trip.getStatus() != null ? trip.getStatus().toString() : TripStatus.in_progress.toString();
		this.cargo_weight = trip.getCargoWeight();
		this.origin = trip.getOrigin();
		this.arrival_time = trip.getArrivalTime() != null ? trip.getArrivalTime().toString() : "";
		this.receiver_name = trip.getReceiverName();
		this.receiver_document = trip.getReceiverDocument();
		this.cargo_description = trip.getCargoDescription();
		this.driver_id = trip.getDriver() != null ? Optional.of(trip.getDriver().getId()) : Optional.empty();
		this.truck_id = trip.getTruck() != null ? Optional.of(trip.getTruck().getId()) : Optional.empty();
		this.driver = trip.getDriver() != null ? Optional.of(new DriverDTO()) : Optional.empty();
		this.truck = trip.getTruck() != null ? Optional.of(new TruckDTO()) : Optional.empty();
		if (trip.getDriver() != null) {
			DriverDTO driverDTO = new DriverDTO();
			driverDTO.setName(trip.getDriver().getUser().getName());
			this.driver = Optional.of(driverDTO);
		} else {
			this.driver = Optional.empty();
		}
	}
}
