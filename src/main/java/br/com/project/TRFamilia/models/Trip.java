package br.com.project.TRFamilia.models;

import java.math.BigDecimal;
import java.time.LocalDateTime;

import com.fasterxml.jackson.annotation.JsonBackReference;
import com.fasterxml.jackson.annotation.JsonManagedReference;

import br.com.project.TRFamilia.enums.TripStatus;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.OneToOne;
import jakarta.persistence.Table;
import lombok.Getter;
import lombok.Setter;

@Entity
@Table(name = "trips")
@Getter
@Setter
public class Trip {

    @Id 
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

	@OneToOne
    @JoinColumn(name = "driver_id", nullable = false, unique = true)
    @JsonBackReference
    private Driver driver;

	@OneToOne
    @JsonManagedReference
    @JoinColumn(name = "truck_id", nullable = false, unique = true)
    private Truck truck;

    @Column(name = "departure_time")
    private LocalDateTime departureTime;

    @Column(name = "arrival_time")
    private LocalDateTime arrivalTime;

    @Column(name = "return_time")
    private LocalDateTime returnTime;

    @Column(name = "origin")
    private String origin;

    @Column(name = "destination")
    private String destination;

    @Column(name = "origin_latitude")
    private Double originLatitude;

    @Column(name = "origin_longitude")
    private Double originLongitude;

    @Column(name = "destination_latitude")
    private Double destinationLatitude;

    @Column(name = "destination_longitude")
    private Double destinationLongitude;

    @Column(name = "initial_mileage")
    private BigDecimal initialMileage;

    @Column(name = "final_mileage")
    private BigDecimal finalMileage;

    @Column(name = "cargo_description")
    private String cargoDescription;

    @Column(name = "cargo_weight")
    private BigDecimal cargoWeight;

    @Column(name = "receiver_name")
    private String receiverName;

    @Column(name = "receiver_document")
    private String receiverDocument;

    @Column(name = "total_value")
    private BigDecimal totalValue;

    @Column(name = "commission_percentage")
    private BigDecimal commissionPercentage;

    @OneToOne
    @JsonBackReference
    @JoinColumn(name = "freightbill_id", nullable = true, unique = true)
    private FreightBill freightBill;

    @Column(name = "status")
    @Enumerated(EnumType.STRING)
    private TripStatus status;

    @Column(name = "notes")
    private String notes;

    @Column(name = "created_at")
    private LocalDateTime createdAt;

    @Column(name = "updated_at")
    private LocalDateTime updatedAt;

    public Trip() {
    }

    public Trip(Driver driver, Truck truck, LocalDateTime departureTime, LocalDateTime arrivalTime,
                LocalDateTime returnTime, String origin, String destination, Double originLatitude,
                Double originLongitude, Double destinationLatitude, Double destinationLongitude,
                BigDecimal initialMileage, BigDecimal finalMileage, String cargoDescription,
                BigDecimal cargoWeight, String receiverName, String receiverDocument, BigDecimal totalValue,
                BigDecimal commissionPercentage, TripStatus status, String notes,
                LocalDateTime createdAt, LocalDateTime updatedAt) {
        this.driver = driver;
        this.truck = truck;
        this.departureTime = departureTime;
        this.arrivalTime = arrivalTime;
        this.returnTime = returnTime;
        this.origin = origin;
        this.destination = destination;
        this.originLatitude = originLatitude;
        this.originLongitude = originLongitude;
        this.destinationLatitude = destinationLatitude;
        this.destinationLongitude = destinationLongitude;
        this.initialMileage = initialMileage;
        this.finalMileage = finalMileage;
        this.cargoDescription = cargoDescription;
        this.cargoWeight = cargoWeight;
        this.receiverName = receiverName;
        this.receiverDocument = receiverDocument;
        this.totalValue = totalValue;
        this.commissionPercentage = commissionPercentage;
        this.status = status;
        this.notes = notes;
        this.createdAt = createdAt;
        this.updatedAt = updatedAt;
    }
}