package com.desafio.gerenciadorDeImpostos.controllers.dto;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Positive;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class CalculoImpostoResponseDTO {

    @NotBlank(message = "O nome do imposto é obrigatorio.")
    private String nomeImposto;

    @NotNull(message = "O valor base é obrigatorio.")
    @Positive(message = "O valor base deve ser maior que zero.")
    private Double valorBase;

    @NotNull(message = "O valor total do imposto é obrigatorio.")
    @Positive(message = "O valor total do imposto deve ser maior que zero.")
    private Double aliquota;

    private Double valorFixoImposto;
    private Double valorTotalImposto;

}
