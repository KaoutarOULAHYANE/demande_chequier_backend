package com.example.demande_chequier.dao;

import com.example.demande_chequier.metier.Abonne;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.rest.core.annotation.RepositoryRestResource;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.CrossOrigin;

@RepositoryRestResource
@CrossOrigin("*")

public interface AbonneRepository extends JpaRepository<Abonne,Long> {
    public Abonne findByUsername(String username);
}
