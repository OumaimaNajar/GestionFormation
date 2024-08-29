package tn.esprit.gestiondesformations.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import tn.esprit.gestiondesformations.entity.Enseignant;
import tn.esprit.gestiondesformations.entity.EvaluationFormation;
import tn.esprit.gestiondesformations.entity.EvaluationParticipant;

import java.util.List;
import java.util.Map;
import java.util.Optional;

public interface EvaluationParticipantRepository extends JpaRepository<EvaluationParticipant, Integer> {

    @Query("SELECT e.enseignant.nom AS nom, e.enseignant.prenom AS prenom " +
            "FROM EvaluationParticipant e " +
            "WHERE e.idEvaluationParticipant = :idEvaluationParticipant")
    Map<String, Object> findEnseignantDetailsById(@Param("idEvaluationParticipant") int idEvaluationParticipant);



    @Query("SELECT f.titre FROM EvaluationParticipant ep JOIN ep.formation f WHERE ep.idEvaluationParticipant = :idEvaluationParticipant")
    String findFormationTitleByEvaluationId(@Param("idEvaluationParticipant") int idEvaluationParticipant);



    //@Query("SELECT new map(e.enseignant.nom as nom, e.enseignant.prenom as prenom, f.titre as titreFormation) " +
   //         "FROM EvaluationParticipant e " +
   //         "JOIN e.formation f " +
  //          "WHERE e.idEvaluationParticipant = :idEvaluationParticipant")
  //  Map<String, Object> findEnseignantAndFormationById(@Param("idEvaluationParticipant") int idEvaluationParticipant);



    @Query("SELECT new map(e.idEvaluationParticipant as idEvaluationParticipant, e.enseignant.nom as nom, e.enseignant.prenom as prenom, f.titre as titreFormation) " +
            "FROM EvaluationParticipant e " +
            "JOIN e.formation f " +
            "WHERE e.idEvaluationParticipant = :idEvaluationParticipant")
    Map<String, Object> findEnseignantAndFormationById(@Param("idEvaluationParticipant") int idEvaluationParticipant);



    // Méthode pour récupérer les évaluations par ID d'enseignant
    List<EvaluationParticipant> findEvaluationParticipantByEnseignant_IdEnseignant(int idEnseignant);



    @Query("SELECT e.idEnseignant AS idEnseignant, e.nom AS nom, e.prenom AS prenom, e.email AS email, e.identifiant AS identifiant, ep.evaluation AS evaluation " +
            "FROM Enseignant e JOIN e.evaluationParticipants ep ON e.idEnseignant = ep.enseignant.idEnseignant " +
            "WHERE e.role = 'PARTICIPANT' AND CAST(ep.evaluation AS integer) >= 15")
    List<Map<String, Object>> findEnseignantsWithLowScoresAndEvaluations();





}
