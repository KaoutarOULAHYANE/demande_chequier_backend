package com.example.demande_chequier.metier;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.ToString;

import javax.persistence.*;

@Entity
@Data
@NoArgsConstructor
@AllArgsConstructor
@ToString
public class AppRole {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String roleNom;

    @ManyToOne
    @JoinColumn(name = "id_abonne")
    @JsonProperty(access = JsonProperty.Access.WRITE_ONLY)
    private Abonne abonne;
}
