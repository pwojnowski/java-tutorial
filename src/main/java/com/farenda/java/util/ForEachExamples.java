package com.farenda.java.util;

import java.util.HashMap;
import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.Map.Entry;
import java.util.Set;

import static java.util.Arrays.asList;

public class ForEachExamples {

    public static void main(String[] args) {
        oldSchoolForEach();
        listForEach();
        forEachOnSet();

        mapOldSchoolForEach();
        mapForEach();
    }

    private static void oldSchoolForEach() {
        List<Integer> numbers = asList(1, 2, 3);
        for (Integer x : numbers) {
            System.out.println(x);
        }
    }

    private static void listForEach() {
        List<Integer> numbers = asList(1, 2, 3);
        numbers.forEach(System.out::println);

        numbers.forEach(x -> {
            System.out.println("Number: " + x);
        });
    }

    private static void forEachOnSet() {
        Set<Integer> numbers = new HashSet<>(asList(1, 2, 3));
        numbers.forEach(System.out::println);
    }

    private static void mapOldSchoolForEach() {
        Map<Integer, String> languages = new HashMap<>();
        languages.put(1, "Java");
        languages.put(2, "Clojure");
        languages.put(3, "Scala");
        for (Entry<Integer,String> lang : languages.entrySet()) {
            System.out.printf("%d: %s%n",
                    lang.getKey(), lang.getValue());
        }
    }

    private static void mapForEach() {
        Map<Integer, String> languages = new HashMap<>();
        languages.put(1, "Java");
        languages.put(2, "Clojure");
        languages.put(3, "Scala");
        languages.forEach((id, name) ->
                System.out.printf("%d: %s%n", id, name));
    }
}
