package tn.esprit.gestiondesformations.controllers;

import io.swagger.v3.oas.annotations.Operation;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import tn.esprit.gestiondesformations.entity.Attestation;
import tn.esprit.gestiondesformations.entity.Enseignant;
import tn.esprit.gestiondesformations.entity.Formation;
import tn.esprit.gestiondesformations.services.IAttestationService;
import tn.esprit.gestiondesformations.services.IEnseignantService;
import tn.esprit.gestiondesformations.services.IFormationService;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.util.List;

@RestController
@RequiredArgsConstructor
public class AttestationController {

    @Autowired
    private IAttestationService attestationService;

    @Autowired
    private IEnseignantService enseignantService;

    @Autowired
    private IFormationService formationService;

    // Télécharger l'attestation au format PDF pour un participant donné
    @GetMapping("/telecharger/{idAttestation}")
    public ResponseEntity<byte[]> telechargerAttestation(@PathVariable int idAttestation) throws IOException {
        Attestation attestation = attestationService.retrieveAttestation(idAttestation);
        if (attestation == null) {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }

        File pdfFile = new File(attestation.getLienPDF());
        if (!pdfFile.exists()) {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }

        FileInputStream fileInputStream = new FileInputStream(pdfFile);
        byte[] pdfBytes = fileInputStream.readAllBytes();
        fileInputStream.close();

        HttpHeaders headers = new HttpHeaders();
        headers.set(HttpHeaders.CONTENT_DISPOSITION, "attachment; filename=" + pdfFile.getName());
        headers.set(HttpHeaders.CONTENT_TYPE, "application/pdf");

        return new ResponseEntity<>(pdfBytes, headers, HttpStatus.OK);
    }



    // Générer des attestations pour une formation
    @PostMapping("/generer/{idFormation}")
    public ResponseEntity<Void> genererAttestationsPourFormation(@PathVariable int idFormation) {
        Formation formation = formationService.findFormationByIdFormation(idFormation);

        if (formation == null) {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }

        List<Enseignant> participants = enseignantService.getEnseignantsByFormation(idFormation);
        attestationService.genererAttestationsPourFormation(participants, formation);

        return new ResponseEntity<>(HttpStatus.OK);
    }



    @Operation(description = "Add Attestation")
    @PostMapping("/addAttestation")
    public Attestation addAttestation(@RequestBody Attestation attestation) {
        return attestationService.addAttestation(attestation);
    }



    @Operation(description = "Add AttestationAndAssignToFormationAndEnseignant")
    @PostMapping("/addAttestationAndAssignToFormationAndEnseignant/{idFormation}/{idEnseignant}")
    public ResponseEntity<Attestation> addAttestationAndAssignToFormationAndEnseignant(
            @RequestBody Attestation attestationRequest,
            @PathVariable int idFormation,
            @PathVariable int idEnseignant) {
        Attestation attestation = attestationService.addAttestationAndAssignToFormationAndEnseignant(
                attestationRequest.getDate(), idFormation, idEnseignant);
        return new ResponseEntity<>(attestation, HttpStatus.CREATED);
    }



    @Operation(description = "Retrieve all Attestation")
    @GetMapping("/allAttestation")
    public List<Attestation> getAllAttestations() {
        return attestationService.retrieveAllAttestations();
    }

    @Operation(description = "Update Attestation")
    @PutMapping("/updateAttestation/{idAttestation}")
    public ResponseEntity<Attestation> updateAttestation(@RequestBody Attestation attestationRequest, @PathVariable int idAttestation) {
        Attestation attestation = attestationService.updateAttestation(idAttestation, attestationRequest.getDate());
        return new ResponseEntity<>(attestation, HttpStatus.ACCEPTED);
    }

    @Operation(description = "Retrieve Attestation by Id")
    @GetMapping("/getAttestation/{id-attestation}")
    public Attestation getAttestationById(@PathVariable("id-attestation") int idAttestation) {
        return attestationService.retrieveAttestation(idAttestation);
    }

    @Operation(description = "Remove Attestation")
    @DeleteMapping("/deleteAttestation/{id-attestation}")
    public void removeAttestation(@PathVariable("id-attestation") int idAttestation) {
        attestationService.removeAttestation(idAttestation);
    }


    // Nouvelle méthode pour générer des attestations pour l'enseignant connecté
    @PostMapping("/genererPourEnseignant/{idEnseignant}")
    public ResponseEntity<Void> genererAttestationsPourEnseignant(@PathVariable int idEnseignant) {
        Enseignant enseignant = enseignantService.retrieveEnseignant(idEnseignant);

        if (enseignant == null) {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }

        List<Formation> formations = enseignant.getFormations().stream().toList(); // Obtenez les formations de l'enseignant

        // Générer des attestations pour chaque formation
        for (Formation formation : formations) {
            attestationService.genererAttestationsPourFormation(List.of(enseignant), formation);
        }

        return new ResponseEntity<>(HttpStatus.OK);
    }






}
