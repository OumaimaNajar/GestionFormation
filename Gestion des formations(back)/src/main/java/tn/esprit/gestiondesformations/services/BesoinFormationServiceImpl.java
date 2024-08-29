package tn.esprit.gestiondesformations.services;

import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;
import tn.esprit.gestiondesformations.entity.BesoinFormation;
import tn.esprit.gestiondesformations.repositories.BesoinFormationRepository;

import java.util.List;

@AllArgsConstructor
@Service
public class BesoinFormationServiceImpl implements IBesoinFormationService{
    BesoinFormationRepository besoinFormationRepository;
    @Override
    public List<BesoinFormation> retrieveAllBesoinFormations() {
        return besoinFormationRepository.findAll();
    }

    @Override
    public BesoinFormation addBesoinFormation(BesoinFormation besoinFormation) {
        return besoinFormationRepository.save(besoinFormation);
    }

    @Override
    public BesoinFormation updateBesoinFormation(BesoinFormation besoinFormation) {
        return besoinFormationRepository.save(besoinFormation);
    }

    @Override
    public BesoinFormation retrieveBesoinFormation(int idBesoinFormation) {
        return besoinFormationRepository.findById(idBesoinFormation).orElse(null);
    }

    @Override
    public void removeBesoinFormation(int idBesoinFormation) {
        besoinFormationRepository.deleteById(idBesoinFormation);
    }
}
