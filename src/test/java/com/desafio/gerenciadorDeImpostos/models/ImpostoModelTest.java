package com.desafio.gerenciadorDeImpostos.models;

import jakarta.validation.ConstraintViolation;
import jakarta.validation.Validation;
import jakarta.validation.Validator;
import jakarta.validation.ValidatorFactory;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import java.util.Set;

import static org.junit.jupiter.api.Assertions.assertTrue;

public class ImpostoModelTest {

    private Validator validator;
    private ImpostoModel imposto;

    @BeforeEach
    void setUp() {
        ValidatorFactory factory = Validation.buildDefaultValidatorFactory();
        validator = factory.getValidator();

        imposto = ImpostoModel.builder()
                .name("Imposto de Renda")
                .descricao("Imposto sobre a renda")
                .aliquota(15.0)
                .valorFixoImposto(100.0)
                .build();
    }

    @Test
    void testValidImpostoModel() {

        Set<ConstraintViolation<ImpostoModel>> violations = validator.validate(imposto); // Valida o objeto imposoto

        assertTrue(violations.isEmpty(), "O modelo de imposto deve ser válido");
    }

}
