package tn.esprit.gestiondesformations.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;
import tn.esprit.gestiondesformations.entity.Enseignant;
import tn.esprit.gestiondesformations.entity.Role;

import java.util.List;
import java.util.Optional;
import java.util.Set;

@Repository
public interface EnseignantRepository extends JpaRepository<Enseignant, Integer> {

    @Query("SELECT e FROM Enseignant e WHERE e.role = 'PARTICIPANT'")
    List<Enseignant> findAllParticipants();


    Optional<Enseignant> findByIdentifiant(String identifiant);

   // @Query("SELECT e FROM Enseignant e JOIN e.formations f WHERE f.idFormation = :idFormation")
   // List<Enseignant> findEnseignantsByFormationId(int idFormation);


    @Query("SELECT e FROM Enseignant e JOIN e.formations f WHERE f.idFormation = :idFormation AND e.role = 'PARTICIPANT'")
    List<Enseignant> findEnseignantsByFormationId(int idFormation);



    @Query("SELECT DISTINCT e FROM Enseignant e " +
            "JOIN e.evaluationParticipants ep " +
            "WHERE CAST(ep.evaluation AS integer) >= 15 AND e.role = 'PARTICIPANT'")
    List<Enseignant> findEnseignantsWithHighEvaluations();


}
