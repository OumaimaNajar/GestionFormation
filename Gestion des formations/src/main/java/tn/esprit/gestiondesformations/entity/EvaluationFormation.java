package tn.esprit.gestiondesformations.entity;

import com.fasterxml.jackson.annotation.JsonIgnore;
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
public class EvaluationFormation implements Serializable {
    @Id
    @GeneratedValue(strategy= GenerationType.IDENTITY)
    int idEvaluationFormation;

    @Temporal(TemporalType.TIMESTAMP)
    Date date;
    String note;

    @Enumerated(EnumType.STRING)
    private SatisfactionLevel aspectOrganisationnel;

    @Enumerated(EnumType.STRING)
    private SatisfactionLevel aspectPedagogique;

    @Enumerated(EnumType.STRING)
    private SatisfactionLevel aspectTechnique;

    @PrePersist
    protected void onCreate() {
        date = new Date();
    }


    @JsonIgnore
    @ManyToOne
    @JoinColumn(name = "formation_id", nullable = false)
    private Formation formation;


    @JsonIgnore
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "enseignant_id")
    private Enseignant enseignant;

}
