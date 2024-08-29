package tn.esprit.gestiondesformations.entity;

import com.fasterxml.jackson.annotation.JsonFormat;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.io.Serializable;
import java.util.Date;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Entity
public class Attestation implements Serializable {
    @Id
    @GeneratedValue(strategy= GenerationType.IDENTITY)
    int idAttestation;

    @Temporal(TemporalType.DATE)
    @JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "yyyy-MM-dd")
    Date date;  // Date de génération de l'attestation

    @Column(nullable = false)
    String lienPDF;  // Lien vers le fichier PDF de l'attestation

    @ManyToOne
    @JoinColumn(name = "id_formation", nullable = false)
    private Formation formation;  // La formation associée à l'attestation

    @ManyToOne
    @JoinColumn(name = "id_enseignant", nullable = false)
    private Enseignant enseignant;  // Le participant (enseignant) associé à l'attestation
}
