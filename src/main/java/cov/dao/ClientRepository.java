package cov.dao;

import java.util.List;
import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import cov.entities.Client;
import cov.entities.Compte;
import cov.entities.NotificationClient;
import cov.security.UsersComptes;


public interface ClientRepository extends JpaRepository<Client, Integer>  {
	
	public Optional<Client> findByUserCompte(UsersComptes user);
	
	@Query("Select c.lesNotifications From Client c Where c.idClient = :id")
	public List<NotificationClient> findLesNotifications(@Param("id") Integer idClient );

	@Query("Select c.lesComptesBancaire From Client c Where c.idClient = :id")
	public List<Compte> findLesComptesBancaires(@Param("id") Integer idClient );
	
}
