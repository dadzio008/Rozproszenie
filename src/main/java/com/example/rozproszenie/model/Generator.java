package com.example.rozproszenie.model;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Entity
@AllArgsConstructor
@NoArgsConstructor
public class Generator {
    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "generator_gen")
    @SequenceGenerator(name = "generator_gen", sequenceName = "generator_seq", allocationSize = 2147483647)
    @Column(name = "id", nullable = false)
    private Long id;
    private String url;
}
