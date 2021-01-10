package cov.dao;

import org.springframework.data.jpa.repository.JpaRepository;

import cov.entities.HistoriqueDemande;

public interface HistoriqueDemandeRepository extends JpaRepository<HistoriqueDemande, Integer> {

	
}
