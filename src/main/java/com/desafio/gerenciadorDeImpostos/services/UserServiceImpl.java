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
import lombok.RequiredArgsConstructor;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.Set;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class UserServiceImpl implements UserService{

    private final UserRepository userRepository;
    private final RoleRepository roleRepository;
    private final PasswordEncoder bCryptPasswordEncoder;
    private final CustomUserDetailsService customUserDetailsService;
    private final JwtTokenProvider jwtTokenProvider;

    public UserResponseDTO createUser(UserRequestDTO userRequestDTO) {
        String username = userRequestDTO.getName();
        if (userRepository.existsByName(username)) {
            throw new DuplicateUsernameException("Usuário já cadastrado no sistema");
        }

        UserModel userModel = new UserModel();
        userModel.setName(username);
        userModel.setPassword(bCryptPasswordEncoder.encode(userRequestDTO.getPassword()));

        Set<RoleModel> roles = userRequestDTO.getRoles().stream()
                .map(roleName -> roleRepository.findByName(roleName)
                        .orElseGet(() -> roleRepository.save(new RoleModel(roleName))))
                .collect(Collectors.toSet());

        userModel.setRoles(roles);
        userRepository.save(userModel);

        Set<String> stringRoles = userModel.getRoles().stream()
                .map(RoleModel::getName)
                .collect(Collectors.toSet());

        return new UserResponseDTO(userModel.getId(), userModel.getName(), stringRoles);
    }


    public LoginResponseDTO login(LoginRequestDTO loginRequestDTO) {
        UserModel userModel = userRepository.findByName(loginRequestDTO.getName())
                .orElseThrow(() -> new UsernameNotFoundException("Usuário não encontrado!"));

        if (!bCryptPasswordEncoder.matches(loginRequestDTO.getPassword(), userModel.getPassword())) {
            throw new UsernameNotFoundException("Senha inválida!");
        }

        CustomUserDetails userDetails = (CustomUserDetails) customUserDetailsService
                .loadUserByUsername(loginRequestDTO.getName());

        Authentication authentication = new UsernamePasswordAuthenticationToken(
                userDetails, null, userDetails.getAuthorities());
        SecurityContextHolder.getContext().setAuthentication(authentication);

        String username = authentication.getName();

        String token = jwtTokenProvider.generateToken("testUser");

        return new LoginResponseDTO(token);
    }

}
