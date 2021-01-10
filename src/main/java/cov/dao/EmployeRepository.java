package cov.dao;

import java.util.List;
import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import cov.entities.Employe;
import cov.entities.NotificationEmploye;
import cov.security.UsersComptes;

@Repository
public interface EmployeRepository extends JpaRepository<Employe, Integer> {
	
	public  Optional<Employe> findByUserCompte(UsersComptes user);

	@Query("Select e.lesNotifications From Employe e Where e.idEmploye = :id")
	public List<NotificationEmploye> findLesNotifications(@Param("id") Integer idEmploye );
	
}
