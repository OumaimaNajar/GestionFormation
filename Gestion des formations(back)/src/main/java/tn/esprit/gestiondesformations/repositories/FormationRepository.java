package tn.esprit.gestiondesformations.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import tn.esprit.gestiondesformations.entity.Enseignant;
import tn.esprit.gestiondesformations.entity.Formation;

import java.text.Normalizer;
import java.util.Set;

public interface FormationRepository extends JpaRepository<Formation, Integer> {

    @Query("SELECT f.titre FROM Formation f JOIN f.enseignants e WHERE e.idEnseignant = :idEnseignant")
    String findFormationTitleByEnseignantId(int idEnseignant);





}
