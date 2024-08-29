package tn.esprit.gestiondesformations.entity;

import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.persistence.*;
import lombok.*;
import lombok.experimental.FieldDefaults;

import java.io.Serializable;
import java.util.Date;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Entity
public class Formation implements Serializable {
    @Id
    @GeneratedValue(strategy= GenerationType.IDENTITY)
    int idFormation;
    String titre;
    String description;
    Date date;
    String formateur ;
    String nbHeures;
    String publicCible ;
    String Objectifs ;

    @JsonIgnore
    @ManyToMany
    @JoinTable(
            name = "formation_enseignant",
            joinColumns = @JoinColumn(name = "formation_id"),
            inverseJoinColumns = @JoinColumn(name = "enseignant_id"))
    private Set<Enseignant> enseignants;


    @OneToMany(mappedBy = "formation", cascade = CascadeType.ALL)
    private Set<Attestation> attestations;


    @JsonIgnore
    @OneToMany(mappedBy = "formation",cascade = CascadeType.ALL)
    private List<EvaluationFormation> evaluationFormations;



    @JsonIgnore
    @OneToMany(cascade = CascadeType.ALL)
    @JoinColumn(name = "formation_id")
    private Set<EvaluationParticipant> evaluationParticipants;


    @OneToMany(mappedBy = "formation", cascade = CascadeType.ALL)
    private Set<BesoinFormation> besoinFormations;

    @ManyToMany
    @JoinTable(
            name = "formation_competence",
            joinColumns = @JoinColumn(name = "formation_id"),
            inverseJoinColumns = @JoinColumn(name = "competence_id"))
    private Set<Competence> competences;

}
