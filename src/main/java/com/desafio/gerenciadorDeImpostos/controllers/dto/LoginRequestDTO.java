package com.desafio.gerenciadorDeImpostos.controllers.dto;

import jakarta.validation.constraints.NotNull;
import lombok.Data;

@Data
public class LoginRequestDTO {
    @NotNull
    private String name;

    @NotNull
    private String password;

}
