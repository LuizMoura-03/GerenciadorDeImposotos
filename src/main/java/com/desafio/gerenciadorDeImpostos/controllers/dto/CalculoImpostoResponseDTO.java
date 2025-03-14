package com.desafio.gerenciadorDeImpostos.controllers.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class CalculoImpostoResponseDTO {
    private Double valorBase;
    private Double aliquota;
    private Double valorFixoImposto;
    private Double valorTotalImposto;

}
