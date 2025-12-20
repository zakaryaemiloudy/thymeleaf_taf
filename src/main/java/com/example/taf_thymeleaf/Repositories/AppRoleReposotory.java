package com.example.taf_thymeleaf.Repositories;

import com.example.taf_thymeleaf.Entities.AppRole;
import org.springframework.data.jpa.repository.JpaRepository;

public interface AppRoleReposotory extends JpaRepository<AppRole,String> {
}