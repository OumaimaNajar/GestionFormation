package tn.esprit.gestiondesformations.controllers;

import io.swagger.v3.oas.annotations.Operation;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;
import tn.esprit.gestiondesformations.entity.EvaluationFormation;
import tn.esprit.gestiondesformations.services.IEvaluationFormationService;

import java.util.List;

@RestController
@RequiredArgsConstructor
public class EvaluationFormationController {

    private final IEvaluationFormationService evaluationFormationService;

    @Operation(description = "Add Evaluation Formation")
    @PostMapping("/addEvaluationFormation/{idFormation}")
    public EvaluationFormation addEvaluationFormationAndAssignToFormation(@RequestBody EvaluationFormation evaluationFormation, @PathVariable int idFormation){
        return evaluationFormationService.addEvaluationFormationAndAssignToFormation(evaluationFormation,idFormation);
    }

    @PostMapping("/addEvaluationFormationAndAssignToFormationAndEnseignant/{idFormation}/{idEnseignant}")
    public EvaluationFormation addEvaluationFormationAndAssignToFormationAndEnseignant(
            @RequestBody EvaluationFormation evaluationFormation,
            @PathVariable int idFormation,
            @PathVariable int idEnseignant) {
        System.out.println("ID Formation: " + idFormation);
        System.out.println("ID Enseignant: " + idEnseignant);
        return evaluationFormationService.addEvaluationFormationAndAssignToFormationAndEnseignant(evaluationFormation, idFormation, idEnseignant);
    }

    @Operation(description = "Retrieve all Evaluation Formation")
    @GetMapping("/getAllEvaluationFormation")
    public List<EvaluationFormation> getAllEvaluationFormations(){
        return evaluationFormationService.retrieveAllEvaluationFormations();
    }


    @GetMapping("/findByEnseignant_IdEnseignant/{idEnseignant}")
    public List<EvaluationFormation> findByEnseignant_IdEnseignant(@PathVariable int idEnseignant){
        return evaluationFormationService.findByEnseignant_IdEnseignant(idEnseignant);
    }

    @Operation(description = "Update Evaluation Formation")
    @PutMapping("/{idEvaluationFormation}")
    public EvaluationFormation updateEvaluationFormation(@RequestBody EvaluationFormation evaluationFormationDetails, @PathVariable int idEvaluationFormation){
        return evaluationFormationService.updateEvaluationFormation(evaluationFormationDetails, idEvaluationFormation);
    }

    @Operation(description = "Retrieve Evaluation Formation by Id")
    @GetMapping("/{idEvaluationFormation}")
    public EvaluationFormation getEvaluationFormationById(@PathVariable int idEvaluationFormation){
        return evaluationFormationService.retrieveEvaluationFormation(idEvaluationFormation);
    }

    @Operation(description = "Remove Evaluation Formation")
    @DeleteMapping("/deleteEvaluationFormation/{idEvaluationFormation}")
    public void removeEvaluationFormation(@PathVariable int idEvaluationFormation){
        evaluationFormationService.removeEvaluationFormation(idEvaluationFormation);
    }



    @GetMapping("/getTitleFormationByEvaluation/{idEvaluationFormation}")
    public String getFormationTitleByEvaluationId(@PathVariable int idEvaluationFormation) {
        return evaluationFormationService.getFormationTitleByEvaluationId(idEvaluationFormation);
    }
}
