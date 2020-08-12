package com.example.demande_chequier.service;

import com.example.demande_chequier.dao.AbonneRepository;
import com.example.demande_chequier.dao.RoleRepository;
import com.example.demande_chequier.metier.Abonne;
import com.example.demande_chequier.metier.AppRole;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;

public class AccountServiceImpl implements AccountService{
    @Autowired
    private BCryptPasswordEncoder bCryptPasswordEncoder;
    @Autowired
    private AbonneRepository userRepository;
    @Autowired
    private RoleRepository roleRepository;

    @Override
    public Abonne saveUser(Abonne user) {
        String hashPW=bCryptPasswordEncoder.encode(user.getPassword());
        user.setPassword(hashPW);
        return userRepository.save(user);
    }

    @Override
    public AppRole saveRole(AppRole role) {
        // TODO Auto-generated method stub
        return roleRepository.save(role);
    }

    @Override
    public void addRoleToUser(String username, String roleName) {
        AppRole role=roleRepository.findByRoleNom(roleName);
        Abonne user=userRepository.findByUsername(username);
        user.getRoles().add(role);
    }

    @Override
    public Abonne findUserByUsername(String username) {
        // TODO Auto-generated method stub
        return userRepository.findByUsername(username);
    }
}
