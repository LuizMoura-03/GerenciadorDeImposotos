package com.desafio.gerenciadorDeImpostos.models;

import jakarta.persistence.*;
import lombok.Data;

@Entity
@Data
public class ImpostoModel {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private  Long id;

    @Column(nullable = false, unique = true)
    private String name;

    @Column(nullable = false)
    private Double aliquota;
}
