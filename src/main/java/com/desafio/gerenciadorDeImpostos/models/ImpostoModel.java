package com.desafio.gerenciadorDeImpostos.models;

import jakarta.persistence.*;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Positive;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
@Data
@NoArgsConstructor
@Table(name = "imposto")
@AllArgsConstructor
@Builder
public class ImpostoModel {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @NotBlank(message = "O nome do imposto é obrigatório.")
    @Column(nullable = false, unique = true)
    private String name;

    @NotBlank(message = "A descrição do imposto é obrigatória.")
    @Column(nullable = false)
    private String descricao;

    @NotNull(message = "A alíquota do imposto é obrigatória.")
    @Column(nullable = false)
    private Double aliquota;

    @Positive(message = "O valor fixo do imposto deve ser maior que zero.")
    @Column(name = "valor_fixo_imposto")
    private Double valorFixoImposto;
}
