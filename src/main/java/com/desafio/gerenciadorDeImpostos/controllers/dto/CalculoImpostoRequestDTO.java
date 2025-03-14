package com.desafio.gerenciadorDeImpostos.controllers.dto;

import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Positive;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class CalculoImpostoRequestDTO {
    @NotNull(message = "O valor base para calculo do imposto é obrigatorio")
    @Positive(message = "O valor base deve ser maior que zero")
    private Double valorBase;

    @NotNull(message = "A aliquota do imposto é obrigatoria.")
    @Positive(message = "A aliquota deve ser maior que zero.")
    private Double aliquota;

    private Double valorFixoImposto;
}
