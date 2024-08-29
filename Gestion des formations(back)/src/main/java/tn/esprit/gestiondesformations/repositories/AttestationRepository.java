package tn.esprit.gestiondesformations.repositories;

import jdk.jfr.Registered;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;
import tn.esprit.gestiondesformations.entity.Attestation;

import java.util.List;

@Repository
public interface AttestationRepository extends JpaRepository<Attestation, Integer> {





}
