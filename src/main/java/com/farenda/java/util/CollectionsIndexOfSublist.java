package com.farenda.java.util;

import java.util.Arrays;
import java.util.Collections;
import java.util.List;

public class CollectionsIndexOfSublist {

    public static void main(String[] args) {
        List<String> list = Arrays.asList("This is a sentence".split(""));

        System.out.println("Original list: " + list);

        findFirstAndLastOccurrence(list, Arrays.asList("i", "s"));

        findFirstAndLastOccurrence(list, Collections.emptyList());

        findFirstAndLastOccurrence(list, Arrays.asList("uh", "oh"));
    }

    private static void findFirstAndLastOccurrence(List<String> words,
                                                   List<String> sublist) {
        System.out.printf("The first occurrence of %s at index: %d%n",
                sublist, Collections.indexOfSubList(words, sublist));

        System.out.printf("The last occurrence of %s at index: %d%n%n",
                sublist, Collections.lastIndexOfSubList(words, sublist));
    }
}
