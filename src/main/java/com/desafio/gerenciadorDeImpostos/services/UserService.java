package com.desafio.gerenciadorDeImpostos.services;

import com.desafio.gerenciadorDeImpostos.controllers.dto.LoginResponseDTO;
import com.desafio.gerenciadorDeImpostos.controllers.dto.UserReponseDTO;
import com.desafio.gerenciadorDeImpostos.controllers.dto.UserRequestDTO;

public interface UserService {
    UserReponseDTO createUser (UserRequestDTO userRequestDTO);
    LoginResponseDTO login(LoginResponseDTO loginResponseDTO);
}
