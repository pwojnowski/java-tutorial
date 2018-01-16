package com.farenda.java.util;

import java.util.Arrays;
import java.util.Collections;
import java.util.Comparator;
import java.util.List;

import static java.util.Arrays.asList;
import static java.util.Arrays.copyOf;

public class CollectionsReverseOrder {

    // Orders numbers in natural order
    static class NumberComparator implements Comparator<Integer> {
        @Override
        public int compare(Integer o1, Integer o2) {
            return o1.compareTo(o2);
        }
    }

    public static void main(String[] args) {
        Integer[] scores = {77, 43, 84, 101};

        Integer[] array = copyOf(scores, scores.length);
        onArray(array);

        List<Integer> list = asList(copyOf(scores, scores.length));
        onList(list);

        list = asList(scores);
        withComparator(list);
    }

    private static void onArray(Integer[] scores) {
        System.out.println("Scores array: " + Arrays.toString(scores));

        Arrays.sort(scores);
        System.out.println("Sorted: " + Arrays.toString(scores));

        Arrays.sort(scores, Collections.reverseOrder());
        System.out.println("Reversed: " + Arrays.toString(scores));
    }

    private static void onList(List<Integer> scores) {
        System.out.println();
        System.out.println("Scores List: " + scores);

        Collections.sort(scores);
        System.out.println("Sorted: " + scores);

        Collections.sort(scores, Collections.reverseOrder());
        System.out.println("Reversed: " + scores);
    }

    private static void withComparator(List<Integer> scores) {
        Comparator<Integer> cmp = new NumberComparator();

        System.out.println();
        System.out.println("Scores List: " + scores);

        Collections.sort(scores, cmp);
        System.out.println("Sorted (comparator): " + scores);

        Collections.sort(scores, Collections.reverseOrder(cmp));
        System.out.println("Reversed (comparator): " + scores);
    }
}
