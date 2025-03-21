package com.desafio.gerenciadorDeImpostos.controllers.dto;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import lombok.Data;

import java.util.Set;

@Data
public class UserRequestDTO {

    @NotBlank(message = "O nome de usuario é obrigatorio.")
    private String name;

    @NotBlank(message = "A senha é obrigatoria não pode ser vazia.")
    private String password;

    @NotNull(message = "O campo de roles é obrigatório.")
    private Set<String> roles;

}
