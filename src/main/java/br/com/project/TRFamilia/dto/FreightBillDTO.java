package br.com.project.TRFamilia.dto;

import java.math.BigDecimal;

import br.com.project.TRFamilia.enums.FreightBillStatus;
import br.com.project.TRFamilia.models.FreightBill;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class FreightBillDTO {
    
    private Long id;
    private BigDecimal initialValue;
    private BigDecimal remainingValue;
    private BigDecimal truckExpensesTotal;
    private BigDecimal tripExpensesTotal;
    private BigDecimal driverPaymentValue;
    private BigDecimal companyRevenue;
    private FreightBillStatus paymentStatus;
    private String notes;

    public FreightBillDTO() {}

    public FreightBillDTO(FreightBill freightBill) {
        this.initialValue = freightBill.getInitialValue();
        this.remainingValue = freightBill.getRemainingValue();
        this.truckExpensesTotal = freightBill.getTruckExpensesTotal();
        this.tripExpensesTotal = freightBill.getTripExpensesTotal();
        this.driverPaymentValue = freightBill.getDriverPaymentValue();
        this.companyRevenue = freightBill.getCompanyRevenue();
        this.paymentStatus = freightBill.getPaymentStatus();
        this.notes = freightBill.getNotes();
    }
}