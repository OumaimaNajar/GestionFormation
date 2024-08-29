package tn.esprit.gestiondesformations.services;

import tn.esprit.gestiondesformations.entity.Formation;
import tn.esprit.gestiondesformations.entity.ProprieteCompetence;

import java.util.List;

public interface IProprieteCompetenceService {
    List<ProprieteCompetence> retrieveAllProprieteCompetences();

    ProprieteCompetence  addProprieteCompetence(ProprieteCompetence  proprieteCompetence);

    ProprieteCompetence updateProprieteCompetence(ProprieteCompetence proprieteCompetence);

    ProprieteCompetence retrieveProprieteCompetence(int idProprieteCompetence);

    void removeProprieteCompetence(int idProprieteCompetence);
}
