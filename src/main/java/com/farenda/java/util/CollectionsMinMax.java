package com.farenda.java.util;

import java.util.Arrays;
import java.util.Collections;
import java.util.Comparator;
import java.util.List;

public class CollectionsMinMax {

    // Orders bigger numbers before smaller
    static class ReverseComparator implements Comparator<Integer> {
        @Override
        public int compare(Integer o1, Integer o2) {
            // Do it other way around:
            return o2 - o1;
        }
    }

    public static void main(String[] args) {
        List<Integer> scores = Arrays.asList(9, 2, 7, 8, 0, 3, 1);

        System.out.println("Scores: " + scores);

        System.out.println("Min score: " + Collections.min(scores));
        System.out.println("Max score: " + Collections.max(scores));

        Comparator<Integer> cmp = new ReverseComparator();
        System.out.println("Min score with own Comparator: "
                + Collections.min(scores, cmp));
        System.out.println("Max score with own Comparator: "
                + Collections.max(scores, cmp));
    }
}
