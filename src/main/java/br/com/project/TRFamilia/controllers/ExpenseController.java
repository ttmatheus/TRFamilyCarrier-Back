package br.com.project.TRFamilia.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import br.com.project.TRFamilia.dto.CreateExpenseDTO;
import br.com.project.TRFamilia.models.Expense;
import br.com.project.TRFamilia.services.ExpenseService;

@RestController
@RequestMapping("/api/expenses")
public class ExpenseController {

    @Autowired
    private ExpenseService expenseService;

    // Criar uma nova despesa
    @PostMapping
    public ResponseEntity<Expense> createExpense(@RequestBody CreateExpenseDTO dto) {
        Expense response = expenseService.createExpense(dto);
        return ResponseEntity.status(201).body(response);
    }

    @GetMapping("/{id}")
    public ResponseEntity<Expense> getExpenseById(@PathVariable Long id) {
        return ResponseEntity.ok(expenseService.getExpenseById(id));
    }

    @PutMapping("/{id}")
    public ResponseEntity<Expense> updateExpense(
            @PathVariable Long id,
            @RequestBody CreateExpenseDTO dto) {
        Expense updated = expenseService.updateExpense(id, dto);
        return ResponseEntity.ok(updated);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<String> deleteExpense(@PathVariable Long id) {
        expenseService.deleteExpense(id);
        return ResponseEntity.ok("Despesa removida com sucesso");
    }
}
