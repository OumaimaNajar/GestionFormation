package tn.esprit.gestiondesformations.services;

import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;
import tn.esprit.gestiondesformations.entity.ProprieteCompetence;
import tn.esprit.gestiondesformations.repositories.ProprieteCompetenceRepository;

import java.util.List;

@AllArgsConstructor
@Service
public class ProprieteCompetenceServiceImpl implements IProprieteCompetenceService {
    ProprieteCompetenceRepository proprieteCompetenceRepository;
    @Override
    public List<ProprieteCompetence> retrieveAllProprieteCompetences() {
        return proprieteCompetenceRepository.findAll();
    }

    @Override
    public ProprieteCompetence addProprieteCompetence(ProprieteCompetence proprieteCompetence) {
        return proprieteCompetenceRepository.save(proprieteCompetence);
    }

    @Override
    public ProprieteCompetence updateProprieteCompetence(ProprieteCompetence proprieteCompetence) {
        return proprieteCompetenceRepository.save(proprieteCompetence);
    }

    @Override
    public ProprieteCompetence retrieveProprieteCompetence(int idProprieteCompetence) {
        return proprieteCompetenceRepository.findById(idProprieteCompetence).orElse(null);
    }

    @Override
    public void removeProprieteCompetence(int idProprieteCompetence) {
        proprieteCompetenceRepository.deleteById(idProprieteCompetence);
    }
}
