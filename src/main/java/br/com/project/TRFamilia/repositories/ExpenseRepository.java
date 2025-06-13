package br.com.project.TRFamilia.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import br.com.project.TRFamilia.models.Expense;

@Repository
public interface ExpenseRepository extends JpaRepository<Expense, Long>{
	
}
