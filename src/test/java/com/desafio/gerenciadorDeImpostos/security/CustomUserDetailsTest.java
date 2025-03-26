package com.desafio.gerenciadorDeImpostos.security;

import org.junit.jupiter.api.Test;
import org.springframework.security.core.authority.SimpleGrantedAuthority;

import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;

public class CustomUserDetailsTest {

    @Test
    void testCustomUserDetails_GettersAndSetters() {

        String username = "testUser";
        String password = "testPassword";
        List<SimpleGrantedAuthority> authorities = List.of(new SimpleGrantedAuthority("ROLE_USER"));

        CustomUserDetails customUserDetails = new CustomUserDetails();
        customUserDetails.setUsername(username);
        customUserDetails.setPassword(password);
        customUserDetails.setAuthorities(authorities);

        assertEquals(username, customUserDetails.getUsername());
        assertEquals(password, customUserDetails.getPassword());
        assertEquals(authorities, customUserDetails.getAuthorities());
    }

    @Test
    void testCustomUserDetails_Constructor() {
        String username = "testUser";
        String password = "testPassword";
        List<SimpleGrantedAuthority> authorities = List.of(new SimpleGrantedAuthority("ROLE_USER"));

        CustomUserDetails customUserDetails = new CustomUserDetails(username, password, authorities);

        assertEquals(username, customUserDetails.getUsername());
        assertEquals(password, customUserDetails.getPassword());
        assertEquals(authorities, customUserDetails.getAuthorities());
    }

    @Test
    void testIsAccountNonExpired() {
        CustomUserDetails customUserDetails = new CustomUserDetails();

        assertTrue(customUserDetails.isAccountNonExpired());
    }

    @Test
    void testIsAccountNonLocked() {
        CustomUserDetails customUserDetails = new CustomUserDetails();

        assertTrue(customUserDetails.isAccountNonLocked());
    }

    @Test
    void testIsCredentialsNonExpired() {
        CustomUserDetails customUserDetails = new CustomUserDetails();

        assertTrue(customUserDetails.isCredentialsNonExpired());
    }

    @Test
    void testIsEnabled() {
        CustomUserDetails customUserDetails = new CustomUserDetails();

        assertTrue(customUserDetails.isEnabled());
    }

}
