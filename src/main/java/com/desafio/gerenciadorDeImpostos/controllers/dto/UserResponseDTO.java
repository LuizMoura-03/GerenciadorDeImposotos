package com.desafio.gerenciadorDeImpostos.controllers.dto;

import lombok.AllArgsConstructor;
import lombok.Data;

import java.util.Set;

@Data
@AllArgsConstructor
public class UserResponseDTO {
    private Long id;
    private String name;
    private Set<String> roles;
}
