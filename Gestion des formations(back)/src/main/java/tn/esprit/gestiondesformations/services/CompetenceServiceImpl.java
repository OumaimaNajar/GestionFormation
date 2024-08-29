package tn.esprit.gestiondesformations.services;

import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;
import tn.esprit.gestiondesformations.entity.Competence;
import tn.esprit.gestiondesformations.repositories.CompetenceRepository;

import java.util.List;

@AllArgsConstructor
@Service
public class CompetenceServiceImpl implements ICompetenceService{
    CompetenceRepository competenceRepository;
    @Override
    public List<Competence> retrieveAllCompetences() {
        return competenceRepository.findAll();
    }

    @Override
    public Competence addCompetence(Competence competence) {
        return competenceRepository.save(competence);
    }

    @Override
    public Competence updateCompetence(Competence competence) {
        return competenceRepository.save(competence);
    }

    @Override
    public Competence retrieveCompetence(int idCompetence) {
        return competenceRepository.findById(idCompetence).orElse(null);
    }

    @Override
    public void removeCompetence(int idCompetence) {
        competenceRepository.deleteById(idCompetence);
    }
}
