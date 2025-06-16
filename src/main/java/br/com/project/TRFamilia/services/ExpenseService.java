package br.com.project.TRFamilia.services;

import java.time.LocalDateTime;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;

import br.com.project.TRFamilia.dto.CreateExpenseDTO;
import br.com.project.TRFamilia.exceptions.ApiException;
import br.com.project.TRFamilia.models.Expense;
import br.com.project.TRFamilia.models.Trip;
import br.com.project.TRFamilia.repositories.ExpenseRepository;
import br.com.project.TRFamilia.repositories.TripRepository;

@Service
public class ExpenseService {

    @Autowired
    private ExpenseRepository expenseRepository;

    @Autowired
    private TripRepository tripRepository;

    public Expense createExpense(CreateExpenseDTO dto) {
        Trip trip = tripRepository.findById(dto.getTripId())
                .orElseThrow(() -> new ApiException(404, "Trip not found", HttpStatus.NOT_FOUND));

        Expense expense = new Expense();
        expense.setTrip(trip);
        expense.setExpenseType(dto.getExpenseType());
        expense.setValue(dto.getValue());
        expense.setDescription(dto.getDescription());
        expense.setExpenseDate(dto.getExpenseDate());
        expense.setReceiptUrl(dto.getReceiptUrl());
        expense.setCreatedAt(LocalDateTime.now());
        expense.setUpdatedAt(LocalDateTime.now());

        return expenseRepository.save(expense);
    }

    public Expense getExpenseById(Long id) {
        Expense expense = expenseRepository.findById(id)
                .orElseThrow(() -> new ApiException(404, "Expense not found", HttpStatus.NOT_FOUND));
        return expense;
    }

    public List<Expense> getAllExpenses() {
        List<Expense> expenses = expenseRepository.findAll();
        return expenses;
    }

    public Expense updateExpense(Long id, CreateExpenseDTO dto) {
        Expense expense = expenseRepository.findById(id)
                .orElseThrow(() -> new ApiException(404, "Expense not found", HttpStatus.NOT_FOUND));

        Trip trip = tripRepository.findById(dto.getTripId())
                .orElseThrow(() -> new ApiException(404, "Trip not found", HttpStatus.NOT_FOUND));

        expense.setTrip(trip);
        expense.setExpenseType(dto.getExpenseType());
        expense.setValue(dto.getValue());
        expense.setDescription(dto.getDescription());
        expense.setExpenseDate(dto.getExpenseDate());
        expense.setReceiptUrl(dto.getReceiptUrl());
        expense.setUpdatedAt(LocalDateTime.now());

        return expenseRepository.save(expense);
    }

    public void deleteExpense(Long id) {
        if (!expenseRepository.existsById(id)) {
            throw new ApiException(404, "Despesa não encontrada", HttpStatus.NOT_FOUND);
        }
        expenseRepository.deleteById(id);
    }
}
