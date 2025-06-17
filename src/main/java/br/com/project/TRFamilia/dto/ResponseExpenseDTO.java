package br.com.project.TRFamilia.dto;

import java.math.BigDecimal;

import br.com.project.TRFamilia.models.Expense;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class ResponseExpenseDTO {
	private Long id;
	private BigDecimal value;
	private String expense_type;
	private String expense_date;

	public ResponseExpenseDTO() {}

	public ResponseExpenseDTO(Expense expense) {
		this.id = expense.getId();
		this.value = expense.getValue();
		this.expense_type = expense.getExpenseType();
		this.expense_date = expense.getExpenseDate().toString();
	}
}
