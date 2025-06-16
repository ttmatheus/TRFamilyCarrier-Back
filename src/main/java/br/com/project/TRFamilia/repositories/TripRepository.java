package br.com.project.TRFamilia.repositories;

import java.util.List;
import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import br.com.project.TRFamilia.models.Trip;

@Repository
public interface TripRepository extends JpaRepository<Trip, Long>{
	List<Trip> findByDriverId_Id(Long id);
	Optional<Trip> findOneByDriverId_Id(Long id);
}
