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
public class Competence implements Serializable {
    @Id
    @GeneratedValue(strategy= GenerationType.IDENTITY)
    int idCompetence;
    String domaine ;

    @ManyToMany(mappedBy = "competences")
    private Set<Formation> formations;

    @Enumerated(EnumType.STRING)
    @Column(name = "domaine",insertable=false, updatable=false)
    private Domaine domaines;


    @ManyToOne
    @JoinColumn(name = "enseignant_id")
    private Enseignant enseignant;
}
