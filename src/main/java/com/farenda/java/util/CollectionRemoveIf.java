package com.farenda.java.util;

import java.util.Iterator;
import java.util.List;
import java.util.stream.IntStream;

import static java.util.stream.Collectors.toList;

public class CollectionRemoveIf {

    public static void main(String[] args) {
        preJava8version();
        java8version();
    }

    private static void preJava8version() {
        List<Integer> numbers = inclusiveRange(1, 10);
        System.out.println("Numbers: " + numbers);
        for (Iterator<Integer> it = numbers.iterator(); it.hasNext(); ) {
            if (it.next() % 2 == 0) {
                it.remove();
            }
        }
        numbers.removeIf(i -> i % 2 == 0);
        System.out.println("Numbers: " + numbers);
    }

    private static void java8version() {
        List<Integer> numbers = inclusiveRange(1, 10);
        System.out.println("Numbers: " + numbers);
        numbers.removeIf(i -> i % 2 == 0);
        System.out.println("Numbers: " + numbers);
    }

    private static List<Integer> inclusiveRange(int from, int to) {
        return IntStream.rangeClosed(from, to)
                .boxed()
                .collect(toList());
    }
}
