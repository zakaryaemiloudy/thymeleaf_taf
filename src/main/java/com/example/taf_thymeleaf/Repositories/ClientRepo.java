package com.example.taf_thymeleaf.Repositories;

import com.example.taf_thymeleaf.Entities.Client;
import org.springframework.data.repository.CrudRepository;

import java.util.List;

public interface ClientRepo extends CrudRepository<Client, Long> {

    public List<Client> findClientsByNameContains(String mot);


}
