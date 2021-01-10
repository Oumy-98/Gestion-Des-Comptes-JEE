package cov.dao;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import cov.entities.NotificationClient;

@Repository
public interface NotificationClientRepository extends JpaRepository<NotificationClient, Integer> {

	
	
}
