package tn.esprit.gestiondesformations.services;

import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;
import tn.esprit.gestiondesformations.entity.Attestation;
import tn.esprit.gestiondesformations.entity.Enseignant;
import tn.esprit.gestiondesformations.entity.Formation;
import tn.esprit.gestiondesformations.repositories.AttestationRepository;
import tn.esprit.gestiondesformations.repositories.EnseignantRepository;
import tn.esprit.gestiondesformations.repositories.FormationRepository;

import java.util.Date;
import java.util.List;

@AllArgsConstructor
@Service
public class AttestationServiceImpl implements IAttestationService{
    AttestationRepository attestationRepository ;
    FormationRepository formationRepository;
    EnseignantRepository enseignantRepository;
    @Override
    public List<Attestation> retrieveAllAttestations() {
        return attestationRepository.findAll();
    }

    @Override
    public Attestation addAttestation(Attestation attestation) {
        return attestationRepository.save(attestation);
    }

    @Override
    public Attestation addAttestationAndAssignToFormationAndEnseignant(Date date,int idFormation, int idEnseignant) {
        Formation formation = formationRepository.findById(idFormation).orElseThrow(() -> new RuntimeException("Formation not found with id " + idFormation));
        Enseignant enseignant = enseignantRepository.findById(idEnseignant).orElseThrow(() -> new RuntimeException("Enseignant not found with id " + idEnseignant));

        Attestation attestation = new Attestation();
        attestation.setDate(date);
        attestation.setFormation(formation);
        attestation.setEnseignant(enseignant);
        return attestationRepository.save(attestation);
    }

    @Override
    public Attestation updateAttestation(int idAttestation,Date date) {
        Attestation attestation = attestationRepository.findById(idAttestation).orElse(null);
        attestation.setDate(date);
        return attestationRepository.save(attestation);
    }

    @Override
    public Attestation retrieveAttestation(int idAttestation) {
        return attestationRepository.findById(idAttestation).orElse(null);
    }

    @Override
    public void removeAttestation(int idAttestation) {
        attestationRepository.deleteById(idAttestation);
    }

    @Override
    public Attestation genererAttestation(Enseignant enseignant, Formation formation) {

        Attestation attestation = new Attestation();
        attestation.setEnseignant(enseignant);
        attestation.setFormation(formation);
        attestation.setDate(new Date());

        // Génération du lien vers le fichier PDF (remplacez ce chemin par le chemin réel où vous sauvegardez les PDF)
        String lienPDF = "/path/to/attestations/" + enseignant.getNom() + "_" + enseignant.getPrenom() + "_formation_" + formation.getTitre() + ".pdf";
        attestation.setLienPDF(lienPDF);

        // Appel à une méthode pour générer le PDF (non incluse ici)
        // generatePdf(attestation, lienPDF);

        return attestationRepository.save(attestation);
    }

    @Override
    public void genererAttestationsPourFormation(List<Enseignant> participants, Formation formation) {

        // Parcourir tous les participants et créer une attestation pour chacun
        for (Enseignant participant : participants) {
            Attestation attestation = new Attestation();
            attestation.setEnseignant(participant);
            attestation.setFormation(formation);
            attestation.setDate(new Date()); // La date actuelle

            // Sauvegarder l'attestation dans la base de données
            attestationRepository.save(attestation);
        }

    }

    @Override
    public Attestation generateAttestation(Date date, String lienPDF, int idFormation, int idEnseignant) {
        Formation formation = formationRepository.findById(idFormation).orElse(null);
        Enseignant enseignant = enseignantRepository.findById(idEnseignant).orElse(null);

        Attestation attestation = new Attestation();
        attestation.setDate(date);
        attestation.setLienPDF(lienPDF);
        attestation.setFormation(formation);
        attestation.setEnseignant(enseignant);

        return attestationRepository.save(attestation);
    }

    @Override
    public void genererAttestationsPourEnseignant(Enseignant enseignant) {
        List<Formation> formations = enseignant.getFormations().stream().toList();
        for (Formation formation : formations) {
            Attestation attestation = new Attestation();
            attestation.setDate(new Date());
            attestation.setLienPDF("chemin/vers/attestation.pdf"); // Remplacez par la logique de génération PDF
            attestation.setFormation(formation);
            attestation.setEnseignant(enseignant);
            attestationRepository.save(attestation);
        }

    }


}
