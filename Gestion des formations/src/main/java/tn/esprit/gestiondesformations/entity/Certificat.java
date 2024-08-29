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
public class Certificat implements Serializable {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int idCertificat; // Identifiant du certificat

    @JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "yyyy-MM-dd")
    private Date dateEmission; // Date d'émission du certificat

    @Column(nullable = false)
    private String lienPDF; // Lien vers le fichier PDF du certificat




    @ManyToOne
    @JoinColumn(name = "id_formation", nullable = false)
    private Formation formation; // La formation associée au certificat


    @ManyToOne
    @JoinColumn(name = "id_enseignant", nullable = false)
    private Enseignant enseignant; // L'enseignant associé au certificat
}
