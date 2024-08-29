package tn.esprit.gestiondesformations.services;

import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;
import tn.esprit.gestiondesformations.entity.Enseignant;
import tn.esprit.gestiondesformations.entity.EvaluationParticipant;
import tn.esprit.gestiondesformations.entity.Formation;
import tn.esprit.gestiondesformations.repositories.EnseignantRepository;
import tn.esprit.gestiondesformations.repositories.EvaluationParticipantRepository;
import tn.esprit.gestiondesformations.repositories.FormationRepository;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Set;
import java.util.stream.Collectors;

@AllArgsConstructor
@Service
public class EvaluationParticipantServiceImpl implements IEvaluationParticipantService{
    EvaluationParticipantRepository evaluationParticipantRepository;
    EnseignantRepository enseignantRepository;
    FormationRepository formationRepository;

    @Override
    public List<EvaluationParticipant> retrieveAllEvaluationParticipants() {
        return evaluationParticipantRepository.findAll();
    }

    @Override
    public EvaluationParticipant addEvaluationParticipant(EvaluationParticipant evaluationParticipant) {
        return evaluationParticipantRepository.save(evaluationParticipant);
    }

    @Override
    public EvaluationParticipant assignEvaluationToEnseignantAndFormation(EvaluationParticipant evaluationParticipant, int idFormation, int idEnseignant) {
        Enseignant enseignant = enseignantRepository.findById(idEnseignant).orElseThrow(() -> new RuntimeException("Enseignant non trouvé"));
        Formation formation = formationRepository.findById(idFormation).orElseThrow(() -> new RuntimeException("Formation non trouvée"));

        evaluationParticipant.setEnseignant(enseignant);
        evaluationParticipant.setFormation(formation);

        return evaluationParticipantRepository.save(evaluationParticipant);
    }

    @Override
    public EvaluationParticipant updateEvaluationParticipant(EvaluationParticipant evaluationParticipantDetails, int idEvaluationParticipant) {
        EvaluationParticipant evaluationParticipant = evaluationParticipantRepository.findById(idEvaluationParticipant).orElse(null);
        evaluationParticipant.setDate(evaluationParticipantDetails.getDate());
        evaluationParticipant.setEvaluation(evaluationParticipantDetails.getEvaluation());

        return evaluationParticipantRepository.save(evaluationParticipant);
    }

    @Override
    public EvaluationParticipant retrieveEvaluationParticipant(int idEvaluationParticipant) {
        return evaluationParticipantRepository.findById(idEvaluationParticipant).orElse(null);
    }

    @Override
    public void removeEvaluationParticipant(int idEvaluationParticipant) {
        evaluationParticipantRepository.deleteById(idEvaluationParticipant);
    }

    @Override
    public Map<String, Object> getEnseignantDetailsById(int idEvaluationParticipant) {
        return evaluationParticipantRepository.findEnseignantDetailsById(idEvaluationParticipant);

    }

    @Override
    public String getFormationTitleByEvaluationId(int idEvaluationParticipant) {
        return evaluationParticipantRepository.findFormationTitleByEvaluationId(idEvaluationParticipant);

    }

    @Override
    public Map<String, Object> getEnseignantAndFormationDetails(int idEvaluationParticipant) {
        return evaluationParticipantRepository.findEnseignantAndFormationById(idEvaluationParticipant);
    }

    @Override
    public List<EvaluationParticipant> findEvaluationParticipantByEnseignant_IdEnseignant(int idEnseignant) {
        return evaluationParticipantRepository.findEvaluationParticipantByEnseignant_IdEnseignant(idEnseignant);
    }



    @Override
    public Map<Integer, List<EvaluationParticipant>> getEvaluationsByParticipant() {

            // Récupérer toutes les évaluations
            List<EvaluationParticipant> evaluations = evaluationParticipantRepository.findAll();

            // Grouper les évaluations par ID d'enseignant
            return evaluations.stream()
                    .collect(Collectors.groupingBy(e -> e.getEnseignant().getIdEnseignant()));
        }

    @Override
    public List<Map<String, Object>> getEnseignantsWithLowScoresAndEvaluations() {
        List<Map<String, Object>> result = evaluationParticipantRepository.findEnseignantsWithLowScoresAndEvaluations();
        System.out.println("Résultat de la requête : " + result);
        return result;
    }


}
