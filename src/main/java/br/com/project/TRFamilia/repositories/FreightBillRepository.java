package br.com.project.TRFamilia.repositories;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import br.com.project.TRFamilia.models.FreightBill;
import br.com.project.TRFamilia.models.Trip;

@Repository
public interface FreightBillRepository extends JpaRepository<FreightBill, Long> {
    boolean existsByTrip(Trip trip);
	List<FreightBill> findByTripId_Id(Long id);
}
