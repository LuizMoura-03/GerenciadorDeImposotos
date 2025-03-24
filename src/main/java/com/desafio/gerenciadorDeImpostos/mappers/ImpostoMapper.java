package com.desafio.gerenciadorDeImpostos.mappers;

import com.desafio.gerenciadorDeImpostos.controllers.dto.ImpostoRequestDTO;
import com.desafio.gerenciadorDeImpostos.controllers.dto.ImpostoResponseDTO;
import com.desafio.gerenciadorDeImpostos.models.ImpostoModel;
import org.springframework.stereotype.Component;

@Component
public class ImpostoMapper {

    public ImpostoModel toEntity(ImpostoRequestDTO impostoRequestDTO) {
        return ImpostoModel.builder()
                .name(impostoRequestDTO.getName())
                .descricao(impostoRequestDTO.getDescricao())
                .aliquota(impostoRequestDTO.getAliquota())
                .valorFixoImposto(impostoRequestDTO.getValorFixoImposto())
                .build();
    }

    public ImpostoResponseDTO toResponse(ImpostoModel impostoModel) {
        return ImpostoResponseDTO.builder()
                .id(impostoModel.getId())
                .name(impostoModel.getName())
                .descricao(impostoModel.getDescricao())
                .aliquota(impostoModel.getAliquota())
                .valorFixoImposto(impostoModel.getValorFixoImposto())
                .build();
    }

}
