package com.farenda.java.util;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

import static java.util.Arrays.asList;

public class CollectionsCopy {

    public static void main(String[] args) {
        usingCopyConstructor();

        copySourceToSmallerDest();

        copySameSizeLists();

        copySourceToBiggerDest();

        copyToUnmodifiableDest();
    }

    private static void usingCopyConstructor() {
        List<Integer> source = asList(1, 2, 3);
        List<Integer> dest = new ArrayList<>(source);
        System.out.printf("%nCopy %s to %s using copy constructor.%n", source, dest);
    }

    private static void copySourceToSmallerDest() {
        List<Integer> source = asList(1, 2, 3);
        // The same for: new ArrayList<>(source.size());
        List<Integer> dest = asList(4, 5);

        System.out.printf("%nCopy %s to (smaller) %s%n", source, dest);
        try {
            Collections.copy(dest, source);
        } catch (IndexOutOfBoundsException e) {
            e.printStackTrace();
        }
    }

    private static void copySameSizeLists() {
        List<Integer> source = asList(1, 2);
        List<Integer> dest = asList(3, 4);

        System.out.printf("%nCopy %s to (same size) %s%n", source, dest);
        Collections.copy(dest, source);
        System.out.println("source: " + source);
        System.out.println("destination: " + dest);
    }

    private static void copySourceToBiggerDest() {
        List<Integer> source = asList(1, 2);
        List<Integer> dest = asList(3, 4, 5);

        System.out.printf("%nCopy %s to (bigger) %s%n", source, dest);
        Collections.copy(dest, source);
        System.out.println("source: " + source);
        System.out.println("destination: " + dest);
    }

    private static void copyToUnmodifiableDest() {
        List<Integer> source = asList(1, 2);
        List<Integer> dest = Collections.unmodifiableList(asList(4, 5));

        System.out.printf("%nCopy %s to (unmodifiable) %s%n", source, dest);
        try {
            Collections.copy(dest, source);
        } catch (UnsupportedOperationException e) {
            e.printStackTrace();
        }
    }
}
