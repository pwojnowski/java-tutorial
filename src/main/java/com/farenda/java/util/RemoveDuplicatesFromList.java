package com.farenda.java.util;

import java.util.*;
import java.util.stream.Collectors;

import static java.util.Arrays.asList;

public class RemoveDuplicatesFromList {

    public static void main(String[] args) {
        usingSet();
        slowButConstantMemory();
        removingFromSortedList();
        //java8streams();
    }

    private static void usingSet() {
        List<Integer> numbers = asList(1, 1, 2, 1, 2, 3, 5);
        System.out.println("Numbers: " + numbers);

        Set<Integer> unique = new HashSet<>(numbers);
        numbers = new ArrayList<>(unique);
        System.out.println("Unique: " + numbers);
    }

    private static void slowButConstantMemory() {
        List<Integer> numbers = new ArrayList<>(asList(1, 1, 2, 1, 2, 3, 5));
        System.out.println("Numbers: " + numbers);

        ListIterator<Integer> it = numbers.listIterator();
        while (it.hasNext()) {
            int i = it.nextIndex();
            Integer current = it.next();
            for (int j = 0; j < i; ++j) {
                if (current.equals(numbers.get(j))) {
                    it.remove();
                    break;
                }
            }
        }
        System.out.println("Unique: " + numbers);
    }

    private static void removingFromSortedList() {
        List<Integer> numbers = new ArrayList<>(asList(1, 1, 1, 2, 2, 3, 5, 5));
        System.out.println("Numbers: " + numbers);

        if (numbers.size() > 1) {
            Iterator<Integer> it = numbers.iterator();
            Integer prev = it.next();
            while (it.hasNext()) {
                Integer current = it.next();
                if (prev.equals(current)) {
                    it.remove();
                } else {
                    prev = current;
                }
            }
        }
        System.out.println("Unique: " + numbers);
    }

    private static void java8streams() {
        List<Integer> numbers = new ArrayList<>(asList(1, 2, 1, 3, 2, 5));
        System.out.println("Numbers: " + numbers);
        numbers = numbers.parallelStream().unordered().distinct().collect(Collectors.toList());
        System.out.println("Unique: " + numbers);
    }
}
