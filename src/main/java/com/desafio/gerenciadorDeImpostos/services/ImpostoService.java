package com.desafio.gerenciadorDeImpostos.services;

import com.desafio.gerenciadorDeImpostos.controllers.dto.CalculoImpostoRequestDTO;
import com.desafio.gerenciadorDeImpostos.controllers.dto.ImpostoRequestDTO;
import com.desafio.gerenciadorDeImpostos.controllers.dto.ImpostoResponseDTO;

import java.util.List;

public interface ImpostoService {
    List<ImpostoResponseDTO> findAll();
    ImpostoResponseDTO findById(Long id);
    ImpostoResponseDTO addImposto(ImpostoRequestDTO impostoRequestDTO);
    CalculoImpostoRequestDTO calcularImposto(CalculoImpostoRequestDTO calcularImpostoRequestDTO);
    void deleteImpostoById(Long id);
}
