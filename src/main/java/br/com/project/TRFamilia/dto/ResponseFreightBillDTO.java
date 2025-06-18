package br.com.project.TRFamilia.dto;

import java.math.BigDecimal;
import java.time.LocalDateTime;

import br.com.project.TRFamilia.enums.FreightBillStatus;
import br.com.project.TRFamilia.models.FreightBill;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class ResponseFreightBillDTO {
    
    private Integer id;
    private Long tripId;
    private BigDecimal companyRevenue;
    private BigDecimal initialValue;
    private BigDecimal remainingValue;
    private BigDecimal truckExpensesTotal;
    private BigDecimal driverPaymentValue;
    private FreightBillStatus paymentStatus;
    private String notes;
    private LocalDateTime createdAt;
    private BigDecimal tripExpensesTotal;

    public ResponseFreightBillDTO() {}

    public ResponseFreightBillDTO(FreightBill freightBill) {
        this.id = freightBill.getId();
        this.tripId = freightBill.getTrip().getId();
        this.companyRevenue = freightBill.getCompanyRevenue();
        this.initialValue = freightBill.getInitialValue();
        this.remainingValue = freightBill.getRemainingValue();
        this.truckExpensesTotal = freightBill.getTruckExpensesTotal();
        this.driverPaymentValue = freightBill.getDriverPaymentValue();
        this.companyRevenue = freightBill.getCompanyRevenue();
        this.paymentStatus = freightBill.getPaymentStatus();
        this.notes = freightBill.getNotes();
        this.createdAt = freightBill.getCreatedAt();
        this.tripExpensesTotal = freightBill.getTripExpensesTotal();
    }
}