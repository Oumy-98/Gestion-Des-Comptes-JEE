package cov.dao;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import cov.entities.Agence;
@Repository
public interface AgenceRepository extends JpaRepository<Agence, Integer> {

}
