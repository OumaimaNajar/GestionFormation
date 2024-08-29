package tn.esprit.gestiondesformations.services;

import tn.esprit.gestiondesformations.entity.Competence;
import tn.esprit.gestiondesformations.entity.Formation;

import java.util.List;

public interface ICompetenceService {

    List<Competence> retrieveAllCompetences();

    Competence  addCompetence(Competence  competence);

    Competence updateCompetence(Competence competence);

    Competence retrieveCompetence(int idCompetence);

    void removeCompetence(int idCompetence);
}
