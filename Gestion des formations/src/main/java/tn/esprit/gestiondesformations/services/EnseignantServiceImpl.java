package tn.esprit.gestiondesformations.services;

import lombok.AllArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import tn.esprit.gestiondesformations.entity.Enseignant;
import tn.esprit.gestiondesformations.entity.EvaluationParticipant;
import tn.esprit.gestiondesformations.entity.Role;
import tn.esprit.gestiondesformations.repositories.EnseignantRepository;
import tn.esprit.gestiondesformations.repositories.EvaluationParticipantRepository;

import java.util.List;
import java.util.Optional;
import java.util.Set;
import java.util.stream.Collectors;

@AllArgsConstructor
@Service
public class EnseignantServiceImpl implements IEnseignantService{
    EnseignantRepository enseignantRepository;
    EvaluationParticipantRepository evaluationParticipantRepository;

    @Autowired
    private PasswordEncoder  passwordEncoder;
    @Override
    public List<Enseignant> retrieveAllEnseignants() {
        return enseignantRepository.findAll();
    }

    @Override
    public Enseignant addEnseignant(Enseignant enseignant) {
        // Encodage du mot de passe avant de sauvegarder
        String encodedPassword = passwordEncoder.encode(enseignant.getPassword());
        enseignant.setPassword(encodedPassword);

        // Sauvegarde de l'enseignant avec le mot de passe encodé
        return enseignantRepository.save(enseignant);
    }

    @Override
    public Enseignant updateEnseignant(int idEnseignant, Enseignant enseignant) {
        Enseignant enseignant1 = enseignantRepository.findById(idEnseignant).orElse(null);
        if (enseignant1 != null) {
            enseignant1.setNom(enseignant.getNom());
            enseignant1.setPrenom(enseignant.getPrenom());
            enseignant1.setEmail(enseignant.getEmail());
            enseignant1.setIdentifiant(enseignant.getIdentifiant());

            // Encoder le mot de passe uniquement s'il est fourni
            if (enseignant.getPassword() != null && !enseignant.getPassword().isEmpty()) {
                enseignant1.setPassword(passwordEncoder.encode(enseignant.getPassword()));
            }

            enseignant1.setDepartement(enseignant.getDepartement());

            return enseignantRepository.save(enseignant1);
        }

        return null;
    }


    @Override
    public Enseignant retrieveEnseignant(int idEnseignant) {
        return enseignantRepository.findById(idEnseignant).orElse(null);
    }

    @Override
    public void removeEnseignant(int idEnseignant) {
        enseignantRepository.deleteById(idEnseignant);
    }

    @Override
    public Enseignant findByIdentifiant(String identifiant) {
        return enseignantRepository.findByIdentifiant(identifiant)
                .orElseThrow(() -> new UsernameNotFoundException("User not found"));
    }

    @Override
    public boolean checkPassword(String rawPassword, String encodedPassword) {

        return passwordEncoder.matches(rawPassword, encodedPassword);
    }

    @Override
    public List<Enseignant> getEnseignantsByFormation(int idFormation) {
        return enseignantRepository.findEnseignantsByFormationId(idFormation);
    }

    @Override
    public List<Enseignant> getEnseignantsByRole() {
        return enseignantRepository.findAllParticipants();
    }

    @Override
    public List<Enseignant> getEnseignantsWithHighEvaluations() {
        return enseignantRepository.findEnseignantsWithHighEvaluations();
    }


}
