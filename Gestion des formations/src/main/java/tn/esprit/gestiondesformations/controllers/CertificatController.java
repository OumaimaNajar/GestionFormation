package tn.esprit.gestiondesformations.controllers;

import io.swagger.v3.oas.annotations.Operation;
import lombok.AllArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.parameters.P;
import org.springframework.web.bind.annotation.*;
import tn.esprit.gestiondesformations.entity.Attestation;
import tn.esprit.gestiondesformations.entity.Certificat;
import tn.esprit.gestiondesformations.entity.Enseignant;
import tn.esprit.gestiondesformations.entity.Formation;
import tn.esprit.gestiondesformations.services.CertificatServiceImpl;
import tn.esprit.gestiondesformations.services.IEnseignantService;
import tn.esprit.gestiondesformations.services.IFormationService;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.util.List;

@AllArgsConstructor
@RestController
public class CertificatController {
    @Autowired
    private CertificatServiceImpl certificatService;
    @Autowired
    private IFormationService formationService;
    @Autowired
    private IEnseignantService enseignantService;

    // Télécharger certificat au format PDF pour un participant donné
    @GetMapping("/telechargerCertificat/{idCertificat}")
    public ResponseEntity<byte[]> telechargerCertificat(@PathVariable int idCertificat) throws IOException {
        Certificat certificat = certificatService.getCertificatById(idCertificat);
        if (certificat == null) {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }

        File pdfFile = new File(certificat.getLienPDF());
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

    // Générer des certificats pour une formation
    @PostMapping("/genererCertificats/{idFormation}")
    public ResponseEntity<Void> genererCertificatsPourFormation(@PathVariable int idFormation) {
        Formation formation = formationService.findFormationByIdFormation(idFormation);

        if (formation == null) {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }

        List<Enseignant> participants = enseignantService.getEnseignantsByFormation(idFormation);
        certificatService.genererCertificatPourFormation(participants, formation);

        return new ResponseEntity<>(HttpStatus.OK);
    }

    @Operation(description = "Add CertificatAndAssignToFormationAndEnseignant")
    @PostMapping("/addCertificatAndAssignToFormationAndEnseignant/{idFormation}/{idEnseignant}")
    public ResponseEntity<Certificat> addCertificatAndAssignToFormationAndEnseignant(@RequestBody Certificat certificatRequest, @PathVariable int idFormation, @PathVariable int idEnseignant) {
        Certificat certificat = certificatService.addCertificatAndAssignToFormationAndEnseignant(certificatRequest.getDateEmission(), idFormation, idEnseignant);
        return new ResponseEntity<>(certificat, HttpStatus.CREATED);
    }


    @PostMapping("/addCertificat")
    public Certificat createCertificat(@RequestBody Certificat certificat) {
       return certificatService.createCertificat(certificat);
    }

    @GetMapping("/allCertificat")
    public List<Certificat> getAllCertificats() {
        return certificatService.getAllCertificats();
    }

    @PutMapping("/updateCertificat/{idCertificat}")
    public ResponseEntity<Certificat> updateCertificat(@RequestBody Certificat certificatRequest, @PathVariable int idCertificat) {
        Certificat certificat = certificatService.updateCertificat(idCertificat, certificatRequest.getDateEmission());
        return new ResponseEntity<>(certificat, HttpStatus.ACCEPTED);
    }

    @GetMapping("/retrieveCertificat/{idCertificat}")
    public Certificat retrieveCertificat(@PathVariable int idCertificat){
        return certificatService.retrieveCertificat(idCertificat);
    }

    @DeleteMapping("/removeCertificat/{idCertificat}")
    public void removeCertificat(@PathVariable int idCertificat){
        certificatService.removeCertificat(idCertificat);
    }



    @GetMapping("/{id}")
    public ResponseEntity<Certificat> getCertificatById(@PathVariable int id) {
        Certificat certificat = certificatService.getCertificatById(id);
        if (certificat == null) {
            return ResponseEntity.notFound().build();
        }
        return ResponseEntity.ok(certificat);
    }
}
