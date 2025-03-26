package com.desafio.gerenciadorDeImpostos.mappers;

import com.desafio.gerenciadorDeImpostos.controllers.dto.ImpostoRequestDTO;
import com.desafio.gerenciadorDeImpostos.controllers.dto.ImpostoResponseDTO;
import com.desafio.gerenciadorDeImpostos.models.ImpostoModel;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;

public class ImpostoMapperTest {

    private ImpostoMapper impostoMapper;

    @BeforeEach
    void setUp() {
        impostoMapper = new ImpostoMapper();
    }

    @Test
    void testToEntity() {
        ImpostoRequestDTO impostoRequestDTO = new ImpostoRequestDTO();
        impostoRequestDTO.setName("Imposto Teste");
        impostoRequestDTO.setDescricao("Descrição do imposto");
        impostoRequestDTO.setAliquota(10.0);
        impostoRequestDTO.setValorFixoImposto(100.0);

        ImpostoModel impostoModel = impostoMapper.toEntity(impostoRequestDTO);

        assertNotNull(impostoModel);
        assertEquals("Imposto Teste", impostoModel.getName());
        assertEquals("Descrição do imposto", impostoModel.getDescricao());
        assertEquals(10.0, impostoModel.getAliquota());
        assertEquals(100.0, impostoModel.getValorFixoImposto());
    }

    @Test
    void testToResponse() {
        ImpostoModel impostoModel = new ImpostoModel();
        impostoModel.setId(1L);
        impostoModel.setName("Imposto Teste");
        impostoModel.setDescricao("Descrição do imposto");
        impostoModel.setAliquota(10.0);
        impostoModel.setValorFixoImposto(100.0);

        ImpostoResponseDTO impostoResponseDTO = impostoMapper.toResponse(impostoModel);

        assertNotNull(impostoResponseDTO);
        assertEquals(1L, impostoResponseDTO.getId());
        assertEquals("Imposto Teste", impostoResponseDTO.getName());
        assertEquals("Descrição do imposto", impostoResponseDTO.getDescricao());
        assertEquals(10.0, impostoResponseDTO.getAliquota());
        assertEquals(100.0, impostoResponseDTO.getValorFixoImposto());
    }

}
