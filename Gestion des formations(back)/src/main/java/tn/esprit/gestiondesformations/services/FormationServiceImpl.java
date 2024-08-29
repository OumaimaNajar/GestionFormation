package tn.esprit.gestiondesformations.services;

import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;
import tn.esprit.gestiondesformations.entity.Enseignant;
import tn.esprit.gestiondesformations.entity.Formation;
import tn.esprit.gestiondesformations.repositories.EnseignantRepository;
import tn.esprit.gestiondesformations.repositories.FormationRepository;

import java.io.Console;
import java.util.*;
import java.util.stream.Collectors;

@AllArgsConstructor
@Service
public class FormationServiceImpl implements IFormationService{
    private FormationRepository formationRepository ;
    private EnseignantRepository enseignantRepository;
    @Override
    public List<Formation> retrieveAllFormations() {
        return formationRepository.findAll();
    }

    @Override
    public List<Enseignant> getAllEnseignantByIdFormation(int idFormation) {
        Formation formation = formationRepository.findById(idFormation)
                .orElse(null);
        return new ArrayList<>(formation.getEnseignants());

    }

    @Override
    public Formation addFormation(Formation formation) {

        Set<Enseignant> enseignantSet = new HashSet<>();
        formation.setEnseignants(enseignantSet);

        return formationRepository.save(formation);
    }

    @Override
    public Formation assignFormationToEnseignant(int idFormation, int idEnseignant) {
        Formation formation = formationRepository.findById(idFormation).orElse(null);
        Enseignant enseignant = enseignantRepository.findById(idEnseignant).orElse(null);
        try{
            formation.getEnseignants().add(enseignant);
        }catch (NullPointerException exception){
            Set<Enseignant> enseignantSet = new HashSet<>();
            enseignantSet.add(enseignant);
            formation.setEnseignants(enseignantSet);
        }

        return formationRepository.save(formation);
    }


    @Override
    public Formation updateFormation(Formation formation) {
        return formationRepository.save(formation);
    }

    @Override
    public Formation retrieveFormation(int idFormation) {
        Optional<Formation> formationOptional = formationRepository.findById(idFormation);
        return formationOptional.orElse(null);
    }

    @Override
    public void removeFormation(int idFormation) {
        formationRepository.deleteById(idFormation);
    }

    @Override
    public Formation findFormationByIdFormation(int idFormation) {
        Formation formation = formationRepository.findById(idFormation).orElse(null);
        return formation;
    }

    @Override
    public String getFormationTitleByEnseignantId(int idEnseignant) {
        return formationRepository.findFormationTitleByEnseignantId(idEnseignant);
    }

    @Override
    public Set<Formation> getFormationsByEnseignantId(int idEnseignant) {
        Enseignant enseignant = enseignantRepository.findById(idEnseignant).orElse(null);
        if (enseignant != null) {
            return enseignant.getFormations();
        }
        return null;
    }


}
