package tn.esprit.gestiondesformations.services;

import lombok.AllArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import tn.esprit.gestiondesformations.entity.Enseignant;
import tn.esprit.gestiondesformations.entity.EvaluationFormation;
import tn.esprit.gestiondesformations.entity.Formation;
import tn.esprit.gestiondesformations.repositories.EnseignantRepository;
import tn.esprit.gestiondesformations.repositories.EvaluationFormationRepository;
import tn.esprit.gestiondesformations.repositories.FormationRepository;

import java.io.Console;
import java.util.List;

@AllArgsConstructor
@Service
public class EvaluationFormationServiceImpl implements IEvaluationFormationService {

    private final EvaluationFormationRepository evaluationFormationRepository;
    private FormationRepository formationRepository;
    private EnseignantRepository enseignantRepository;

    @Override
    public List<EvaluationFormation> retrieveAllEvaluationFormations() {
        return evaluationFormationRepository.findAll();
    }

    @Override
    public EvaluationFormation addEvaluationFormationAndAssignToFormation(EvaluationFormation evaluationFormation, int idFormation) {
        Formation formation = formationRepository.findById(idFormation).orElse(null);
        evaluationFormation.setFormation(formation);

        return evaluationFormationRepository.save(evaluationFormation);
    }

    @Override
    public EvaluationFormation updateEvaluationFormation(EvaluationFormation evaluationFormationDetails, int idEvaluationFormation) {
        EvaluationFormation evaluationFormation = evaluationFormationRepository.findById(idEvaluationFormation)
                .orElseThrow(() -> new RuntimeException("EvaluationFormation not found with id " + idEvaluationFormation));

        evaluationFormation.setNote(evaluationFormationDetails.getNote());
        evaluationFormation.setAspectOrganisationnel(evaluationFormationDetails.getAspectOrganisationnel());
        evaluationFormation.setAspectPedagogique(evaluationFormationDetails.getAspectPedagogique());
        evaluationFormation.setAspectTechnique(evaluationFormationDetails.getAspectTechnique());

        // Optionally update formation and enseignant if they are provided in the details
        if (evaluationFormationDetails.getFormation() != null) {
            evaluationFormation.setFormation(evaluationFormationDetails.getFormation());
        }
        if (evaluationFormationDetails.getEnseignant() != null) {
            evaluationFormation.setEnseignant(evaluationFormationDetails.getEnseignant());
        }

        return evaluationFormationRepository.save(evaluationFormation);
    }

    @Override
    public EvaluationFormation retrieveEvaluationFormation(int idEvaluationFormation) {
        return evaluationFormationRepository.findById(idEvaluationFormation).orElse(null);
    }

    @Override
    public void removeEvaluationFormation(int idEvaluationFormation) {
        evaluationFormationRepository.deleteById(idEvaluationFormation);
    }

    @Override
    public String getFormationTitleByEvaluationId(int idEvaluationFormation) {
        return evaluationFormationRepository.findFormationTitleByEvaluationId(idEvaluationFormation);
    }

    @Override
    public EvaluationFormation addEvaluationFormationAndAssignToFormationAndEnseignant(EvaluationFormation evaluationFormation, int idFormation, int idEnseignant) {
        Formation formation = formationRepository.findById(idFormation).orElse(null);
        Enseignant enseignant = enseignantRepository.findById(idEnseignant).orElse(null);

        evaluationFormation.setFormation(formation);
        evaluationFormation.setEnseignant(enseignant);

        return evaluationFormationRepository.save(evaluationFormation);
    }

    @Override
    public List<EvaluationFormation> findByEnseignant_IdEnseignant(int idEnseignant) {
        return evaluationFormationRepository.findByEnseignant_IdEnseignant(idEnseignant);
    }
}
