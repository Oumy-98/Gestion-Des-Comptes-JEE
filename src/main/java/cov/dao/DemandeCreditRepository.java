package cov.dao;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import cov.entities.DemandeCredit;
import cov.entities.HistoriqueDemande;

@Repository
public interface DemandeCreditRepository extends JpaRepository<DemandeCredit, Integer> {

	@Query("Select d.lesAvances From DemandeCredit d Where d.id_demande = :id")
	public List<HistoriqueDemande> findAvances(@Param("id") Integer idDemande );
}
