package br.com.project.TRFamilia.dto;

import java.math.BigDecimal;
import java.time.LocalDateTime;

import br.com.project.TRFamilia.enums.TripStatus;
import jakarta.validation.constraints.NotNull;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class UpdateTripDTO {

    @NotNull
    private Long id;

    private Long driverId;
    private Long truckId;

    private LocalDateTime departureTime;
    private LocalDateTime arrivalTime;
    private LocalDateTime returnTime;

    private String origin;
    private String destination;

    private Double originLatitude;
    private Double originLongitude;
    private Double destinationLatitude;
    private Double destinationLongitude;

    private Double initialMileage;
    private Double finalMileage;

    private String cargoDescription;
    private Double cargoWeight;

    private String receiverName;
    private String receiverDocument;

    private BigDecimal totalValue;
    private Double commissionPercentage;

    private TripStatus status;

    private String notes;
}