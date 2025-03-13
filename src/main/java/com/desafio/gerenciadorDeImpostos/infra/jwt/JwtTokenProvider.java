package com.desafio.gerenciadorDeImpostos.infra.jwt;

import org.springframework.stereotype.Component;
import org.springframework.web.filter.OncePerRequestFilter;

@Component
public class JwtTokenProvider{


    public boolean validateToken(String token) {
    }

    public String getUsernameFromToken(String token) {
    }
}
