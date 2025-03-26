package com.desafio.gerenciadorDeImpostos.services;

import com.desafio.gerenciadorDeImpostos.controllers.dto.LoginRequestDTO;
import com.desafio.gerenciadorDeImpostos.controllers.dto.LoginResponseDTO;
import com.desafio.gerenciadorDeImpostos.exception.InvalidCredentialsException;
import com.desafio.gerenciadorDeImpostos.infra.jwt.JwtTokenProvider;
import com.desafio.gerenciadorDeImpostos.models.UserModel;
import com.desafio.gerenciadorDeImpostos.repositories.UserRepository;
import com.desafio.gerenciadorDeImpostos.security.CustomUserDetailsService;
import org.slf4j.LoggerFactory;
import org.slf4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

@Service
public class AuthenticationServiceImpl implements AuthenticationService {

    private final UserRepository userRepository;
    private final PasswordEncoder passwordEncoder;
    private final JwtTokenProvider jwtTokenProvider;
    private final CustomUserDetailsService customUserDetailsService;

    @Autowired
    public AuthenticationServiceImpl(UserRepository userRepository, PasswordEncoder passwordEncoder, JwtTokenProvider jwtTokenProvider, CustomUserDetailsService customUserDetailsService) {
        this.userRepository = userRepository;
        this.passwordEncoder = passwordEncoder;
        this.jwtTokenProvider = jwtTokenProvider;
        this.customUserDetailsService = customUserDetailsService;
    }

    private static final Logger logger = (Logger) LoggerFactory.getLogger(AuthenticationServiceImpl.class);

    public LoginResponseDTO authenticate(LoginRequestDTO loginRequestDTO) {
        logger.info("Tentativa de autenticação para o usuario: {}", loginRequestDTO.getName());

        UserModel userModel = userRepository.findByName(loginRequestDTO.getName())
                .orElseThrow(() -> new UsernameNotFoundException("Usuário não encontrado!"));

        validatePassword(loginRequestDTO.getPassword(), userModel.getPassword());

        String token = jwtTokenProvider.generateToken(userModel.getName());

        logger.info("Autenticação bem-sucedida: {}", loginRequestDTO.getPassword());
        return new LoginResponseDTO(token);
    }

    private String generateToken( String username) {
        return "token-gerado";
    }

    private void validatePassword(String rawPassword, String encodedPassword) {
        if (!passwordEncoder.matches(rawPassword, encodedPassword)) {
            throw new InvalidCredentialsException("Credenciais inválidas!");
        }
    }
}
