package com.farenda.java.lang;

import java.time.Instant;
import java.util.Arrays;
import java.util.Collection;
import java.util.List;
import java.util.stream.Collectors;

public class CastingTrick {

    public static void main(String[] args) {
        Collection<Object> bag = Arrays.asList(
                "string 1",
                1,
                "string 2",
                2,
                42d,
                Instant.EPOCH,
                Instant.now()
        );

        System.out.println("Strings: " + selectByType(bag, String.class));
        System.out.println("Numbers: " + selectByType(bag, Number.class));
        System.out.println("Ints:    " + selectByType(bag, Integer.class));
        System.out.println("Times:   " + selectByType(bag, Instant.class));
    }

    static <T> List<T> selectByType(Collection<Object> bag,
                                    Class<T> type) {
        return bag.stream()
                .filter(o -> type.isAssignableFrom(o.getClass()))
                .map(type::cast)
                .collect(Collectors.toList());
    }
}
