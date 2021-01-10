package cov.dao;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import cov.entities.HistoriqueCredit;

@Repository
public interface HistoriqueCreditRepository extends JpaRepository<HistoriqueCredit, Integer> {
	
	
}
