package com.farenda.java.util;

import java.util.Arrays;
import java.util.Collections;
import java.util.LinkedList;
import java.util.List;

public class CollectionsFill {

    public static void main(String[] args) {
        replaceSelectedElement();

        replaceAllElementsWithOne();
    }

    private static void replaceSelectedElement() {
        List<String> items = Arrays.asList("a", "b", "c", "b");
        System.out.println("Original: " + items);

        Collections.replaceAll(items, "b", "B");
        System.out.println("Updated: " + items);
    }

    private static void replaceAllElementsWithOne() {
        List<String> languages = new LinkedList<>();

        System.out.println("\nLanguages: " + languages);

        Collections.fill(languages, "Esperanto");
        System.out.println("Updated empty: " + languages);

        Collections.addAll(languages, "English", "Espanol");
        System.out.println("Languages: " + languages);

        Collections.fill(languages, "Esperanto");
        System.out.println("Updated: " + languages);
    }
}
