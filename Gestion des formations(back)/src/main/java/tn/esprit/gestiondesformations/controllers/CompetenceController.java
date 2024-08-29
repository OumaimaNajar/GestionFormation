package tn.esprit.gestiondesformations.controllers;

import io.swagger.v3.oas.annotations.Operation;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;
import tn.esprit.gestiondesformations.entity.Attestation;
import tn.esprit.gestiondesformations.entity.Competence;
import tn.esprit.gestiondesformations.services.IAttestationService;
import tn.esprit.gestiondesformations.services.ICompetenceService;

import java.util.List;

@RestController
@RequiredArgsConstructor
public class CompetenceController {
    private final ICompetenceService competenceService;

    @Operation(description = "Add Competence")
    @PostMapping("/addCompetence")
    public Competence addCompetence(@RequestBody Competence competence){
        return  competenceService.addCompetence(competence);
    }

    @Operation(description = "Retrieve all Competence")
    @GetMapping("/allCompetences")
    public List<Competence> getAllCompetences(){
        return competenceService.retrieveAllCompetences();
    }

    @Operation(description = "Update Competence ")
    @PutMapping("/updateCompetence")
    public Competence updateCompetence(@RequestBody Competence competence){
        return  competenceService.updateCompetence(competence);
    }

    @Operation(description = "Retrieve Competence by Id")
    @GetMapping("/getCompetence/{id-competence}")
    public Competence getCompetenceById(@PathVariable("id-competence") int idCompetence){
        return competenceService.retrieveCompetence(idCompetence);
    }

    @Operation(description = "Remove Competence")
    @DeleteMapping("/deleteCompetence/{id-competence}")
    public void removeCompetence(@PathVariable ("id-competence") int idCompetence){
        competenceService.removeCompetence(idCompetence);
    }
}
