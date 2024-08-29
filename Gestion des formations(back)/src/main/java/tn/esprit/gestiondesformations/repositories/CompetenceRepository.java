package tn.esprit.gestiondesformations.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import tn.esprit.gestiondesformations.entity.Competence;

public interface CompetenceRepository extends JpaRepository<Competence, Integer> {
}
