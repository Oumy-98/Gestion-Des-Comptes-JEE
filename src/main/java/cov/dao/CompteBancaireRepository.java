package cov.dao;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import cov.entities.Compte;
import cov.entities.HistoriqueCompte;

@Repository                                          
public interface CompteBancaireRepository extends JpaRepository<Compte, Integer> {
	
	@Query("Select c.lesOperations From Compte c Where c.idCompte = :id")
	public List<HistoriqueCompte> findOperations(@Param("id") Integer idCompte );

}
