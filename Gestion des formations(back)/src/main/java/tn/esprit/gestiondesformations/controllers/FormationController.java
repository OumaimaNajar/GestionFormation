package tn.esprit.gestiondesformations.controllers;

import io.swagger.v3.oas.annotations.Operation;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import tn.esprit.gestiondesformations.entity.Attestation;
import tn.esprit.gestiondesformations.entity.Enseignant;
import tn.esprit.gestiondesformations.entity.Formation;
import tn.esprit.gestiondesformations.services.IAttestationService;
import tn.esprit.gestiondesformations.services.IFormationService;

import java.util.List;
import java.util.Set;

@RestController
@RequiredArgsConstructor
public class FormationController {
    private final IFormationService formationService;

    @Operation(description = "Add Formation")
    @PostMapping("/addFormation")
    public Formation addFormation(@RequestBody Formation formation) {
        return formationService.addFormation(formation);
    }

    @PutMapping("/assignFormationToEnseignant/{idFormation}/{idEnseignant}")
    public Formation assignFormationToEnseignant(@PathVariable int idFormation,@PathVariable int idEnseignant){
        return formationService.assignFormationToEnseignant(idFormation,idEnseignant);
    }



    @Operation(description = "Retrieve all Formation")
    @GetMapping("/allFormation")
    public List<Formation> getAllFormations(){
        return formationService.retrieveAllFormations();
    }


    @GetMapping("/getAllEnseignantByIdFormation/{idFormation}/enseignants")
    public ResponseEntity<List<Enseignant>> getAllEnseignantByIdFormation(@PathVariable int idFormation) {
        List<Enseignant> enseignants = formationService.getAllEnseignantByIdFormation(idFormation);
        return ResponseEntity.ok(enseignants);
    }





    @Operation(description = "Update Formation ")
    @PutMapping("/updateFormation")
    public Formation updateFormation(@RequestBody Formation formation){
        return  formationService.updateFormation(formation);
    }

    @Operation(description = "Retrieve Formation by Id")
    @GetMapping("/findFormationByIdFormation/{idFormation}")
    public Formation findFormationByIdFormation(@PathVariable int idFormation){
        return formationService.findFormationByIdFormation(idFormation);
    }

    @Operation(description = "Remove Formation")
    @DeleteMapping("/deleteFormation/{id-formation}")
    public void removeFormation(@PathVariable ("id-formation") int idFormation){
        formationService.removeFormation(idFormation);
    }


    @GetMapping("/getFormationTitleByEnseignantId/{idEnseignant}")
    public String getFormationTitleByEnseignantId(@PathVariable int idEnseignant){
        return formationService.getFormationTitleByEnseignantId(idEnseignant);
    }


    @GetMapping("/getFormationsByEnseignantId/{idEnseignant}")
    public ResponseEntity<Set<Formation>> getFormationsByEnseignantId(@PathVariable int idEnseignant) {
        Set<Formation> formations = formationService.getFormationsByEnseignantId(idEnseignant);
        if (formations == null) {
            return ResponseEntity.notFound().build();
        }
        return ResponseEntity.ok(formations);
    }
}
