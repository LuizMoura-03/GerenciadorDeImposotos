package com.desafio.gerenciadorDeImpostos.controllers;

import com.desafio.gerenciadorDeImpostos.controllers.dto.CalculoImpostoRequestDTO;
import com.desafio.gerenciadorDeImpostos.controllers.dto.CalculoImpostoResponseDTO;
import com.desafio.gerenciadorDeImpostos.controllers.dto.ImpostoRequestDTO;
import com.desafio.gerenciadorDeImpostos.controllers.dto.ImpostoResponseDTO;
import com.desafio.gerenciadorDeImpostos.services.ImpostoService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/impostos")
public class ImpostoController {

    private final ImpostoService impostoService;

    public ImpostoController(ImpostoService impostoService) {
        this.impostoService = impostoService;
    }

    @GetMapping("/tipos")
    public ResponseEntity<List<ImpostoResponseDTO>> getAllImpostos() {
        List<ImpostoResponseDTO> impostos = impostoService.findAll();
        return ResponseEntity.ok(impostos);
    }

    @GetMapping("/tipos/{id}")
    public ResponseEntity<ImpostoResponseDTO> getImpostoById(@PathVariable Long id) {
        ImpostoResponseDTO impostoResponse = impostoService.findById(id);
        return ResponseEntity.ok(impostoResponse);
    }

    @PostMapping("/tipos")
    @PreAuthorize("hasRole('ADMIN')")
    public ResponseEntity<ImpostoResponseDTO> addImposto(@Valid @RequestBody ImpostoRequestDTO impostoRequest) {
        ImpostoResponseDTO impostoResponse = impostoService.addImposto(impostoRequest);
        return ResponseEntity.status(HttpStatus.CREATED).body(impostoResponse);
    }

    @PostMapping("/calculo")
    @PreAuthorize("hasRole('ADMIN')")
    public ResponseEntity<CalculoImpostoResponseDTO> calcularImposto
            (@Valid @RequestBody CalculoImpostoRequestDTO calculoImpostoRequest) {
        CalculoImpostoResponseDTO calculoImpostoResponse = impostoService
                .calculoImpostoResponseDTO(calculoImpostoRequest);
        return ResponseEntity.ok(calculoImpostoResponse);
    }

    @DeleteMapping("/tipos/{id}")
    @PreAuthorize("hasRole('ADMIN')")
    public ResponseEntity<Void> deleteImposto(@PathVariable Long id) {
        impostoService.deleteImpostoById(id);
        return ResponseEntity.noContent().build();
    }

}
