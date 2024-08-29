package tn.esprit.gestiondesformations.controllers;

import io.swagger.v3.oas.annotations.Operation;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;
import tn.esprit.gestiondesformations.entity.Competence;
import tn.esprit.gestiondesformations.entity.ProprieteCompetence;
import tn.esprit.gestiondesformations.services.ICompetenceService;
import tn.esprit.gestiondesformations.services.IProprieteCompetenceService;

import java.util.List;

@RestController
@RequiredArgsConstructor
public class ProprieteCompetenceController {
    private final IProprieteCompetenceService proprieteCompetenceService;

    @Operation(description = "Add ProprieteCompetence")
    @PostMapping("/addProprieteCompetence")
    public ProprieteCompetence addProprieteCompetence(@RequestBody ProprieteCompetence proprieteCompetence){
        return  proprieteCompetenceService.addProprieteCompetence(proprieteCompetence);
    }

    @Operation(description = "Retrieve all Propriete Competence")
    @GetMapping("/allProprieteCompetences")
    public List<ProprieteCompetence> getAllProprieteCompetences(){
        return proprieteCompetenceService.retrieveAllProprieteCompetences();
    }

    @Operation(description = "Update Propriete Competence ")
    @PutMapping("/updateProprieteCompetence")
    public ProprieteCompetence updateProprieteCompetence(@RequestBody ProprieteCompetence proprieteCompetence){
        return  proprieteCompetenceService.updateProprieteCompetence(proprieteCompetence);
    }

    @Operation(description = "Retrieve Propriete Competence by Id")
    @GetMapping("/getProprieteCompetence/{id-propriete-competence}")
    public ProprieteCompetence getProprieteCompetenceById(@PathVariable("id-propriete-competence") int idProprieteCompetence){
        return proprieteCompetenceService.retrieveProprieteCompetence(idProprieteCompetence);
    }

    @Operation(description = "Remove Propriete Competence")
    @DeleteMapping("/deleteProprieteCompetence/{id-propriete-competence}")
    public void removeProprieteCompetence(@PathVariable ("id-propriete-competence") int idProprieteCompetence){
        proprieteCompetenceService.removeProprieteCompetence(idProprieteCompetence);
    }
}
