package tn.esprit.gestiondesformations.services;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import tn.esprit.gestiondesformations.entity.Attestation;
import tn.esprit.gestiondesformations.entity.Certificat;
import tn.esprit.gestiondesformations.entity.Enseignant;
import tn.esprit.gestiondesformations.entity.Formation;
import tn.esprit.gestiondesformations.repositories.CertificatRepository;
import tn.esprit.gestiondesformations.repositories.EnseignantRepository;
import tn.esprit.gestiondesformations.repositories.FormationRepository;

import java.util.Date;
import java.util.List;

@Service
public class CertificatServiceImpl implements ICertificatService{
    @Autowired
    private CertificatRepository certificatRepository;
    @Autowired
    private FormationRepository formationRepository;
    @Autowired
    private EnseignantRepository enseignantRepository;
    @Override
    public Certificat createCertificat(Certificat certificat) {
        return certificatRepository.save(certificat);
    }

    @Override
    public List<Certificat> getAllCertificats() {
        return certificatRepository.findAll();
    }

    @Override
    public Certificat getCertificatById(int id) {
        return certificatRepository.findById(id).orElse(null);
    }

    @Override
    public Certificat addCertificatAndAssignToFormationAndEnseignant(Date date, int idFormation, int idEnseignant) {
        Formation formation = formationRepository.findById(idFormation).orElseThrow(() -> new RuntimeException("Formation not found with id " + idFormation));
        Enseignant enseignant = enseignantRepository.findById(idEnseignant).orElseThrow(() -> new RuntimeException("Enseignant not found with id " + idEnseignant));

        Certificat certificat = new Certificat();
        certificat.setDateEmission(date);
        certificat.setFormation(formation);
        certificat.setEnseignant(enseignant);
        return certificatRepository.save(certificat);
    }

    @Override
    public Certificat updateCertificat(int idCertificat, Date dateEmission) {
        Certificat certificat = certificatRepository.findById(idCertificat).orElse(null);
        certificat.setDateEmission(dateEmission);
        return certificatRepository.save(certificat);
    }

    @Override
    public Certificat retrieveCertificat(int idCertificat) {
        return certificatRepository.findById(idCertificat).orElse(null);
    }

    @Override
    public void removeCertificat(int idCertificat) {
        certificatRepository.deleteById(idCertificat);
    }



    @Override
    public void genererCertificatPourFormation(List<Enseignant> participants, Formation formation) {

        // Parcourir tous les participants et créer une attestation pour chacun
        for (Enseignant participant : participants) {
            Certificat certificat = new Certificat();
            certificat.setEnseignant(participant);
            certificat.setFormation(formation);
            certificat.setDateEmission(new Date()); // La date actuelle

            // Sauvegarder l'attestation dans la base de données
            certificatRepository.save(certificat);
        }

    }

   @Override
    public Certificat genererCertificat(Enseignant enseignant, Formation formation) {
        Certificat certificat = new Certificat();
        certificat.setEnseignant(enseignant);
        certificat.setFormation(formation);
        certificat.setDateEmission(new Date());

        // Génération du lien vers le fichier PDF (remplacez ce chemin par le chemin réel où vous sauvegardez les PDF)
        String lienPDF = "/path/to/certificats/" + enseignant.getNom() + "_" + enseignant.getPrenom() + "_formation_" + formation.getTitre() + ".pdf";
        certificat.setLienPDF(lienPDF);

        // Appel à une méthode pour générer le PDF (non incluse ici)
        // generatePdf(attestation, lienPDF);

        return certificatRepository.save(certificat);
    }
}
