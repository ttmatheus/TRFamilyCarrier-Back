package br.com.project.TRFamilia.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import br.com.project.TRFamilia.models.FreightBill;

@Repository
public interface FreightBillRepository extends JpaRepository<FreightBill, Long>{
	
}
