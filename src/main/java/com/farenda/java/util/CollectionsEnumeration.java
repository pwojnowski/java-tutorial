package com.farenda.java.util;

import java.util.Arrays;
import java.util.Collections;
import java.util.Enumeration;
import java.util.List;

public class CollectionsEnumeration {

    public static void main(String[] args) {
        List<String> coll = Arrays.asList("java", "util", "Collections");
        System.out.println("Collection: " + coll);

        // convert a Collection to Enumeration of the same type:
        Enumeration<String> en = Collections.enumeration(coll);
        System.out.println("Enumeration (no toString): " + en);
        while (en.hasMoreElements()) {
            System.out.println(en.nextElement());
        }
    }
}
