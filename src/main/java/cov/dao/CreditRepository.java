package cov.dao;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import cov.entities.Credit;
import cov.entities.HistoriqueCredit;

@Repository
public interface CreditRepository extends JpaRepository<Credit, Integer>{

	@Query("Select c.lesOperations From Credit c Where c.idCredit = :id")
	public List<HistoriqueCredit> findOperations(@Param("id") Integer idCredit );

	
}
