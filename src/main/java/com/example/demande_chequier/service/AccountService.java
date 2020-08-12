package com.example.demande_chequier.service;

import com.example.demande_chequier.metier.Abonne;
import com.example.demande_chequier.metier.AppRole;

public interface AccountService {
    public Abonne saveUser(Abonne user);
    public AppRole saveRole(AppRole role);
    public void addRoleToUser(String username, String roleName);
    public Abonne findUserByUsername(String username);
}
