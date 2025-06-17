package br.com.project.TRFamilia.dto;

import java.math.BigDecimal;
import java.time.LocalDate;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class CreateMaintenanceRecordDTO {
    private Long truckId;
    private String maintenanceType;
    private String description;
    private BigDecimal cost;
    private BigDecimal mileage;
    private LocalDate maintenanceDate;
    private LocalDate nextMaintenanceDate;
    private String serviceProvider;
    private String receiptUrl;
}
