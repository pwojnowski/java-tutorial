package com.farenda.javax.validation;

import jakarta.validation.Validation;
import jakarta.validation.Validator;

public class Bean {

    static Validator validator
            = Validation.buildDefaultValidatorFactory().getValidator();
}
