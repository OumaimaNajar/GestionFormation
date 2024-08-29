package tn.esprit.gestiondesformations.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import tn.esprit.gestiondesformations.entity.EvaluationFormation;

import java.util.List;

public interface EvaluationFormationRepository extends JpaRepository<EvaluationFormation, Integer> {
    @Query("SELECT f.titre FROM EvaluationFormation ef JOIN ef.formation f WHERE ef.idEvaluationFormation = :idEvaluationFormation")
    String findFormationTitleByEvaluationId(@Param("idEvaluationFormation") int idEvaluationFormation);


    // Méthode pour récupérer les évaluations par ID d'enseignant
    List<EvaluationFormation> findByEnseignant_IdEnseignant(int idEnseignant);


}
