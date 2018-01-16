package com.farenda.java.util;

import java.util.Arrays;
import java.util.Collections;
import java.util.Enumeration;
import java.util.List;

public class CollectionsListEnumeration {

    public static void main(String[] args) {
        List<String> items = Arrays.asList("evaporating", "content");

        Enumeration<String> en = Collections.enumeration(items);
        print(en);

        // Legacy Enumeration to List:
        List<String> list = Collections.list(en);
        System.out.println("As list (content is gone!): " + list);

        // Again, but without iterating:
        en = Collections.enumeration(items);
        list = Collections.list(en);
        System.out.println("\nAs list: " + list);
        print(en);
    }

    private static void print(Enumeration<String> en) {
        System.out.println("Enumeration: ");
        while (en.hasMoreElements()) {
            System.out.println(en.nextElement());
        }
    }
}
