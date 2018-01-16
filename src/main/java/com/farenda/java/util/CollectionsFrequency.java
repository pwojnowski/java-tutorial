package com.farenda.java.util;

import java.util.Arrays;
import java.util.Collections;
import java.util.List;

public class CollectionsFrequency {

    public static void main(String[] args) {
        String s = "Metio lacigas, metio vivigas.";
        List<String> chars = Arrays.asList(s.split(""));

        System.out.println("Original sentence: " + s);
        System.out.println("Chars: " + chars);

        System.out.println("Number of 'M' in chars: "
                + Collections.frequency(chars, "M"));

        System.out.println("Number of 'a' in chars: "
                + Collections.frequency(chars, "a"));

        System.out.println("Number of nulls in chars: "
                + Collections.frequency(chars, null));
    }
}
