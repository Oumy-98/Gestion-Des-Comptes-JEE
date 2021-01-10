package cov.dao;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import cov.security.UsersComptes;

@Repository
public interface UsersComptesRepository extends JpaRepository<UsersComptes, Integer>{

	public Optional<UsersComptes> findByUsername(String username); 
	
//	public Optional<Client> findByClient(Clie)
	
}
