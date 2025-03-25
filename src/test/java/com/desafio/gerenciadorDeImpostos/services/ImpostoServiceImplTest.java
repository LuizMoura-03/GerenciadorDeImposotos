package com.desafio.gerenciadorDeImpostos.services;

import com.desafio.gerenciadorDeImpostos.controllers.dto.CalculoImpostoRequestDTO;
import com.desafio.gerenciadorDeImpostos.controllers.dto.CalculoImpostoResponseDTO;
import com.desafio.gerenciadorDeImpostos.controllers.dto.ImpostoRequestDTO;
import com.desafio.gerenciadorDeImpostos.controllers.dto.ImpostoResponseDTO;
import com.desafio.gerenciadorDeImpostos.exception.ImpostoNaoEncontradoException;
import com.desafio.gerenciadorDeImpostos.exception.NomeImpostoDuplicadoException;
import com.desafio.gerenciadorDeImpostos.mappers.ImpostoMapper;
import com.desafio.gerenciadorDeImpostos.models.ImpostoModel;
import com.desafio.gerenciadorDeImpostos.repositories.ImpostoRepository;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import java.util.Collections;
import java.util.List;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

public class ImpostoServiceImplTest {

    @Mock
    private ImpostoRepository impostoRepository;

    @Mock
    private ImpostoMapper impostoMapper;

    @InjectMocks
    private ImpostoServiceImpl impostoServiceImpl;

    @BeforeEach
    void setUp() {
        MockitoAnnotations.openMocks(this);
    }

    @Test
    void testFindById_ImpostoNaoEncontrado() {
        when(impostoRepository.findById(1L)).thenReturn(Optional.empty());

        ImpostoNaoEncontradoException exception = assertThrows(ImpostoNaoEncontradoException.class, () -> {
            impostoServiceImpl.findById(1L);
        });

        assertEquals("Imposto não encontrado", exception.getMessage());
        verify(impostoRepository, times(1)).findById(1L);
    }

    @Test
    void testAddImposto_NomeDuplicado() {
        ImpostoRequestDTO impostoRequestDTO = new ImpostoRequestDTO();
        impostoRequestDTO.setName("Imposto Duplicado");

        when(impostoRepository.existsByName("Imposto Duplicado")).thenReturn(true);

        NomeImpostoDuplicadoException exception = assertThrows(NomeImpostoDuplicadoException.class, () -> {
            impostoServiceImpl.addImposto(impostoRequestDTO);
        });

        assertEquals("Imposto já cadastrado no sistema", exception.getMessage());
        verify(impostoRepository, times(1)).existsByName("Imposto Duplicado");
    }

    @Test
    void testCalcularValorImposto_AlíquotaInvalida() {
        CalculoImpostoRequestDTO requestDTO = new CalculoImpostoRequestDTO();
        requestDTO.setImpostoId(1L);
        requestDTO.setValorBase(100.0);

        ImpostoModel impostoModel = new ImpostoModel();
        impostoModel.setAliquota(0.0); // Alíquota invalid
        impostoModel.setName("Imposto Teste");

        when(impostoRepository.findById(1L)).thenReturn(Optional.of(impostoModel));

        IllegalArgumentException exception = assertThrows(IllegalArgumentException.class, () -> {
            impostoServiceImpl.calculoImpostoResponseDTO(requestDTO);
        });

        assertEquals("A alíquota deve ser maior que zero.", exception.getMessage());
        verify(impostoRepository, times(1)).findById(1L);
    }

    @Test
    void testDeleteImpostoById_ImpostoNaoEncontrado() {
        when(impostoRepository.existsById(1L)).thenReturn(false);

        ImpostoNaoEncontradoException exception = assertThrows(ImpostoNaoEncontradoException.class, () -> {
            impostoServiceImpl.deleteImpostoById(1L);
        });

        assertEquals("Imposto não encontrado", exception.getMessage());
        verify(impostoRepository, times(1)).existsById(1L);
    }

    @Test
    void testAddImposto() {
        ImpostoRequestDTO impostoRequestDTO = new ImpostoRequestDTO();
        impostoRequestDTO.setName("Imposto Teste");

        ImpostoModel impostoModel = new ImpostoModel();
        impostoModel.setName("Imposto Teste");

        ImpostoResponseDTO impostoResponseDTO = new ImpostoResponseDTO();
        impostoResponseDTO.setName("Imposto Teste");

        when(impostoRepository.existsByName("Imposto Teste")).thenReturn(false);
        when(impostoMapper.toEntity(impostoRequestDTO)).thenReturn(impostoModel);
        when(impostoRepository.save(impostoModel)).thenReturn(impostoModel);
        when(impostoMapper.toResponse(impostoModel)).thenReturn(impostoResponseDTO);

        ImpostoResponseDTO result = impostoServiceImpl.addImposto(impostoRequestDTO);

        assertNotNull(result);
        assertEquals("Imposto Teste", result.getName());
        verify(impostoRepository, times(1)).existsByName("Imposto Teste");
        verify(impostoRepository, times(1)).save(impostoModel);
    }


    @Test
    void testFindAll() {
        ImpostoModel impostoModel = new ImpostoModel();
        impostoModel.setId(1L);
        impostoModel.setName("Imposto Teste");

        ImpostoResponseDTO impostoResponseDTO = new ImpostoResponseDTO();
        impostoResponseDTO.setName("Imposto Teste");

        when(impostoRepository.findAll()).thenReturn(Collections.singletonList(impostoModel));
        when(impostoMapper.toResponse(impostoModel)).thenReturn(impostoResponseDTO);

        List<ImpostoResponseDTO> result = impostoServiceImpl.findAll();

        assertNotNull(result);
        assertEquals(1, result.size());
        assertEquals("Imposto Teste", result.get(0).getName());
        verify(impostoRepository, times(1)).findAll();
        verify(impostoMapper, times(1)).toResponse(impostoModel);

    }

    @Test
    void testCalcularImposto() {
        CalculoImpostoRequestDTO requestDTO = new CalculoImpostoRequestDTO();
        requestDTO.setImpostoId(1L);
        requestDTO.setValorBase(100.0);

        ImpostoModel impostoModel = new ImpostoModel();
        impostoModel.setName("Imposto Teste");
        impostoModel.setAliquota(10.0);

        when(impostoRepository.findById(1L)).thenReturn(Optional.of(impostoModel));

        CalculoImpostoResponseDTO result = impostoServiceImpl.calcularImposto(requestDTO);

        assertNotNull(result);
        assertEquals("Imposto Teste", result.getNomeImposto());
        assertEquals(10.0, result.getValorTotalImposto());
        verify(impostoRepository, times(1)).findById(1L);

    }


    @Test
    void testDeleteImpostoById_Sucesso() {
        when(impostoRepository.existsById(1L)).thenReturn(true);

        impostoServiceImpl.deleteImpostoById(1L);

        verify(impostoRepository, times(1)).existsById(1L);
    }

}
