package com.desafio.gerenciadorDeImpostos.models;

import jakarta.validation.ConstraintViolation;
import jakarta.validation.Validation;
import jakarta.validation.Validator;
import jakarta.validation.ValidatorFactory;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.Set;

import static org.junit.jupiter.api.Assertions.*;

public class RoleModelTest {

    private Validator validator;

    @BeforeEach
    void setUp() {
        ValidatorFactory factory = Validation.buildDefaultValidatorFactory();
        validator = factory.getValidator();
    }

    @Test
    void testValidRoleModel() {

        RoleModel role = new RoleModel("ADMIN"); // Cria um objeto RoleModel valido

        Set<ConstraintViolation<RoleModel>> violations = validator.validate(role);  // Valida o objeto

        assertTrue(violations.isEmpty(), "O modelo de Role deve ser valido");   // Verifica se não ha violações
    }

    @Test
    void testInvalidRoleModel() {

        RoleModel role = new RoleModel(null);  // Cria um objeto RoleModel invalido (nome nulo)

        Set<ConstraintViolation<RoleModel>> violations = validator.validate(role);

        assertFalse(violations.isEmpty(), "O modelo de Role deve ser invalido quando o nome é nulo");
    }

    @Test
    void testRoleModelConstructor() {
        RoleModel role = new RoleModel("USER");
        assertEquals("USER", role.getName(), "O construtor deve inicializar o nome corretamente");
    }

    @Test
    void testRoleModelSettersAndGetters() {
        RoleModel role = new RoleModel();
        role.setName("MANAGER");
        assertEquals("MANAGER", role.getName(), "O setter e o getter de 'name' devem funcionar corretamente");

        role.setId(1L);
        assertEquals(1L, role.getId(), "O setter e o getter de 'id' devem funcionar corretamente");
    }

}
