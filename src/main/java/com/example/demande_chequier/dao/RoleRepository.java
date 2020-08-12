package com.example.demande_chequier.dao;

import com.example.demande_chequier.metier.AppRole;
import org.springframework.data.jpa.repository.JpaRepository;

public interface RoleRepository extends JpaRepository<AppRole,Long > {
    public AppRole findByRoleName(String roleName);
}
