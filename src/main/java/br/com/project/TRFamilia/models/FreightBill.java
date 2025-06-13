package br.com.project.TRFamilia.models;

import java.math.BigDecimal;
import java.time.LocalDateTime;

import com.fasterxml.jackson.annotation.JsonManagedReference;

import br.com.project.TRFamilia.enums.FreightBillStatus;
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
@Table(name = "freightbills")
public class FreightBill {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    @OneToOne
    @JsonManagedReference
    @JoinColumn(name = "trip_id", nullable = false, unique = true)
    private Trip trip;

    @Column(name = "initial_value")
    private BigDecimal initialValue;

    @Column(name = "remaining_value")
    private BigDecimal remainingValue;

    @Column(name = "truck_expenses_total")
    private BigDecimal truckExpensesTotal;

    @Column(name = "trip_expenses_total")
    private BigDecimal tripExpensesTotal;

    @Column(name = "driver_payment_value")
    private BigDecimal driverPaymentValue;

    @Column(name = "company_revenue")
    private BigDecimal companyRevenue;

    @Column(name = "payment_status")
    @Enumerated(EnumType.STRING)
    private FreightBillStatus paymentStatus;

    @Column(name = "notes")
    private String notes;

    @Column(name = "created_at")
    private LocalDateTime createdAt;

    @Column(name = "updated_at")
    private LocalDateTime updatedAt;

    public FreightBill() {
    }

    public FreightBill(BigDecimal initialValue, BigDecimal remainingValue,
                       BigDecimal truckExpensesTotal, BigDecimal tripExpensesTotal, BigDecimal driverPaymentValue,
                       BigDecimal companyRevenue, FreightBillStatus paymentStatus, String notes, LocalDateTime createdAt,
                       LocalDateTime updatedAt, Trip trip) {
        this.initialValue = initialValue;
        this.remainingValue = remainingValue;
        this.truckExpensesTotal = truckExpensesTotal;
        this.tripExpensesTotal = tripExpensesTotal;
        this.driverPaymentValue = driverPaymentValue;
        this.companyRevenue = companyRevenue;
        this.paymentStatus = paymentStatus;
        this.notes = notes;
        this.createdAt = createdAt;
        this.updatedAt = updatedAt;
        this.trip = trip;
    }


    public Integer getId() {
        return id;
    }

    public Trip getTrip() {
        return trip;
    }

    public void setTrip(Trip trip) {
        this.trip = trip;
    }

    public BigDecimal getInitialValue() {
        return initialValue;
    }

    public void setInitialValue(BigDecimal initialValue) {
        this.initialValue = initialValue;
    }

    public BigDecimal getRemainingValue() {
        return remainingValue;
    }

    public void setRemainingValue(BigDecimal remainingValue) {
        this.remainingValue = remainingValue;
    }

    public BigDecimal getTruckExpensesTotal() {
        return truckExpensesTotal;
    }

    public void setTruckExpensesTotal(BigDecimal truckExpensesTotal) {
        this.truckExpensesTotal = truckExpensesTotal;
    }

    public BigDecimal getTripExpensesTotal() {
        return tripExpensesTotal;
    }

    public void setTripExpensesTotal(BigDecimal tripExpensesTotal) {
        this.tripExpensesTotal = tripExpensesTotal;
    }

    public BigDecimal getDriverPaymentValue() {
        return driverPaymentValue;
    }

    public void setDriverPaymentValue(BigDecimal driverPaymentValue) {
        this.driverPaymentValue = driverPaymentValue;
    }

    public BigDecimal getCompanyRevenue() {
        return companyRevenue;
    }

    public void setCompanyRevenue(BigDecimal companyRevenue) {
        this.companyRevenue = companyRevenue;
    }

    public FreightBillStatus getPaymentStatus() {
        return paymentStatus;
    }

    public void setPaymentStatus(FreightBillStatus paymentStatus) {
        this.paymentStatus = paymentStatus;
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
