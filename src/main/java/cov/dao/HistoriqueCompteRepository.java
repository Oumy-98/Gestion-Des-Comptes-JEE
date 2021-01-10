package cov.dao;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import cov.entities.HistoriqueCompte;

@Repository
public interface HistoriqueCompteRepository extends JpaRepository<HistoriqueCompte, Integer> {

//	@Query("From Historique h where Id_compte := idCompte ")
//	public List<Historique> findByIdCompte(@Param("idCompte") Integer idCompte);
	
}


