package com.example.demande_chequier;

import com.example.demande_chequier.metier.Abonne;
import com.example.demande_chequier.metier.Compte;
import com.example.demande_chequier.metier.Demande;
import com.example.demande_chequier.metier.DemandeChequier;
import com.example.demande_chequier.service.IDemandeInitService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.data.rest.core.config.RepositoryRestConfiguration;

@SpringBootApplication
public class DemandeChequierApplication implements CommandLineRunner {

    @Autowired
    private IDemandeInitService iDemandeInitService;

    @Autowired
    private RepositoryRestConfiguration configuration;

    public static void main(String[] args) {
        SpringApplication.run(DemandeChequierApplication.class, args);
    }

    @Override
    public void run(String... args) throws Exception {
        System.out.println("Demande chequier ...");
        configuration.exposeIdsFor(DemandeChequier.class);
        configuration.exposeIdsFor(Compte.class);
        configuration.exposeIdsFor(Abonne.class);
        iDemandeInitService.initAbonnes();
        iDemandeInitService.initComptes();
        iDemandeInitService.initDemandeChequiers();
    }

}
