package com.farenda.java.util;

import java.util.Arrays;
import java.util.Collections;
import java.util.List;
import java.util.stream.Collectors;

public class CollectionsBinarySearchComparator {

    private static final List<User> users = Arrays.asList(
            new User("Jon Snow"),
            new User("Cersei"),
            new User("Joffrey"),
            new User("Sansa"),
            new User("Littlefinger"),
            new User("Daenerys")
    );

    public static void main(String[] args) {
        withDefaultComparator();
        withNameComparator();
    }

    private static void withDefaultComparator() {
        System.out.println("Default Comparator");

        // Get names of users:
        // The below Java 8 code is the same as:
        // names = new List; for(User u : users) names.add(u.getName());
        List<String> names = users
                .parallelStream()
                .map(User::getName)
                .collect(Collectors.toList());

        System.out.println("Unsorted names: " + names);

        // Binary search needs sorted data:
        Collections.sort(names, null);
        System.out.println("Names sorted using default comparator: " + names);

        // Search using default Comparator:
        int idx = Collections.binarySearch(names, "Daenerys", null);
        System.out.println("Found 'Daenerys' at index: " + idx);
    }

    private static void withNameComparator() {
        System.out.println("Name Comparator");

        System.out.println("Unsorted users: " + users);

        NameComparator nameComparator = new NameComparator();
        // binary search expects sorted data:
        Collections.sort(users, nameComparator);
        System.out.println("Users sorted using NameComparator: " + users);

        int idx = Collections.binarySearch(
                users, new User("Daenerys"), nameComparator);
        System.out.println("Found 'Daenerys' at index: " + idx);
    }
}
