package br.com.project.TRFamilia.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import br.com.project.TRFamilia.models.Notification;

@Repository
public interface NotificationRepository extends JpaRepository<Notification, Long>{
	
}
