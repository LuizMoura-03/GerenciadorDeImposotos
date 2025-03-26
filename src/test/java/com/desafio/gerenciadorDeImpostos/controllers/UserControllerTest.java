package com.desafio.gerenciadorDeImpostos.controllers;

import com.desafio.gerenciadorDeImpostos.controllers.dto.LoginRequestDTO;
import com.desafio.gerenciadorDeImpostos.controllers.dto.LoginResponseDTO;
import com.desafio.gerenciadorDeImpostos.controllers.dto.UserRequestDTO;
import com.desafio.gerenciadorDeImpostos.controllers.dto.UserResponseDTO;
import com.desafio.gerenciadorDeImpostos.services.UserService;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;

import java.util.Set;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.mockito.Mockito.*;

public class UserControllerTest {

    @Mock
    private UserService userService;

    @InjectMocks
    private UserController userController;

    @BeforeEach
    void setUp() {
        MockitoAnnotations.openMocks(this);
    }

    @Test
    void registerUser_ShouldReturnCreatedResponse() {
        UserRequestDTO userRequestDTO = new UserRequestDTO();
        UserResponseDTO userResponseDTO = new UserResponseDTO(1L, "username", Set.of("ROLE_USER"));
        when(userService.createUser(userRequestDTO)).thenReturn(userResponseDTO);

        ResponseEntity<UserResponseDTO> response = userController.registerUser(userRequestDTO);

        assertNotNull(response);
        assertEquals(HttpStatus.CREATED, response.getStatusCode());
        assertEquals(userResponseDTO, response.getBody());
        verify(userService, times(1)).createUser(userRequestDTO);
    }

    @Test
    void login_ShouldReturnOkResponse() {
        LoginRequestDTO loginRequestDTO = new LoginRequestDTO("username", "password");
        LoginResponseDTO loginResponseDTO = new LoginResponseDTO("mocked-jwt-token");
        when(userService.login(loginRequestDTO)).thenReturn(loginResponseDTO);

        ResponseEntity<LoginResponseDTO> response = userController.login(loginRequestDTO);

        assertNotNull(response);
        assertEquals(HttpStatus.OK, response.getStatusCode());
        assertEquals(loginResponseDTO, response.getBody());
        verify(userService, times(1)).login(loginRequestDTO);
    }

}
