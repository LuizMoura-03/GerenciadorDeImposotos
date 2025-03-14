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
public class ImpostoResponseDTO {

    private Long id;

    @NotBlank(message = "O nome do imposto é obrigatorio.")
    private String name;

    @NotBlank(message = "A descrição do imposto é obrigatoria.")
    private String descricao;

    @Positive(message = "A aliquota do imposto deve ser maior que zero.")
    @NotNull(message = "A aliquota do imposto é obrigatoria.")
    private double aliquota;

    @Positive(message = "O valor fixo do imposto deve ser maior que zero.")
    private Double valorFixoImposto;
}
