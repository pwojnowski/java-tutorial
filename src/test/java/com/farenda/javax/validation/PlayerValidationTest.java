package com.farenda.javax.validation;

import jakarta.validation.ConstraintViolation;
import jakarta.validation.Validation;
import jakarta.validation.Validator;
import jakarta.validation.ValidatorFactory;
import org.junit.AfterClass;
import org.junit.BeforeClass;
import org.junit.Test;

import java.util.Set;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;

public class PlayerValidationTest {

    private static ValidatorFactory validatorFactory;
    private static Validator validator;

    @BeforeClass
    public static void createValidator() {
        validatorFactory = Validation.buildDefaultValidatorFactory();
        validator = validatorFactory.getValidator();
    }

    @AfterClass
    public static void close() {
        validatorFactory.close();
    }

    @Test
    public void shouldHaveNoViolations() {
        //given:
        Player player = new Player("ABC", 44);

        //when:
        Set<ConstraintViolation<Player>> violations
                = validator.validate(player);

        //then:
        assertTrue(violations.isEmpty());
    }

    @Test
    public void shouldDetectInvalidName() {
        //given too short name:
        Player player = new Player("a", 44);

        //when:
        Set<ConstraintViolation<Player>> violations
                = validator.validate(player);

        //then:
        assertEquals(violations.size(), 1);

        ConstraintViolation<Player> violation
                = violations.iterator().next();
        assertEquals("size must be between 3 and 3",
                     violation.getMessage());
        assertEquals("name", violation.getPropertyPath().toString());
        assertEquals("a", violation.getInvalidValue());
    }
}
