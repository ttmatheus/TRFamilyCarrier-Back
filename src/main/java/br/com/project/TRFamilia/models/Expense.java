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
@Table(name = "expenses")
@Getter
@Setter
public class Expense {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @OneToOne
    @JoinColumn(name = "trip_id", unique = true, nullable = false)
    private Trip trip;

    @Column(name = "expense_type")
    private String expenseType;

    @Column(name = "value")
    private BigDecimal value;

    @Column(name = "description")
    private String description;

    @Column(name = "expense_date")
    private LocalDate expenseDate;

    @Column(name = "receipt_url")
    private String receiptUrl;

    @Column(name = "created_at")
    private LocalDateTime createdAt;

    @Column(name = "updated_at")
    private LocalDateTime updatedAt;

    public Expense() {
    }

    public Expense(Long id, Trip trip, String expenseType, BigDecimal value, String description,
                   LocalDate expenseDate, String receiptUrl, LocalDateTime createdAt, LocalDateTime updatedAt) {
        this.id = id;
        this.trip = trip;
        this.expenseType = expenseType;
        this.value = value;
        this.description = description;
        this.expenseDate = expenseDate;
        this.receiptUrl = receiptUrl;
        this.createdAt = createdAt;
        this.updatedAt = updatedAt;
    }
}
