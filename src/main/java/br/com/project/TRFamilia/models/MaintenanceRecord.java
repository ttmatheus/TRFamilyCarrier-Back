package br.com.project.TRFamilia.models;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.time.LocalDateTime;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.OneToOne;
import jakarta.persistence.Table;
import lombok.Getter;
import lombok.Setter;

@Entity
@Table(name = "maintenancerecords")
@Getter
@Setter
public class MaintenanceRecord {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

	@OneToOne
    @JoinColumn(name = "truck_id")
    private Truck truck;

    @Column(name = "maintenance_type")
    private String maintenanceType;

    @Column(name = "description")
    private String description;

    @Column(name = "cost")
    private BigDecimal cost;

    @Column(name = "mileage")
    private BigDecimal mileage;

    @Column(name = "maintenance_date")
    private LocalDate maintenanceDate;

    @Column(name = "next_maintenance_date")
    private LocalDate nextMaintenanceDate;

    @Column(name = "service_provider")
    private String serviceProvider;

    @Column(name = "receipt_url")
    private String receiptUrl;

    @Column(name = "created_at")
    private LocalDateTime createdAt;

    @Column(name = "updated_at")
    private LocalDateTime updatedAt;

    public MaintenanceRecord() {
    }

    public MaintenanceRecord(Long id, Truck truck, String maintenanceType, String description, BigDecimal cost,
                             BigDecimal mileage, LocalDate maintenanceDate, LocalDate nextMaintenanceDate,
                             String serviceProvider, String receiptUrl, LocalDateTime createdAt, LocalDateTime updatedAt) {
        this.id = id;
        this.truck = truck;
        this.maintenanceType = maintenanceType;
        this.description = description;
        this.cost = cost;
        this.mileage = mileage;
        this.maintenanceDate = maintenanceDate;
        this.nextMaintenanceDate = nextMaintenanceDate;
        this.serviceProvider = serviceProvider;
        this.receiptUrl = receiptUrl;
        this.createdAt = createdAt;
        this.updatedAt = updatedAt;
    }
}
