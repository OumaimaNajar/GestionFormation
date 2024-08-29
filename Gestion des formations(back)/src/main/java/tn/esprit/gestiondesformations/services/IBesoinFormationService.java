package tn.esprit.gestiondesformations.services;

import tn.esprit.gestiondesformations.entity.BesoinFormation;
import tn.esprit.gestiondesformations.entity.Formation;

import java.util.List;

public interface IBesoinFormationService {
    List<BesoinFormation> retrieveAllBesoinFormations();

    BesoinFormation  addBesoinFormation(BesoinFormation  besoinFormation);

    BesoinFormation updateBesoinFormation(BesoinFormation besoinFormation);

    BesoinFormation retrieveBesoinFormation(int idBesoinFormation);

    void removeBesoinFormation(int idBesoinFormation);
}
