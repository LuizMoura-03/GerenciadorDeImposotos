package com.desafio.gerenciadorDeImpostos.models;

import jakarta.validation.ConstraintViolation;
import jakarta.validation.Validation;
import jakarta.validation.Validator;
import jakarta.validation.ValidatorFactory;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.HashSet;
import java.util.Set;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;

public class UserModelTest {

    private Validator validator;

    @BeforeEach
    void setUp() {
        ValidatorFactory factory = Validation.buildDefaultValidatorFactory();
        validator = factory.getValidator();
    }

    @Test
    void testValidUserModel() {
        Set<RoleModel> roles = new HashSet<>();
        roles.add(new RoleModel("ADMIN"));

        UserModel user = UserModel.builder()
                .id(1L)
                .name("Luiz")
                .password("password123")
                .roles(roles)
                .build();

        Set<ConstraintViolation<UserModel>> violations = validator.validate(user);
        assertTrue(violations.isEmpty(), "No validation errors should occur for a valid UserModel");
    }

    @Test
    void testInvalidUserModel_NameIsBlank() {
        Set<RoleModel> roles = new HashSet<>();
        roles.add(new RoleModel("ADMIN"));

        UserModel user = UserModel.builder()
                .id(1L)
                .name("")
                .password("password123")
                .roles(roles)
                .build();

        Set<ConstraintViolation<UserModel>> violations = validator.validate(user);
        assertEquals(1, violations.size());
        assertEquals("O nome de usuário é obrigatório.", violations.iterator().next().getMessage());
    }

    @Test
    void testInvalidUserModel_PasswordIsBlank() {
        Set<RoleModel> roles = new HashSet<>();
        roles.add(new RoleModel("ADMIN"));

        UserModel user = UserModel.builder()
                .id(1L)
                .name("Luiz")
                .password("")
                .roles(roles)
                .build();

        Set<ConstraintViolation<UserModel>> violations = validator.validate(user);
        assertEquals(1, violations.size());
        assertEquals("A senha é obrigatória.", violations.iterator().next().getMessage());
    }

    @Test
    void testInvalidUserModel_RolesIsEmpty() {
        UserModel user = UserModel.builder()
                .id(1L)
                .name("Luiz")
                .password("password123")
                .roles(new HashSet<>())
                .build();

        Set<ConstraintViolation<UserModel>> violations = validator.validate(user);
        assertEquals(1, violations.size());
        assertEquals("O usuário deve ter pelo menos um papel associado.", violations.iterator().next().getMessage());
    }

    @Test
    void testGettersAndSetters() {
        UserModel user = new UserModel();
        user.setId(1L);
        user.setName("Luiz");
        user.setPassword("password123");

        Set<RoleModel> roles = new HashSet<>();
        roles.add(new RoleModel("ADMIN"));
        user.setRoles(roles);

        assertEquals(1L, user.getId());
        assertEquals("Luiz", user.getName());
        assertEquals("password123", user.getPassword());
        assertEquals(roles, user.getRoles());
    }

    @Test
    void testConstructor() {
        Set<RoleModel> roles = new HashSet<>();
        roles.add(new RoleModel("ADMIN"));

        UserModel user = new UserModel(1L, "Luiz", "password123", roles);

        assertEquals(1L, user.getId());
        assertEquals("Luiz", user.getName());
        assertEquals("password123", user.getPassword());
        assertEquals(roles, user.getRoles());
    }

}
