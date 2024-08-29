package tn.esprit.gestiondesformations.services;

import tn.esprit.gestiondesformations.entity.Enseignant;
import tn.esprit.gestiondesformations.entity.Formation;
import tn.esprit.gestiondesformations.entity.Role;

import java.util.List;
import java.util.Optional;

public interface IEnseignantService {

    List<Enseignant> retrieveAllEnseignants();

    Enseignant  addEnseignant(Enseignant  enseignant);

    Enseignant updateEnseignant(int idEnseignant, Enseignant enseignant);

    Enseignant retrieveEnseignant(int idEnseignant);

    void removeEnseignant(int idEnseignant);


    public Enseignant findByIdentifiant(String identifiant);


    public boolean checkPassword(String rawPassword, String encodedPassword);

    List<Enseignant> getEnseignantsByFormation(int idFormation);

    public List<Enseignant> getEnseignantsByRole();


    public List<Enseignant> getEnseignantsWithHighEvaluations();

}
