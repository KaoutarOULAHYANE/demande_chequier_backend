package com.example.demande_chequier.web;

import com.example.demande_chequier.dao.AbonneRepository;
import com.example.demande_chequier.metier.Abonne;
import com.example.demande_chequier.metier.Demande;
import com.example.demande_chequier.metier.DemandeChequier;
import com.example.demande_chequier.metier.DemandeFilter;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.*;
import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

@RestController
@Transactional
@CrossOrigin("*")
public class ChequierRestController {
    @Autowired
    private AbonneRepository abonneRepository;

    @PostMapping(path = "/search")
    public List<DemandeChequier> searchForDemandes(@RequestBody DemandeFilter demandeFilter) {
        System.out.println(demandeFilter);
        List<DemandeChequier> filtredDemandes = new ArrayList<>();
        Abonne abonne = abonneRepository.findById(demandeFilter.getAbonne()).get();
        Collection<Demande> demandeChequiers = abonne.getDemandes();
        demandeChequiers.forEach(demande -> {
            DemandeChequier demandeChequier = (DemandeChequier) demande;
            System.out.println(demandeChequier.getDate_execution());
            System.out.println(demandeFilter.getDate_debut());
            if (demandeChequier.getStatut().equals(demandeFilter.getStatut())
                    && demandeChequier.getCompte().getId().toString().equals(demandeFilter.getCompte())
                    && (demandeChequier.getDate_execution().compareTo(demandeFilter.getDate_debut()) > 0
                    || demandeChequier.getDate_execution().compareTo(demandeFilter.getDate_debut()) == 0)
                    && (demandeChequier.getDate_execution().compareTo(demandeFilter.getDate_fin()) < 0
                    || demandeChequier.getDate_execution().compareTo(demandeFilter.getDate_fin()) == 0)
                    && (demandeChequier.getMontant_chequier().doubleValue() > demandeFilter.getMontant_min()
                    || demandeChequier.getMontant_chequier().doubleValue() == demandeFilter.getMontant_min())
                    && (demandeChequier.getMontant_chequier().doubleValue() < demandeFilter.getMontant_max())
                    || demandeChequier.getMontant_chequier().doubleValue() == demandeFilter.getMontant_max())
                filtredDemandes.add(demandeChequier);
        });
        return filtredDemandes;
    }
}
