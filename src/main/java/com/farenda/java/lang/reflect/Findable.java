package com.farenda.java.lang.reflect;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

// retention runtime - make the annotation available at runtime
@Retention(RetentionPolicy.RUNTIME)
// target [Method, Field] - allow to use only on methods and fields
@Target({ElementType.METHOD, ElementType.FIELD})
public @interface Findable {

    /**
     * User friendly name of annotated class.
     */
    String name();
}
