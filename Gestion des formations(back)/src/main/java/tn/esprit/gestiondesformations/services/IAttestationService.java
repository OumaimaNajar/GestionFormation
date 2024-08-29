package tn.esprit.gestiondesformations.services;

import tn.esprit.gestiondesformations.entity.Attestation;
import tn.esprit.gestiondesformations.entity.Enseignant;
import tn.esprit.gestiondesformations.entity.Formation;

import java.util.Date;
import java.util.List;

public interface IAttestationService {

    List<Attestation> retrieveAllAttestations();

    Attestation  addAttestation(Attestation  attestation);
    Attestation addAttestationAndAssignToFormationAndEnseignant(Date date,int idFormation, int idEnseignant);

    Attestation updateAttestation(int idAttestation,Date date);

    Attestation retrieveAttestation(int idAttestation);

    void removeAttestation(int idAttestation);

    Attestation genererAttestation(Enseignant enseignant, Formation formation);

    void genererAttestationsPourFormation(List<Enseignant> participants, Formation formation);


    Attestation generateAttestation(Date date, String lienPDF, int idFormation, int idEnseignant);

    void genererAttestationsPourEnseignant(Enseignant enseignant);

}
