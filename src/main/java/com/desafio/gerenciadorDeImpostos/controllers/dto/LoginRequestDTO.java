package com.desafio.gerenciadorDeImpostos.controllers.dto;

import jakarta.validation.constraints.NotNull;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class LoginRequestDTO {
    @NotNull
    private String name;

    @NotNull
    private String password;

}
