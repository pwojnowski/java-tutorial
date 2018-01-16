package com.farenda.java.util;

import java.util.Arrays;
import java.util.Collections;
import java.util.LinkedList;
import java.util.List;

public class CollectionsSort {

    public static void main(String[] args) {
        sortUsingDefaultComparator();

        System.out.println();

        sortUsingOwnComparator();
    }

    private static void sortUsingOwnComparator() {
        List<User> kings = new LinkedList<>(Arrays.asList(
                new User("Jagiełło"),
                new User("Batory"),
                new User("Chrobry"),
                new User("Sobieski")));
        Collections.shuffle(kings);
        System.out.println("Kings:");
        kings.forEach(System.out::println);

        System.out.println();

        List<User> sorted = new LinkedList<>(kings);
        Collections.sort(sorted, new NameComparator());
        System.out.println("Kings sorted by name:");
        sorted.forEach(System.out::println);

        System.out.println();

        sorted = new LinkedList<>(kings);
        Collections.sort(sorted, new IdComparator());
        System.out.println("Kings sorted by id:");
        sorted.forEach(System.out::println);
    }

    private static void sortUsingDefaultComparator() {
        List<Integer> temperatures = Arrays.asList(34, 30, 31, 30, 32, 35, 34);
        System.out.println("Temperatures: " + temperatures);

        Collections.sort(temperatures);
        System.out.println("Sorted: " + temperatures);
    }
}
