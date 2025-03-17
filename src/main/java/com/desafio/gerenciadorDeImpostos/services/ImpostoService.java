package com.desafio.gerenciadorDeImpostos.services;

import com.desafio.gerenciadorDeImpostos.controllers.dto.CalculoImpostoRequestDTO;
import com.desafio.gerenciadorDeImpostos.controllers.dto.CalculoImpostoResponseDTO;
import com.desafio.gerenciadorDeImpostos.controllers.dto.ImpostoRequestDTO;
import com.desafio.gerenciadorDeImpostos.controllers.dto.ImpostoResponseDTO;
import jakarta.validation.Valid;

import java.util.List;

public interface ImpostoService {
    List<ImpostoResponseDTO> findAll();

    ImpostoResponseDTO findById(Long id);

    ImpostoResponseDTO addImposto(ImpostoRequestDTO impostoRequestDTO);

    CalculoImpostoResponseDTO calcularImposto(CalculoImpostoRequestDTO calcularImpostoRequestDTO);

    void deleteImpostoById(Long id);

    CalculoImpostoResponseDTO calculoImpostoResponseDTO(@Valid CalculoImpostoRequestDTO calculoImpostoRequest);
}
