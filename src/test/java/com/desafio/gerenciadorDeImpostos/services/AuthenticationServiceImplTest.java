package com.desafio.gerenciadorDeImpostos.services;

import com.desafio.gerenciadorDeImpostos.controllers.dto.LoginRequestDTO;
import com.desafio.gerenciadorDeImpostos.controllers.dto.LoginResponseDTO;
import com.desafio.gerenciadorDeImpostos.exception.InvalidCredentialsException;
import com.desafio.gerenciadorDeImpostos.infra.jwt.JwtTokenProvider;
import com.desafio.gerenciadorDeImpostos.models.UserModel;
import com.desafio.gerenciadorDeImpostos.repositories.UserRepository;
import com.desafio.gerenciadorDeImpostos.security.CustomUserDetails;
import com.desafio.gerenciadorDeImpostos.security.CustomUserDetailsService;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.password.PasswordEncoder;

import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

public class AuthenticationServiceImplTest {

    @Mock
    private UserRepository userRepository;

    @Mock
    private PasswordEncoder passwordEncoder;

    @Mock
    private JwtTokenProvider jwtTokenProvider;

    @Mock
    private CustomUserDetailsService customUserDetailsService;

    @InjectMocks
    private AuthenticationServiceImpl authenticationService;

    @BeforeEach
    void setUp() {
        MockitoAnnotations.openMocks(this);
    }

    @Test
    void authenticate_Success() {
        LoginRequestDTO loginRequestDTO = new LoginRequestDTO("testUser", "password");
        UserModel userModel = new UserModel();
        userModel.setName("testUser");
        userModel.setPassword("encodedPassword");

        when(userRepository.findByName("testUser")).thenReturn(Optional.of(userModel));
        when(passwordEncoder.matches("password", "encodedPassword")).thenReturn(true);
        when(jwtTokenProvider.generateToken(anyString())).thenReturn("token-gerado");

        LoginResponseDTO response = authenticationService.authenticate(loginRequestDTO);

        assertNotNull(response);
        assertEquals("token-gerado", response.getToken());
        verify(userRepository, times(1)).findByName("testUser");
        verify(passwordEncoder, times(1)).matches("password", "encodedPassword");
        verify(jwtTokenProvider, times(1)).generateToken(anyString());

    }

    @Test
    void authenticate_UserNotFound() {
        LoginRequestDTO loginRequestDTO = new LoginRequestDTO("testUser", "password");

        when(userRepository.findByName("testUser")).thenReturn(Optional.empty());

        assertThrows(UsernameNotFoundException.class, () -> authenticationService.authenticate(loginRequestDTO));
        verify(userRepository, times(1)).findByName("testUser");
        verifyNoInteractions(passwordEncoder, jwtTokenProvider);
    }

    @Test
    void authenticate_InvalidPassword() {
        LoginRequestDTO loginRequestDTO = new LoginRequestDTO("testUser", "wrongPassword");
        UserModel userModel = new UserModel();
        userModel.setName("testUser");
        userModel.setPassword("encodedPassword");

        when(userRepository.findByName("testUser")).thenReturn(Optional.of(userModel));
        when(passwordEncoder.matches("wrongPassword", "encodedPassword")).thenReturn(false);

        assertThrows(InvalidCredentialsException.class, () -> authenticationService.authenticate(loginRequestDTO));
        verify(userRepository, times(1)).findByName("testUser");
        verify(passwordEncoder, times(1)).matches("wrongPassword", "encodedPassword");
        verifyNoInteractions(jwtTokenProvider);
    }

}
