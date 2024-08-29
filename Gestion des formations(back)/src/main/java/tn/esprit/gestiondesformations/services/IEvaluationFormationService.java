package tn.esprit.gestiondesformations.services;

import tn.esprit.gestiondesformations.entity.EvaluationFormation;
import tn.esprit.gestiondesformations.entity.Formation;

import java.util.List;

public interface IEvaluationFormationService {
    List<EvaluationFormation> retrieveAllEvaluationFormations();

    EvaluationFormation  addEvaluationFormationAndAssignToFormation(EvaluationFormation  evaluationFormation,int idFormation);

    EvaluationFormation updateEvaluationFormation(EvaluationFormation evaluationFormationDetails, int idEvaluationFormation);

    EvaluationFormation retrieveEvaluationFormation(int idEvaluationFormation);

    void removeEvaluationFormation(int idEvaluationFormation);


    String getFormationTitleByEvaluationId(int idEvaluation);

    EvaluationFormation  addEvaluationFormationAndAssignToFormationAndEnseignant(EvaluationFormation  evaluationFormation,int idFormation,int idEnseignant);


    List<EvaluationFormation> findByEnseignant_IdEnseignant(int idEnseignant);

}
