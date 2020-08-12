package com.example.demande_chequier.service;

import com.example.demande_chequier.dao.AbonneRepository;
import com.example.demande_chequier.dao.CompteRepository;
import com.example.demande_chequier.dao.DemandeChequierRepository;
import com.example.demande_chequier.metier.Abonne;
import com.example.demande_chequier.metier.Compte;
import com.example.demande_chequier.metier.DemandeChequier;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.math.BigDecimal;
import java.util.*;
import java.util.stream.Stream;

@Service
@Transactional
public class DemandeInitServiceImpl implements IDemandeInitService {

    @Autowired
    private AbonneRepository abonneRepository;

    @Autowired
    private CompteRepository compteRepository;

    @Autowired
    private DemandeChequierRepository demandeChequierRepository;

    @Override
    public void initAbonnes() {
        Stream.of(new String[]{"Kaoutar", "OULAHYANE"}, new String[]{"Laila", "SABAR"})
                .forEach(elem -> {
                    Abonne abonne = new Abonne();
                    abonne.setNom(elem[1]);
                    abonne.setPrenom(elem[0]);
                    abonne.setUsername(abonne.getNom() + "-" + abonne.getPrenom());
                    abonne.setPassword("1234");

                    abonneRepository.save(abonne);
                });
    }

    @Override
    public void initComptes() {
        abonneRepository.findAll().forEach(abonne -> {
            int nbrComptes = 3 + (int) (Math.random() * 5);
            for (int i = 0; i < nbrComptes; i++) {
                Compte compte = new Compte();
                compte.setAbonne(abonne);
                int nbr = 1000 + new Random().nextInt(999);
                compte.setNumero_compte(abonne.getPrenom().subSequence(0, 3).toString().toUpperCase() + nbr);
                compteRepository.save(compte);
            }
        });
    }

    @Override
    public void initDemandeChequiers() {
        abonneRepository.findAll().forEach(abonne -> {
            int nbrDemande = 5 + (int) (Math.random() * 7);
            System.out.println("Abonne " + abonne.getId() + " : " + nbrDemande + " demandes");
            for (int i = 0; i < nbrDemande; i++) {
                DemandeChequier demandeChequier = new DemandeChequier();
                demandeChequier.setAbonne(abonne);
                demandeChequier.setDate_creation(new Date());
                demandeChequier.setDate_execution(new Date());
                demandeChequier.setMotif("MTF");
                String[] status = new String[]{"Enregistré", "Confirmé et signé"};
                int randStatus = new Random().nextInt(status.length);
                demandeChequier.setStatut(status[randStatus]);
                BigDecimal montant = new BigDecimal(1000.0 + new Random().nextDouble() * 999.0);
                List<Compte> comptes = new ArrayList<>(abonne.getComptes());
                int randIndex = (int) (Math.random() * (comptes.size()));
                demandeChequier.setCompte(comptes.get(randIndex));
                demandeChequier.setMontant_chequier(montant);
                demandeChequierRepository.save(demandeChequier);
            }
        });
    }
}
