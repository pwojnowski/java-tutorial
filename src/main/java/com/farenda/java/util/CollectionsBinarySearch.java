package com.farenda.java.util;

import java.util.Arrays;
import java.util.Collections;
import java.util.List;

public class CollectionsBinarySearch {

    public static void main(String[] args) {
        String s = "Binary search in Java";
        s = s.replaceAll(" ", ""); // let's remove the spaces

        List<String> items = Arrays.asList(s.split(""));
        System.out.println("Unsorted items: " + items);

        // Search in unsorted list gives unpredictable result:
        int idx = Collections.binarySearch(items, "i");
        System.out.println("Found 'i' at index: " + idx);

        // Items must be sorted in ascending order:
        Collections.sort(items);
        System.out.println("Sorted items: " + items);

        // Search in a sorted list:
        idx = Collections.binarySearch(items, "i");
        System.out.println("Found 'i' at index: " + idx);

        // Find insertion point for non existing item:
        idx = Collections.binarySearch(items, "d");
        System.out.println("'d' can be inserted at: " + -(idx+1));
    }
}
