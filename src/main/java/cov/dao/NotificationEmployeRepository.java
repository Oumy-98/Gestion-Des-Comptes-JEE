package cov.dao;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import cov.entities.NotificationEmploye;

@Repository
public interface NotificationEmployeRepository extends JpaRepository<NotificationEmploye, Integer> {

}
