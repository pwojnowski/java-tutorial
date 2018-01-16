package com.farenda.java.util;

import java.util.Arrays;
import java.util.Collection;
import java.util.Collections;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

public class CollectionsDisjoint {

    public static void main(String[] args) {
        List<String> c1 = Arrays.asList("effective", "java");
        Set<String> c2 = new HashSet<>(Arrays.asList("java", "tutorial"));

        areDifferent(c1, c2);

        areDifferent(c1, Arrays.asList("clojure", "scala"));
    }

    private static void areDifferent(Collection<String> c1,
                                     Collection<String> c2) {
        // In other words: have _NO_ common elements:
        System.out.printf("%s and %s are completely different: %s%n",
                c1, c2, Collections.disjoint(c1, c2));
    }
}
