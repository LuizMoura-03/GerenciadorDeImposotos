package com.desafio.gerenciadorDeImpostos.repositories;

import com.desafio.gerenciadorDeImpostos.models.RoleModel;
import jakarta.validation.ConstraintViolation;
import jakarta.validation.Validation;
import jakarta.validation.Validator;
import jakarta.validation.ValidatorFactory;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import java.util.Set;
import static org.junit.jupiter.api.Assertions.*;

public class RoleRepositoryTest {

    private Validator validator;

    @BeforeEach
    void setUp() {
        ValidatorFactory factory = Validation.buildDefaultValidatorFactory();
        validator = factory.getValidator();
    }

    @Test
    void testValidRoleModel() {
        RoleModel role = new RoleModel("ADMIN");
        Set<ConstraintViolation<RoleModel>> violations = validator.validate(role); // Valida o objeto
        assertTrue(violations.isEmpty(), "O modelo de Role deve ser valido"); // Verifica se não há violações
        assertEquals("ADMIN", role.getName());
    }

    @Test
    void testInvalidRoleModelWithNullName() {
        RoleModel role = new RoleModel(null);
        Set<ConstraintViolation<RoleModel>> violations = validator.validate(role);
        assertFalse(violations.isEmpty(), "O modelo de Role deve ser invalido quando o nome é nulo");
    }

    @Test
    void testSettersAndGetters() {
        RoleModel role = new RoleModel("ADMIN");
        role.setId(2L);           // Simula a atribuição de um ID
        role.setName("USER");
        assertEquals(2L, role.getId());
        assertEquals("USER", role.getName());
    }

    @Test
    void testConstructor() {
        RoleModel role = new RoleModel("MANAGER");
        assertEquals("MANAGER", role.getName());
    }
}
