package com.farenda.java.util;

import java.util.Optional;

public class OptionalExamples {

    public static void main(String[] args) {
        String value = Optional.of("small")
                .map(String::toUpperCase)
                .get();
        System.out.println("of 'small': " + value);

        value = Optional.of("null from function")
                .map(s -> (String) null)
                .orElse("no value");
        System.out.println("Null from mapper: " + value);

        value = Optional.<String>ofNullable(null)
                .map(String::toUpperCase)
                .orElse("no value");
        System.out.println("Of nullable: " + value);
    }

    private static void ofNullable() {
        String name = null;
        Optional<String> value = Optional.ofNullable(name);
        System.out.println("Is present: " + value.isPresent());
        if (value.isPresent()) {
            System.out.println("Current value: " + value.get());
        }

        value = Optional.ofNullable("Monika");
        System.out.println("Is present: " + value.isPresent());
        if (value.isPresent()) {
            System.out.println("Current value: " + value.get());
        }
    }
}
