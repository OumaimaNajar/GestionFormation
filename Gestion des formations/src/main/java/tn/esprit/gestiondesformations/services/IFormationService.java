package tn.esprit.gestiondesformations.services;

import tn.esprit.gestiondesformations.entity.Enseignant;
import tn.esprit.gestiondesformations.entity.Formation;

import java.text.Normalizer;
import java.util.List;
import java.util.Set;

public interface IFormationService {

    List<Formation> retrieveAllFormations();

    List<Enseignant> getAllEnseignantByIdFormation(int idFormation);

    Formation  addFormation(Formation  formation);

    Formation assignFormationToEnseignant(int idFormation, int idEnseignant);

    Formation updateFormation(Formation formation);

    Formation retrieveFormation(int idFormation);

    void removeFormation(int idFormation);

    Formation findFormationByIdFormation(int idFormation);

    public String getFormationTitleByEnseignantId(int idEnseignant);


    Set<Formation> getFormationsByEnseignantId(int idEnseignant);
}
