package com.desafio.gerenciadorDeImpostos.security;

import com.desafio.gerenciadorDeImpostos.models.UserModel;
import com.desafio.gerenciadorDeImpostos.repositories.UserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class CustomUserDetailsService implements UserDetailsService {

    private final UserRepository userRepository;

    @Override
    public UserDetails loadUserByUsername(String userName) throws UsernameNotFoundException {
        UserModel userModel = userRepository.findByUserName(userName)
                .orElseThrow(() -> new UsernameNotFoundException("Usuário não encontrado com o username: " + userName));

        return new User(
                userModel.getName(),
                userModel.getPassword(), // Senha
                userModel.getRoles() // Papéis (roles)
                        .stream()
                        .map(role -> new SimpleGrantedAuthority(role.getName())) // Converter RoleModel para SimpleGrantedAuthority
                        .toList()
        );

    }

}
