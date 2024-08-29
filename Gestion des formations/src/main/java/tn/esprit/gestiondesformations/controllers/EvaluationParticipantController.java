package tn.esprit.gestiondesformations.controllers;

import io.swagger.v3.oas.annotations.Operation;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import tn.esprit.gestiondesformations.entity.Enseignant;
import tn.esprit.gestiondesformations.entity.EvaluationFormation;
import tn.esprit.gestiondesformations.entity.EvaluationParticipant;
import tn.esprit.gestiondesformations.services.IEnseignantService;
import tn.esprit.gestiondesformations.services.IEvaluationFormationService;
import tn.esprit.gestiondesformations.services.IEvaluationParticipantService;

import java.util.List;
import java.util.Map;

@RestController
@RequiredArgsConstructor
public class EvaluationParticipantController {
    private final IEvaluationParticipantService evaluationParticipantService;


    @Operation(description = "Add Evaluation Participant")
    @PostMapping("/addEvaluationParticipant")
    public EvaluationParticipant addEvaluationParticipant(@RequestBody EvaluationParticipant evaluationParticipant){
        return  evaluationParticipantService.addEvaluationParticipant(evaluationParticipant);
    }

    @PostMapping("/assignEvaluationToEnseignantAndFormation/{idFormation}/{idEnseignant}")
    public EvaluationParticipant assignEvaluationToEnseignantAndFormation(@RequestBody EvaluationParticipant evaluationParticipant,
                                                                          @PathVariable int idFormation,
                                                                          @PathVariable int idEnseignant){
        return evaluationParticipantService.assignEvaluationToEnseignantAndFormation(evaluationParticipant,idFormation,idEnseignant);

    }

    @GetMapping("/enseignant-nom-prenom/{idEvaluationParticipant}")
    public Map<String, Object> getEnseignantDetails(@PathVariable("idEvaluationParticipant") int idEvaluationParticipant) {
        return evaluationParticipantService.getEnseignantDetailsById(idEvaluationParticipant);
    }


    @GetMapping("/getTitleFormationByEvaluationParticipant/{idEvaluationParticipant}")
    public String getFormationTitleByEvaluationId(@PathVariable int idEvaluationParticipant) {
        return evaluationParticipantService.getFormationTitleByEvaluationId(idEvaluationParticipant);
    }



    @Operation(description = "Retrieve all Evaluation Participant")
    @GetMapping("/allEvaluationParticipant")
    public List<EvaluationParticipant> getAllEvaluationParticipants(){
        return evaluationParticipantService.retrieveAllEvaluationParticipants();
    }


    @Operation(description = "Update Evaluation Participant ")
    @PutMapping("/updateEvaluationParticipant/{idEvaluationParticipant}")
    public EvaluationParticipant updateEvaluationParticipant(@RequestBody EvaluationParticipant evaluationParticipantDetails,@PathVariable int idEvaluationParticipant){
        return  evaluationParticipantService.updateEvaluationParticipant(evaluationParticipantDetails,idEvaluationParticipant);
    }



    @Operation(description = "Retrieve Evaluation Participant by Id")
    @GetMapping("/getEvaluationParticipant/{id-EvaluationParticipant}")
    public EvaluationParticipant getEvaluationParticipantById(@PathVariable("id-EvaluationParticipant") int idEvaluationParticipant){
        return evaluationParticipantService.retrieveEvaluationParticipant(idEvaluationParticipant);
    }

    @Operation(description = "Remove Evaluation Participant")
    @DeleteMapping("/deleteEvaluationParticipant/{idEvaluationParticipant}")
    public void removeEvaluationParticipant(@PathVariable int idEvaluationParticipant){
        evaluationParticipantService.removeEvaluationParticipant(idEvaluationParticipant);
    }


    @GetMapping("/evaluationDetails/{idEvaluationParticipant}")
    public ResponseEntity<Map<String, Object>> getEvaluationDetails(@PathVariable("idEvaluationParticipant") int idEvaluationParticipant) {
        Map<String, Object> details = evaluationParticipantService.getEnseignantAndFormationDetails(idEvaluationParticipant);
        return ResponseEntity.ok(details);
    }


    @GetMapping("/findEvaluationParticipantByEnseignant_IdEnseignant/{idEnseignant}")
    public List<EvaluationParticipant> findEvaluationParticipantByEnseignant_IdEnseignant(@PathVariable int idEnseignant){
        return evaluationParticipantService.findEvaluationParticipantByEnseignant_IdEnseignant(idEnseignant);
    }


    @GetMapping("/enseignants-low-scores")
    public ResponseEntity<List<Map<String, Object>>> getEnseignantsWithLowScoresAndEvaluations() {
        List<Map<String, Object>> enseignants = evaluationParticipantService.getEnseignantsWithLowScoresAndEvaluations();
        return ResponseEntity.ok(enseignants);
    }





}
