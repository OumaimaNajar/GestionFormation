package tn.esprit.gestiondesformations.entity;

import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.persistence.*;
import jakarta.validation.constraints.NotBlank;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.io.Serializable;
import java.util.List;
import java.util.Map;
import java.util.Objects;
import java.util.Set;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Entity
public class Enseignant implements Serializable {
    @Id
    @GeneratedValue(strategy= GenerationType.IDENTITY)
    int idEnseignant;
    String nom;
    String prenom;
    String departement ;
    String identifiant; // Utilisé comme nom d'utilisateur
    String email;
    String password ;

    @Enumerated(EnumType.STRING)
    Role role;



    @OneToMany
    private List<Competence> competenceList;

    @JsonIgnore
    @ManyToMany(mappedBy = "enseignants")
    Set<Formation> formations;

    @JsonIgnore
    @OneToMany(mappedBy = "enseignant", cascade = CascadeType.ALL, fetch = FetchType.LAZY)
    private Set<EvaluationFormation> evaluationFormations;


    @OneToMany(mappedBy = "enseignant", cascade = CascadeType.ALL, fetch = FetchType.LAZY)
    private Set<EvaluationParticipant> evaluationParticipants;


}
