package com.desafio.gerenciadorDeImpostos.controllers.dto;

import lombok.AllArgsConstructor;
import lombok.Data;

import java.util.Set;

@Data
@AllArgsConstructor
public class UserReponse {
    private Long id;
    private String username;
    private Set<String> roles;
}
