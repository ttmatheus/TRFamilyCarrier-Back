package br.com.project.TRFamilia.dto;

import java.math.BigDecimal;
import java.time.LocalDateTime;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class CreateTripDTO {

	@NotNull
    private Long driverId;

	@NotNull
    private Long truckId;

	@NotNull
    private LocalDateTime departureTime;

    private LocalDateTime arrivalTime;

    private LocalDateTime returnTime;

	@NotBlank
    private String origin;

	@NotBlank
    private String destination;

    private Double originLatitude;

    private Double originLongitude;

    private Double destinationLatitude;

    private Double destinationLongitude;

    private BigDecimal initialMileage;

    private BigDecimal finalMileage;

	@NotBlank
    private String cargoDescription;

	@NotNull
    private BigDecimal cargoWeight;

	@NotBlank
    private String receiverName;

	@NotBlank
    private String receiverDocument;

    private BigDecimal totalValue;

    private BigDecimal commissionPercentage;

    private String notes;
}
