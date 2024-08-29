package tn.esprit.gestiondesformations.entity;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.io.Serializable;
import java.util.Set;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Entity
public class ProprieteCompetence implements Serializable {
    @Id
    @GeneratedValue(strategy= GenerationType.IDENTITY)
    int idPropComp;

    @ManyToMany(mappedBy = "proprieteCompetences")
    private Set<BesoinFormation> besoinFormations;
}
