package tn.esprit.gestiondesformations.entity;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.io.Serializable;
import java.util.Date;
import java.util.Set;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Entity
public class BesoinFormation implements Serializable {
    @Id
    @GeneratedValue(strategy= GenerationType.IDENTITY)
    int idBesoinFormation;
    Date dateDebut;
    Date dateFin;

    @ManyToMany
    @JoinTable(
            name = "besoinformation_proprietecompetence",
            joinColumns = @JoinColumn(name = "besoinformation_id"),
            inverseJoinColumns = @JoinColumn(name = "proprietecompetence_id"))
    private Set<ProprieteCompetence> proprieteCompetences;


    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "formation_id")
    private Formation formation;
}
