package com.example.taf_thymeleaf.Entities;

import jakarta.persistence.*;
import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.Size;
import lombok.*;

@AllArgsConstructor @NoArgsConstructor @Getter
@Setter @Builder @ToString
@Entity
@Table(name = "CLIENTS")
public class Client {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Size(min = 3, max = 20, message = "nom doit etre 3<nom>20")
    private String name;
    @Size(min = 4, max = 20, message = "prenom doit etre 4<prenom>20")
    private String lastName;
    @Min(18)
    private int age;


}
