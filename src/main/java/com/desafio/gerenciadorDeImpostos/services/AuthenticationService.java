package com.desafio.gerenciadorDeImpostos.services;

import com.desafio.gerenciadorDeImpostos.controllers.dto.LoginRequestDTO;
import com.desafio.gerenciadorDeImpostos.controllers.dto.LoginResponseDTO;

public interface AuthenticationService {
    LoginResponseDTO authenticate(LoginRequestDTO loginRequestDTO);

}
