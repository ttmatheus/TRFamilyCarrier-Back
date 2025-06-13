package br.com.project.TRFamilia.dto;

import java.math.BigDecimal;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;

public class CreateFreightBillDTO {

	@NotNull
    private BigDecimal initialValue;

	@NotNull
    private BigDecimal remainingValue;

	@NotNull
    private BigDecimal truckExpensesTotal;

	@NotNull
    private BigDecimal tripExpensesTotal;

	@NotNull
    private BigDecimal driverPaymentValue;

	@NotNull
    private BigDecimal companyRevenue;

    @NotNull
    private Long tripId;

	@NotBlank
    private String paymentStatus;

	@NotBlank
    private String notes;

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

    public String getPaymentStatus() {
        return paymentStatus;
    }

    public void setPaymentStatus(String paymentStatus) {
        this.paymentStatus = paymentStatus;
    }

    public String getNotes() {
        return notes;
    }

    public void setNotes(String notes) {
        this.notes = notes;
    }

    public Long getTripId() {
        return tripId;
    }

    public void setTripId(Long tripId) {
        this.tripId = tripId;
    }
}
