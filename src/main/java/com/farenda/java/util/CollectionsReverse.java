package com.farenda.java.util;

import java.util.Arrays;
import java.util.Collections;
import java.util.List;

public class CollectionsReverse {

    public static void main(String[] args) {
        List<Integer> numbers = Arrays.asList(1, 2, 3, 4);
        System.out.println("Numbers: " + numbers);

        Collections.reverse(numbers);
        System.out.println("Reversed: " + numbers);
    }
}
