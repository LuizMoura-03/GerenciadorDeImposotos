package com.desafio.gerenciadorDeImpostos.services;

import com.desafio.gerenciadorDeImpostos.controllers.dto.LoginRequestDTO;
import com.desafio.gerenciadorDeImpostos.controllers.dto.LoginResponseDTO;
import com.desafio.gerenciadorDeImpostos.controllers.dto.UserRequestDTO;
import com.desafio.gerenciadorDeImpostos.controllers.dto.UserResponseDTO;
import com.desafio.gerenciadorDeImpostos.exception.DuplicateUsernameException;
import com.desafio.gerenciadorDeImpostos.infra.jwt.JwtTokenProvider;
import com.desafio.gerenciadorDeImpostos.models.RoleModel;
import com.desafio.gerenciadorDeImpostos.models.UserModel;
import com.desafio.gerenciadorDeImpostos.repositories.RoleRepository;
import com.desafio.gerenciadorDeImpostos.repositories.UserRepository;
import com.desafio.gerenciadorDeImpostos.security.CustomUserDetails;
import com.desafio.gerenciadorDeImpostos.security.CustomUserDetailsService;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.MockitoAnnotations;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.password.PasswordEncoder;

import java.util.Optional;
import java.util.Set;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

public class UserServiceImplTest {

    @Mock
    private UserRepository userRepository;

    @Mock
    private RoleRepository roleRepository;

    @Mock
    private PasswordEncoder bCryptPasswordEncoder;

    @Mock
    private CustomUserDetailsService customUserDetailsService;

    @Mock
    private JwtTokenProvider jwtTokenProvider;

    @InjectMocks
    private UserServiceImpl userServiceImpl;

    @BeforeEach
    void setUp() {
        MockitoAnnotations.openMocks(this);
    }

    @Test
    void testCreateUser_Success() {

        UserRequestDTO userRequestDTO = new UserRequestDTO("testUser", "password", Set.of("ROLE_USER"));
        RoleModel roleModel = new RoleModel("ROLE_USER");
        UserModel userModel = new UserModel();
        userModel.setId(1L);
        userModel.setName("testUser");
        userModel.setPassword("encodedPassword");
        userModel.setRoles(Set.of(roleModel));

        when(userRepository.existsByName("testUser")).thenReturn(false);
        when(bCryptPasswordEncoder.encode("password")).thenReturn("encodedPassword");
        when(roleRepository.findByName("ROLE_USER")).thenReturn(Optional.of(roleModel));
        when(userRepository.save(Mockito.<UserModel>any())).thenReturn(userModel);

        UserResponseDTO response = userServiceImpl.createUser(userRequestDTO);

        assertNotNull(response);
        assertEquals("testUser", response.getName());
        assertTrue(response.getRoles().contains("ROLE_USER"));

        verify(userRepository, times(1)).existsByName("testUser");
        verify(bCryptPasswordEncoder, times(1)).encode("password");
        verify(roleRepository, times(1)).findByName("ROLE_USER");
        verify(userRepository, times(1)).save(Mockito.<UserModel>any());

    }

    @Test
    void testCreateUser_DuplicateUsername() {
        UserRequestDTO userRequestDTO = new UserRequestDTO("testUser", "password", Set.of("ROLE_USER"));

        when(userRepository.existsByName("testUser")).thenReturn(true);

        assertThrows(DuplicateUsernameException.class, () -> userServiceImpl.createUser(userRequestDTO));

        verify(userRepository, times(1)).existsByName("testUser");
        verifyNoInteractions(bCryptPasswordEncoder, roleRepository, jwtTokenProvider);
    }

    @Test
    void testLogin_Success() {
        LoginRequestDTO loginRequestDTO = new LoginRequestDTO("testUser", "password");
        UserModel userModel = new UserModel();
        userModel.setName("testUser");
        userModel.setPassword("encodedPassword");

        CustomUserDetails userDetails = mock(CustomUserDetails.class);
        Authentication authentication = mock(Authentication.class);

        when(userRepository.findByName("testUser")).thenReturn(Optional.of(userModel));
        when(bCryptPasswordEncoder.matches("password", "encodedPassword")).thenReturn(true);
        when(customUserDetailsService.loadUserByUsername("testUser")).thenReturn(userDetails);
        when(jwtTokenProvider.generateToken()).thenReturn("jwtToken");

        LoginResponseDTO response = userServiceImpl.login(loginRequestDTO);

        assertNotNull(response);
        assertEquals("jwtToken", response.getToken());

        verify(userRepository, times(1)).findByName("testUser");
        verify(bCryptPasswordEncoder, times(1)).matches("password", "encodedPassword");
        verify(customUserDetailsService, times(1)).loadUserByUsername("testUser");
        verify(jwtTokenProvider, times(1)).generateToken();
    }

    @Test
    void testLogin_UserNotFound() {
        LoginRequestDTO loginRequestDTO = new LoginRequestDTO("testUser", "password");

        when(userRepository.findByName("testUser")).thenReturn(Optional.empty());

        assertThrows(UsernameNotFoundException.class, () -> userServiceImpl.login(loginRequestDTO));

        verify(userRepository, times(1)).findByName("testUser");
        verifyNoInteractions(bCryptPasswordEncoder, customUserDetailsService, jwtTokenProvider);
    }

    @Test
    void testLogin_InvalidPassword() {
        LoginRequestDTO loginRequestDTO = new LoginRequestDTO("testUser", "password");
        UserModel userModel = new UserModel();
        userModel.setName("testUser");
        userModel.setPassword("encodedPassword");

        when(userRepository.findByName("testUser")).thenReturn(Optional.of(userModel));
        when(bCryptPasswordEncoder.matches("password", "encodedPassword")).thenReturn(false);

        assertThrows(UsernameNotFoundException.class, () -> userServiceImpl.login(loginRequestDTO));

        verify(userRepository, times(1)).findByName("testUser");
        verify(bCryptPasswordEncoder, times(1)).matches("password", "encodedPassword");
        verifyNoInteractions(customUserDetailsService, jwtTokenProvider);
    }
}


