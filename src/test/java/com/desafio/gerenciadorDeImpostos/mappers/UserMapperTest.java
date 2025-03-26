package com.desafio.gerenciadorDeImpostos.mappers;

import com.desafio.gerenciadorDeImpostos.controllers.dto.UserRequestDTO;
import com.desafio.gerenciadorDeImpostos.controllers.dto.UserResponseDTO;
import com.desafio.gerenciadorDeImpostos.models.RoleModel;
import com.desafio.gerenciadorDeImpostos.models.UserModel;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.Set;

import static org.junit.jupiter.api.Assertions.*;

public class UserMapperTest {

    private UserMapper userMapper;

    @BeforeEach
    void setUp() {
        userMapper = new UserMapper();
    }

    @Test
    void testToEntity() {
        UserRequestDTO userRequestDTO = new UserRequestDTO();
        userRequestDTO.setName("Test User");
        String encodedPassword = "encodedPassword123";
        RoleModel role = new RoleModel();
        role.setName("ROLE_USER");
        Set<RoleModel> roles = Set.of(role);

        UserModel userModel = userMapper.toEntity(userRequestDTO, encodedPassword, roles);

        assertNotNull(userModel);
        assertEquals("Test User", userModel.getName());
        assertEquals("encodedPassword123", userModel.getPassword());
        assertEquals(1, userModel.getRoles().size());
        assertTrue(userModel.getRoles().stream().anyMatch(r -> r.getName().equals("ROLE_USER")));
    }

    @Test
    void testToResponse() {
        RoleModel role = new RoleModel();
        role.setName("ROLE_USER");
        Set<RoleModel> roles = Set.of(role);

        UserModel userModel = UserModel.builder()
                .id(1L)
                .name("Test User")
                .password("encodedPassword123")
                .roles(roles)
                .build();

        UserResponseDTO userResponseDTO = userMapper.toResponse(userModel);

        assertNotNull(userResponseDTO);
        assertEquals(1L, userResponseDTO.getId());
        assertEquals("Test User", userResponseDTO.getName());
        assertEquals(1, userResponseDTO.getRoles().size());
        assertTrue(userResponseDTO.getRoles().contains("ROLE_USER"));
    }

}
