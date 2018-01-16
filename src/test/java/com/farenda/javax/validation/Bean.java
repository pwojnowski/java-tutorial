package com.farenda.javax.validation;

import javax.validation.Validation;
import javax.validation.Validator;

public class Bean {

    static Validator validator
            = Validation.buildDefaultValidatorFactory().getValidator();
}
