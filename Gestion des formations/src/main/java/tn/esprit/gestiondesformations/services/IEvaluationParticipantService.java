package tn.esprit.gestiondesformations.services;

import tn.esprit.gestiondesformations.entity.Enseignant;
import tn.esprit.gestiondesformations.entity.EvaluationParticipant;
import tn.esprit.gestiondesformations.entity.Formation;

import java.util.List;
import java.util.Map;

public interface IEvaluationParticipantService {
    List<EvaluationParticipant> retrieveAllEvaluationParticipants();

    EvaluationParticipant  addEvaluationParticipant(EvaluationParticipant  evaluationParticipant);


    EvaluationParticipant assignEvaluationToEnseignantAndFormation(
            EvaluationParticipant evaluationParticipant,
            int idFormation,
            int idEnseignant);




    EvaluationParticipant updateEvaluationParticipant(EvaluationParticipant evaluationParticipantDetails, int idEvaluationParticipant);

    EvaluationParticipant retrieveEvaluationParticipant(int idEvaluationParticipant);

    void removeEvaluationParticipant(int idEvaluationParticipant);



    public Map<String, Object> getEnseignantDetailsById(int idEvaluationParticipant);

    String getFormationTitleByEvaluationId(int idEvaluationParticipant);


    public Map<String, Object> getEnseignantAndFormationDetails(int idEvaluationParticipant);


    public List<EvaluationParticipant> findEvaluationParticipantByEnseignant_IdEnseignant(int idEnseignant);





    public Map<Integer, List<EvaluationParticipant>> getEvaluationsByParticipant();

    public List<Map<String, Object>> getEnseignantsWithLowScoresAndEvaluations();


}
