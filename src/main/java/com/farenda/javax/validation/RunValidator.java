package com.farenda.javax.validation;

import jakarta.validation.*;

import java.util.Set;

public class RunValidator {

    public static void main(String[] args) {
        ValidatorFactory vf = Validation.buildDefaultValidatorFactory();
        Validator validator = vf.getValidator();

        validate(validator, new Player("ABC", 44));

        validate(validator, new Player("a", -1));

        vf.close();
    }

    private static void validate(Validator validator, Player player) {
        Set<ConstraintViolation<Player>> violations = validator.validate(player);
        if (violations.isEmpty()) {
            System.out.println("Valid player: " + player);
        } else {
            for (ConstraintViolation<Player> violation : violations) {
                System.out.println("Invalid player: " + violation.getRootBean());
                System.out.println("Offending property: " + violation.getPropertyPath());
                System.out.println("Offending value: " + violation.getInvalidValue());
                System.out.println("Message: " + violation.getMessage());
            }
            throw new ConstraintViolationException(violations);
        }
    }
}
