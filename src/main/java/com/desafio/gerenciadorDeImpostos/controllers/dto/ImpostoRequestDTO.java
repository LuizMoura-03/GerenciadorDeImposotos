package com.desafio.gerenciadorDeImpostos.controllers.dto;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Positive;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class ImpostoRequestDTO {

    private Long id;

    @NotBlank(message = "O nome do imposto é obrigatorio.")
    String name;

    @NotBlank(message = "A descrição do imposto é obrigatória.")
    private String descricao;

    @Positive(message = "A liquota do imposto tem que ser maior que zero")
    @NotNull(message = "A aliquota do imposto é obrigatoria")
    private double aliquota;

    @Positive(message = "A liquota do imposto tem que ser maior que zero")
    private Double valorFixoImposto;

}
