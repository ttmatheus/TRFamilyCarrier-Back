package br.com.project.TRFamilia.repositories;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import br.com.project.TRFamilia.models.Driver;

@Repository
public interface DriverRepository extends JpaRepository<Driver, Long>{
	Optional<Driver> findByUser_id(Long id);
}
