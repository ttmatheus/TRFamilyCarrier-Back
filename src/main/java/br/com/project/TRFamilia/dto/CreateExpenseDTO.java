package br.com.project.TRFamilia.dto;

import java.math.BigDecimal;
import java.time.LocalDate;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class CreateExpenseDTO {
    private Long tripId;
    private String expenseType;
    private BigDecimal value;
    private String description;
    private LocalDate expenseDate;
    private String receiptUrl;
}
