package com.desafio.gerenciadorDeImpostos.mappers;

import com.desafio.gerenciadorDeImpostos.controllers.dto.ImpostoRequestDTO;
import com.desafio.gerenciadorDeImpostos.controllers.dto.ImpostoResponseDTO;
import com.desafio.gerenciadorDeImpostos.models.ImpostoModel;
import org.springframework.stereotype.Component;

@Component
public class ImpostoMapper {

    public ImpostoModel toEntity(ImpostoRequestDTO impostoRequestDTO) {
        return ImpostoModel.builder()
                .nome(impostoRequestDTO.getNome())
                .aliquota(impostoRequestDTO.getAliquota())
                .build();
    }

    public ImpostoResponseDTO toResponse(ImpostoModel impostoModel) {
        return ImpostoResponseDTO.builder()
                .id(imposto.getId())
                .nome(imposto.getNome())
                .aliquota(imposto.getAliquota())
                .build();
    }

}
