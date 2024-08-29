package tn.esprit.gestiondesformations.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import tn.esprit.gestiondesformations.entity.BesoinFormation;

public interface BesoinFormationRepository extends JpaRepository<BesoinFormation, Integer> {
}
