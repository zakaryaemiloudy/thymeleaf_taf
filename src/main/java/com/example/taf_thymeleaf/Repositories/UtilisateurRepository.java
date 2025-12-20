package com.example.taf_thymeleaf.Repositories;

import com.example.taf_thymeleaf.Entities.Utilisateur;
import org.springframework.data.jpa.repository.JpaRepository;

public interface UtilisateurRepository  extends JpaRepository<Utilisateur,Long> {
    Utilisateur findUtilisateurByUsername(String username); }
