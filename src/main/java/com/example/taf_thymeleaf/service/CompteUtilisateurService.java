package com.example.taf_thymeleaf.service;

import com.example.taf_thymeleaf.Entities.AppRole;
import com.example.taf_thymeleaf.Entities.Utilisateur;

public interface CompteUtilisateurService {

    Utilisateur AddNewUser(String username, String password, String email, String confiramPassord) ;
    AppRole AddNewRole(String role);
    void AddRoleToUser(String username, String role);
    void RemoveRoleFromUser(String username, String role);
    Utilisateur loadUserByUsername(String username);

}