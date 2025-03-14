package com.desafio.gerenciadorDeImpostos.controllers.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class ImpostoResponseDTO {
    private Long id;
    private String name;
    private String descricao;
    private double aliquota;
    private Double valorFixoImposto;
}
