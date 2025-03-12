package com.desafio.gerenciadorDeImpostos.services;

import com.desafio.gerenciadorDeImpostos.controllers.dto.LoginRequestDTO;
import com.desafio.gerenciadorDeImpostos.controllers.dto.LoginResponseDTO;
import com.desafio.gerenciadorDeImpostos.controllers.dto.UserResponseDTO;
import com.desafio.gerenciadorDeImpostos.controllers.dto.UserRequestDTO;

public interface UserService {
    UserResponseDTO createUser (UserRequestDTO userRequestDTO);
    LoginResponseDTO login(LoginRequestDTO loginRequestDTO);
}
