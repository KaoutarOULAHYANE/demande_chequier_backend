package com.example.demande_chequier.metier;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.ToString;
import org.springframework.format.annotation.DateTimeFormat;

import javax.persistence.*;
import java.io.Serializable;
import java.util.Date;

@Entity
@Inheritance(strategy = InheritanceType.TABLE_PER_CLASS)
@Data
@NoArgsConstructor
@AllArgsConstructor
@ToString
public class Demande implements Serializable {
    @Id
    @GeneratedValue(strategy = GenerationType.TABLE)
    private Long id;
    private String motif;

    @Temporal(TemporalType.DATE)
    private Date date_creation;

    @Temporal(TemporalType.DATE)
    private Date date_execution;

    private String statut;

    @ManyToOne
    @JoinColumn(name = "id_abonne")
    @JsonProperty(access = JsonProperty.Access.WRITE_ONLY)
    private Abonne abonne;

    @ManyToOne
    @JoinColumn(name = "id_compte")
    private Compte compte;
}
