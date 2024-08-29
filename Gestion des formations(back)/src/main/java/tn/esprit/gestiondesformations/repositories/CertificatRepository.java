package tn.esprit.gestiondesformations.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import tn.esprit.gestiondesformations.entity.Certificat;

@Repository
public interface CertificatRepository extends JpaRepository<Certificat, Integer> {
}
