package tn.esprit.gestiondesformations.services;

import tn.esprit.gestiondesformations.entity.Attestation;
import tn.esprit.gestiondesformations.entity.Certificat;
import tn.esprit.gestiondesformations.entity.Enseignant;
import tn.esprit.gestiondesformations.entity.Formation;

import java.util.Date;
import java.util.List;

public interface ICertificatService {

    public Certificat createCertificat(Certificat certificat);

    public List<Certificat> getAllCertificats();

    public Certificat getCertificatById(int id);

    Certificat addCertificatAndAssignToFormationAndEnseignant(Date date, int idFormation, int idEnseignant);

    Certificat updateCertificat(int idCertificat, Date dateEmission);

    Certificat retrieveCertificat(int idCertificat);

    void removeCertificat(int idCertificat);
    void genererCertificatPourFormation(List<Enseignant> participants, Formation formation);

    Certificat genererCertificat(Enseignant enseignant, Formation formation);

}
