package tn.esprit.gestiondesformations.controllers;

import io.swagger.v3.oas.annotations.Operation;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.server.ResponseStatusException;
import tn.esprit.gestiondesformations.entity.Enseignant;
import tn.esprit.gestiondesformations.entity.Role;
import tn.esprit.gestiondesformations.entity.Status;
import tn.esprit.gestiondesformations.repositories.EnseignantRepository;
import tn.esprit.gestiondesformations.services.IEnseignantService;

import java.util.*;

@RestController
@RequiredArgsConstructor
public class EnseignantController {
    private final IEnseignantService enseignantService;

    @Autowired
    private EnseignantRepository enseignantRepository ;

    @Autowired
    private PasswordEncoder passwordEncoder;

    @PostMapping("/login")
    public ResponseEntity<Map<String, Object>> login(@RequestBody Enseignant enseignant) {
        try {
            Enseignant foundEnseignant = enseignantService.findByIdentifiant(enseignant.getIdentifiant());
            if (passwordEncoder.matches(enseignant.getPassword(), foundEnseignant.getPassword())) {
                Map<String, Object> response = new HashMap<>();
                response.put("status", "SUCCESS");
                response.put("role", foundEnseignant.getRole().name());
                response.put("nom", foundEnseignant.getNom()); // Ajouter le nom
                response.put("prenom", foundEnseignant.getPrenom()); // Ajouter le prénom
                response.put("idEnseignant", foundEnseignant.getIdEnseignant()); // Ajouter l'ID de l'enseignant
                return ResponseEntity.ok(response);
            } else {
                return ResponseEntity.status(HttpStatus.UNAUTHORIZED).body(Collections.singletonMap("status", "FAILURE"));
            }
        } catch (UsernameNotFoundException e) {
            return ResponseEntity.status(HttpStatus.UNAUTHORIZED).body(Collections.singletonMap("status", "FAILURE"));
        }
    }



    @GetMapping("/login/{identifiant}/{password}")
    public Status findByIdentifiant(@PathVariable String identifiant, @PathVariable String password) {
        Enseignant enseignant = enseignantRepository.findByIdentifiant(identifiant)
                .orElseThrow(() -> new UsernameNotFoundException("User not found"));

        if (passwordEncoder.matches(password, enseignant.getPassword())) {
            // Gérer la connexion ici
            return Status.SUCCESS;
        }
        return Status.FAILURE;
    }


    @GetMapping("/getEnseignantsByFormation/{idFormation}")
    public List<Enseignant> getEnseignantsByFormation(@PathVariable int idFormation){
        return enseignantService.getEnseignantsByFormation(idFormation);
    }


    @GetMapping("/getEnseignantsByRole")
    public List<Enseignant> getEnseignantsByRole(){
        return enseignantService.getEnseignantsByRole();
    }



    @Operation(description = "Add Enseignant")
    @PostMapping("/addEnseignant")
    public Enseignant addEnseignant(@RequestBody Enseignant enseignant){
        return  enseignantService.addEnseignant(enseignant);
    }

    @Operation(description = "Retrieve all Enseignant")
    @GetMapping("/allEnseignant")
    public List<Enseignant> getAllEnseignants(){
        List<Enseignant> enseignants = enseignantService.retrieveAllEnseignants();
        if (enseignants == null || enseignants.isEmpty()) {
            throw new ResponseStatusException(HttpStatus.NOT_FOUND, "Aucun enseignant trouvé");
        }
        return enseignants;
    }

    @Operation(description = "Update Enseignant ")
    @PutMapping("/updateEnseignant/{idEnseignant}")
    public ResponseEntity<Enseignant> updateEnseignant(
            @PathVariable int idEnseignant,
            @RequestBody Enseignant enseignant) {
        Enseignant enseignant1 = enseignantService.updateEnseignant(idEnseignant, enseignant);
        if (enseignant != null) {
            return ResponseEntity.ok(enseignant1);
        } else {
            return ResponseEntity.notFound().build();
        }
    }

    @Operation(description = "Retrieve Enseignant by Id")
    @GetMapping("/getEnseignant/{id-enseignant}")
    public Enseignant getEnseignantById(@PathVariable("id-enseignant") int idEnseignant){
        return enseignantService.retrieveEnseignant(idEnseignant);
    }

    @Operation(description = "Remove Enseignant")
    @DeleteMapping("/deleteEnseignant/{id-enseignant}")
    public void removeEnseignant(@PathVariable ("id-enseignant") int idEnseignant){
        enseignantService.removeEnseignant(idEnseignant);
    }

    @GetMapping("/getEnseignantsWithHighEvaluations")
    public List<Enseignant> getEnseignantsWithHighEvaluations(){
        return enseignantService.getEnseignantsWithHighEvaluations();
    }


}
