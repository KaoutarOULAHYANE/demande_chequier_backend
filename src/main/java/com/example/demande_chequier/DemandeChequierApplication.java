package com.example.demande_chequier;

import com.example.demande_chequier.metier.*;
import com.example.demande_chequier.service.AccountService;
import com.example.demande_chequier.service.IDemandeInitService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;
import org.springframework.data.rest.core.config.RepositoryRestConfiguration;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;

@SpringBootApplication
public class DemandeChequierApplication implements CommandLineRunner {

    @Autowired
    private IDemandeInitService iDemandeInitService;

    @Autowired
    private RepositoryRestConfiguration configuration;

    /*@Autowired
    private AccountService accountService;*/

    public static void main(String[] args) {
        SpringApplication.run(DemandeChequierApplication.class, args);
    }

    @Bean
    public BCryptPasswordEncoder getBCPE() {
        return new BCryptPasswordEncoder();
    }

    @Override
    public void run(String... args) throws Exception {
        System.out.println("Demande chequier ...");
        configuration.exposeIdsFor(DemandeChequier.class);
        configuration.exposeIdsFor(Compte.class);
        configuration.exposeIdsFor(Abonne.class);

        /*accountService.saveUser(new Abonne(null,"Laila","Sabar","laila", "1234"));
        accountService.saveUser(new Abonne(null,"Kaoutar","Oulahyane","kaoutar", "1234"));
        accountService.saveRole(new AppRole(null, "USER"));
        accountService.addRoleToUser("laila", "USER");
        accountService.addRoleToUser("kaoutar", "USER");*/


        iDemandeInitService.initAbonnes();
        iDemandeInitService.initComptes();
        iDemandeInitService.initDemandeChequiers();
    }

}
