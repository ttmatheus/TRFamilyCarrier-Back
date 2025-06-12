package br.com.project.TRFamilia.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface Expense extends JpaRepository<Expense, Long>{
	
}
