package com.desafio.gerenciadorDeImpostos.services;

import com.desafio.gerenciadorDeImpostos.controllers.dto.CalculoImpostoRequestDTO;
import com.desafio.gerenciadorDeImpostos.controllers.dto.CalculoImpostoResponseDTO;
import com.desafio.gerenciadorDeImpostos.controllers.dto.ImpostoRequestDTO;
import com.desafio.gerenciadorDeImpostos.controllers.dto.ImpostoResponseDTO;
import com.desafio.gerenciadorDeImpostos.exception.ImpostoNaoEncontradoException;
import com.desafio.gerenciadorDeImpostos.mappers.ImpostoMapper;
import com.desafio.gerenciadorDeImpostos.models.ImpostoModel;
import com.desafio.gerenciadorDeImpostos.repositories.ImpostoRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class ImpostoServiceImpl {

    private final ImpostoRepository impostoRepository;
    private final ImpostoMapper impostoMapper;

    public List<ImpostoResponseDTO> findAll() {
        return impostoRepository.findAll()
                .stream()
                .map(impostoMapper::toResponse)
                .toList();
    }

    public ImpostoResponseDTO findById(Long id) {
        ImpostoModel impostoModel = findImpostoByIdOrThrow(id);
        return impostoMapper.toResponse(impostoModel);
    }


    public ImpostoResponseDTO addImposto(ImpostoRequestDTO impostoRequest) {
        validateDuplicateImpostoName(impostoRequest.getName());
        ImpostoModel impostoModel = impostoMapper.toEntity(impostoRequest);
        ImpostoModel savedImposto = impostoRepository.save(impostoModel);
        return impostoMapper.toResponse(savedImposto);
    }

    public CalculoImpostoResponseDTO calculoImpostoResponseDTO(CalculoImpostoRequestDTO calcularImpostoRequest) {
        ImpostoModel impostoModel = findImpostoByIdOrThrow(calcularImpostoRequest.getImpostoId());
        double valorImposto;
        valorImposto = calcularValorImposto(impostoModel.getAliquota(), calcularImpostoRequest.getValorBase());
        return CalculoImpostoResponseDTO.builder()
                .nomeImposto(impostoModel.getName())
                .valorBase(calcularImpostoRequest.getValorBase())
                .aliquota(impostoModel.getAliquota())
                .valorTotalImposto(valorImposto)
                .build();
    }


    public void deleteImpostoById(Long id) {
        if (!impostoRepository.existsById(id)) {
            throw new ImpostoNaoEncontradoException("Imposto não encontrado");
        }
        impostoRepository.deleteById(id);
    }

    private ImpostoModel findImpostoByIdOrThrow(Long id) {
        return impostoRepository.findById(id)
                .orElseThrow(() -> new ImpostoNaoEncontradoException("Imposto não encontrado"));
    }

    private double calcularValorImposto(double aliquota, double baseCalculo) {
        if (aliquota <= 0) {
            throw new IllegalArgumentException("A alíquota deve ser maior que zero.");
        }
        return baseCalculo * aliquota / 100.0;
    }

    private void validateDuplicateImpostoName(String name) {
        if (impostoRepository.existsByNome(name)) {
            throw new NomeImpostoDuplicadoException("Imposto já cadastrado no sistema");
        }
    }
}
