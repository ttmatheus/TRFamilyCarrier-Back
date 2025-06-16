package br.com.project.TRFamilia.repositories;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import br.com.project.TRFamilia.models.FreightBill;

@Repository
public interface FreightBillRepository extends JpaRepository<FreightBill, Integer>{
	List<FreightBill> findByTripId_Id(Long id);
}
