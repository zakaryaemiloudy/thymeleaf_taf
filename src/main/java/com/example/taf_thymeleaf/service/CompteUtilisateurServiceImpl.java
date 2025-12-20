package com.example.taf_thymeleaf.service;

import com.example.taf_thymeleaf.Entities.AppRole;
import com.example.taf_thymeleaf.Entities.Utilisateur;
import com.example.taf_thymeleaf.Repositories.AppRoleReposotory;
import com.example.taf_thymeleaf.Repositories.UtilisateurRepository;
import lombok.AllArgsConstructor;
import lombok.NoArgsConstructor;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@Transactional

@AllArgsConstructor

public class CompteUtilisateurServiceImpl implements CompteUtilisateurService {

    private final  UtilisateurRepository utilisateurRepository;
    private final  AppRoleReposotory appRoleRepository;
    private final  PasswordEncoder passwordEncoder;

    @Override
    public Utilisateur AddNewUser(String username, String password, String email, String confiramPassord) {
        Utilisateur utilisateur = utilisateurRepository.findUtilisateurByUsername(username);
        if (utilisateur != null) {
            throw new RuntimeException("user already exists");
        }
        if (!password.equals(confiramPassord)) {
            throw new RuntimeException("passwords don't match");
        }
        utilisateur = Utilisateur.builder()
                .username(username)
                .password(passwordEncoder.encode(password))
                .email(email)
                .build();

        return utilisateurRepository.save(utilisateur);
    }

    @Override
    public AppRole AddNewRole(String role) {
        AppRole appRole = appRoleRepository.findById(role).orElse(null);

        if (appRole != null) {
            throw new RuntimeException("role already exists");
        }
        appRole = AppRole.builder()
                .role(role)
                .build();
        return appRoleRepository.save(appRole);
    }

    @Override
    public void AddRoleToUser(String username, String role) {
        Utilisateur utilisateur = utilisateurRepository.findUtilisateurByUsername(username);
        AppRole appRole = appRoleRepository.findById(role).get();
        utilisateur.getRoles().add(appRole);


    }

    @Override
    public void RemoveRoleFromUser(String username, String role) {
        Utilisateur utilisateur = utilisateurRepository.findUtilisateurByUsername(username);
        AppRole appRole = appRoleRepository.findById(role).get();
        utilisateur.getRoles().remove(appRole);

    }

    @Override
    public Utilisateur loadUserByUsername(String username) {

        return utilisateurRepository.findUtilisateurByUsername(username);
    }
}