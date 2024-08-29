package tn.esprit.gestiondesformations.controllers;

import io.swagger.v3.oas.annotations.Operation;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;
import tn.esprit.gestiondesformations.entity.Attestation;
import tn.esprit.gestiondesformations.entity.BesoinFormation;
import tn.esprit.gestiondesformations.services.IAttestationService;
import tn.esprit.gestiondesformations.services.IBesoinFormationService;

import java.util.List;

@RestController
@RequiredArgsConstructor
public class BesoinFormationController {
    private final IBesoinFormationService besoinFormationService;

    @Operation(description = "Add Besoin Formation")
    @PostMapping("/addBesoinFormation")
    public BesoinFormation addBesoinFormation(@RequestBody BesoinFormation besoinFormation){
        return besoinFormationService.addBesoinFormation(besoinFormation);
    }

    @Operation(description = "Retrieve all Besoin Formation")
    @GetMapping("/allBesoinFormation")
    public List<BesoinFormation> getAllBesoinFormations(){
        return besoinFormationService.retrieveAllBesoinFormations();
    }

    @Operation(description = "Update Besoin Formation ")
    @PutMapping("/updateBesoinFormation")
    public BesoinFormation updateBesoinFormation(@RequestBody BesoinFormation besoinFormation){
        return  besoinFormationService.updateBesoinFormation(besoinFormation);
    }

    @Operation(description = "Retrieve Besoin Formation by Id")
    @GetMapping("/getBesoinFormation/{id-BesoinFormation}")
    public BesoinFormation getBesoinFormationById(@PathVariable("id-BesoinFormation") int idBesoinFormation){
        return besoinFormationService.retrieveBesoinFormation(idBesoinFormation);
    }

    @Operation(description = "Remove Besoin Formation")
    @DeleteMapping("/deleteBesoinFormation/{id-BesoinFormation}")
    public void removeBesoinFormation(@PathVariable ("id-BesoinFormation") int idBesoinFormation){
        besoinFormationService.removeBesoinFormation(idBesoinFormation);
    }
}
