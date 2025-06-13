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

@Entity
@Table(name = "trips")
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

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Driver getDriver() {
        return driver;
    }

    public void setDriver(Driver driver) {
        this.driver = driver;
    }

    public Truck getTruckId() {
        return truck;
    }

    public void setTruckId(Truck truck) {
        this.truck = truck;
    }

    public LocalDateTime getDepartureTime() {
        return departureTime;
    }

    public void setDepartureTime(LocalDateTime departureTime) {
        this.departureTime = departureTime;
    }

    public LocalDateTime getArrivalTime() {
        return arrivalTime;
    }

    public void setArrivalTime(LocalDateTime arrivalTime) {
        this.arrivalTime = arrivalTime;
    }

    public LocalDateTime getReturnTime() {
        return returnTime;
    }

    public void setReturnTime(LocalDateTime returnTime) {
        this.returnTime = returnTime;
    }

    public String getOrigin() {
        return origin;
    }

    public void setOrigin(String origin) {
        this.origin = origin;
    }

    public String getDestination() {
        return destination;
    }

    public void setDestination(String destination) {
        this.destination = destination;
    }

    public Double getOriginLatitude() {
        return originLatitude;
    }

    public void setOriginLatitude(Double originLatitude) {
        this.originLatitude = originLatitude;
    }

    public Double getOriginLongitude() {
        return originLongitude;
    }

    public void setOriginLongitude(Double originLongitude) {
        this.originLongitude = originLongitude;
    }

    public Double getDestinationLatitude() {
        return destinationLatitude;
    }

    public void setDestinationLatitude(Double destinationLatitude) {
        this.destinationLatitude = destinationLatitude;
    }

    public Double getDestinationLongitude() {
        return destinationLongitude;
    }

    public void setDestinationLongitude(Double destinationLongitude) {
        this.destinationLongitude = destinationLongitude;
    }

    public BigDecimal getInitialMileage() {
        return initialMileage;
    }

    public void setInitialMileage(BigDecimal initialMileage) {
        this.initialMileage = initialMileage;
    }

    public BigDecimal getFinalMileage() {
        return finalMileage;
    }

    public void setFinalMileage(BigDecimal finalMileage) {
        this.finalMileage = finalMileage;
    }

    public String getCargoDescription() {
        return cargoDescription;
    }

    public void setCargoDescription(String cargoDescription) {
        this.cargoDescription = cargoDescription;
    }

    public BigDecimal getCargoWeight() {
        return cargoWeight;
    }

    public void setCargoWeight(BigDecimal cargoWeight) {
        this.cargoWeight = cargoWeight;
    }

    public String getReceiverName() {
        return receiverName;
    }

    public void setReceiverName(String receiverName) {
        this.receiverName = receiverName;
    }

    public String getReceiverDocument() {
        return receiverDocument;
    }

    public void setReceiverDocument(String receiverDocument) {
        this.receiverDocument = receiverDocument;
    }

    public BigDecimal getTotalValue() {
        return totalValue;
    }

    public void setTotalValue(BigDecimal totalValue) {
        this.totalValue = totalValue;
    }

    public BigDecimal getCommissionPercentage() {
        return commissionPercentage;
    }

    public void setCommissionPercentage(BigDecimal commissionPercentage) {
        this.commissionPercentage = commissionPercentage;
    }

    public FreightBill getFreightBill() {
        return freightBill;
    }

    public void setFreightBill(FreightBill freightBill) {
        this.freightBill = freightBill;
    }

    public TripStatus getStatus() {
        return status;
    }

    public void setStatus(TripStatus status) {
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