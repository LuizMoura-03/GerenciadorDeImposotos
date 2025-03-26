package com.desafio.gerenciadorDeImpostos.controllers;

import com.desafio.gerenciadorDeImpostos.controllers.dto.CalculoImpostoRequestDTO;
import com.desafio.gerenciadorDeImpostos.controllers.dto.CalculoImpostoResponseDTO;
import com.desafio.gerenciadorDeImpostos.controllers.dto.ImpostoResponseDTO;
import com.desafio.gerenciadorDeImpostos.services.ImpostoServiceImpl;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;

import java.util.Arrays;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.mockito.Mockito.*;

public class ImpostoControllerTest {

    @Mock
    private ImpostoServiceImpl impostoServiceImpl;

    @InjectMocks
    private ImpostoController impostoController;

    @BeforeEach
    void setUp() {
        MockitoAnnotations.openMocks(this);
    }

    @Test
    void getAllImpostos_ShouldReturnListOfImpostos() {
        List<ImpostoResponseDTO> mockResponse = Arrays.asList(
                new ImpostoResponseDTO(),
                new ImpostoResponseDTO()
        );
        when(impostoServiceImpl.findAll()).thenReturn(mockResponse);

        ResponseEntity<List<ImpostoResponseDTO>> response = impostoController.getAllImpostos();

        assertNotNull(response);
        assertEquals(HttpStatus.OK, response.getStatusCode());
        assertEquals(2, response.getBody().size());
        verify(impostoServiceImpl, times(1)).findAll();
    }

    @Test
    void getImpostoById_ShouldReturnImposto() {
        Long id = 1L;
        ImpostoResponseDTO mockResponse = new ImpostoResponseDTO();
        when(impostoServiceImpl.findById(id)).thenReturn(mockResponse);

        ResponseEntity<ImpostoResponseDTO> response = impostoController.getImpostoById(id);

        assertNotNull(response);
        assertEquals(HttpStatus.OK, response.getStatusCode());
        assertEquals(mockResponse, response.getBody());
        verify(impostoServiceImpl, times(1)).findById(id);
    }

    @Test
    void calcularImposto_ShouldReturnCalculoResponse() {
        CalculoImpostoRequestDTO request = new CalculoImpostoRequestDTO();
        CalculoImpostoResponseDTO mockResponse = new CalculoImpostoResponseDTO();
        when(impostoServiceImpl.calculoImpostoResponseDTO(request)).thenReturn(mockResponse);

        ResponseEntity<CalculoImpostoResponseDTO> response = impostoController.calcularImposto(request);

        assertNotNull(response);
        assertEquals(HttpStatus.OK, response.getStatusCode());
        assertEquals(mockResponse, response.getBody());
        verify(impostoServiceImpl, times(1)).calculoImpostoResponseDTO(request);
    }

    @Test
    void deleteImposto_ShouldDeleteImposto() {
        Long id = 1L;
        doNothing().when(impostoServiceImpl).deleteImpostoById(id);

        ResponseEntity<Void> response = impostoController.deleteImposto(id);

        assertNotNull(response);
        assertEquals(HttpStatus.NO_CONTENT, response.getStatusCode());
        verify(impostoServiceImpl, times(1)).deleteImpostoById(id);
    }

}
